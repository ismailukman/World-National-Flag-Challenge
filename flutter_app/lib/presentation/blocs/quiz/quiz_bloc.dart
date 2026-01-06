import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../core/config/scoring_config.dart';
import '../../../data/models/flag.dart';
import '../../../data/models/quiz_question.dart';
import '../../../data/repositories/flag_repository.dart';
import '../../../data/repositories/progress_repository.dart';
import '../../../data/repositories/achievement_repository.dart';
import 'quiz_event.dart';
import 'quiz_state.dart';

/// BLoC for managing quiz flow (core game logic)
class QuizBloc extends Bloc<QuizEvent, QuizState> {
  final FlagRepository flagRepository;
  final ProgressRepository progressRepository;
  final AchievementRepository achievementRepository;

  // Quiz session data
  List<QuizQuestion> _questions = [];
  int _currentIndex = 0;
  int _correctCount = 0;
  int _wrongCount = 0;
  String _continentId = '';
  DateTime? _quizStartTime;

  QuizBloc({
    required this.flagRepository,
    required this.progressRepository,
    required this.achievementRepository,
  }) : super(const QuizInitial()) {
    on<StartQuiz>(_onStartQuiz);
    on<AnswerQuestion>(_onAnswerQuestion);
    on<NextQuestion>(_onNextQuestion);
    on<RestartQuiz>(_onRestartQuiz);
  }

  /// Start a new quiz for a continent
  Future<void> _onStartQuiz(
    StartQuiz event,
    Emitter<QuizState> emit,
  ) async {
    try {
      emit(const QuizLoading());

      _continentId = event.continentId;
      _questions = await _generateQuestions(event.continentId);
      _currentIndex = 0;
      _correctCount = 0;
      _wrongCount = 0;
      _quizStartTime = DateTime.now();

      if (_questions.isEmpty) {
        emit(const QuizError('No flags found for this continent'));
        return;
      }

      emit(QuizInProgress(
        currentQuestion: _questions[0],
        totalQuestions: _questions.length,
        currentIndex: 0,
        correctCount: 0,
        wrongCount: 0,
        continentId: _continentId,
      ));
    } catch (e) {
      emit(QuizError('Failed to load quiz: $e'));
    }
  }

  /// Handle user's answer
  Future<void> _onAnswerQuestion(
    AnswerQuestion event,
    Emitter<QuizState> emit,
  ) async {
    if (state is! QuizInProgress) return;

    final currentState = state as QuizInProgress;
    final currentQuestion = _questions[_currentIndex];

    // Check if answer is correct
    final isCorrect = currentQuestion.checkAnswer(event.selectedAnswer);

    // Update counters
    if (isCorrect) {
      _correctCount++;
    } else {
      _wrongCount++;
    }

    // Show feedback with updated counts
    emit(currentState.copyWith(
      correctCount: _correctCount,
      wrongCount: _wrongCount,
      showingFeedback: true,
      wasLastAnswerCorrect: isCorrect,
    ));
  }

  /// Move to next question or complete quiz
  Future<void> _onNextQuestion(
    NextQuestion event,
    Emitter<QuizState> emit,
  ) async {
    if (state is! QuizInProgress) return;

    _currentIndex++;

    // Check if quiz is complete
    if (_currentIndex >= _questions.length) {
      await _completeQuiz(emit);
      return;
    }

    // Show next question
    emit(QuizInProgress(
      currentQuestion: _questions[_currentIndex],
      totalQuestions: _questions.length,
      currentIndex: _currentIndex,
      correctCount: _correctCount,
      wrongCount: _wrongCount,
      continentId: _continentId,
    ));
  }

  /// Complete the quiz and calculate final score
  Future<void> _completeQuiz(Emitter<QuizState> emit) async {
    // Calculate final score using Kotlin formula: correct * 3 - wrong
    final finalScore = ScoringConfig.calculateScore(_correctCount, _wrongCount);

    // Get progress to check for high score
    final progress = await progressRepository.getProgress(_continentId);
    final isHighScore = finalScore > progress.highScore;

    // Get flags that were answered correctly/incorrectly
    final correctFlagIds = <String>[];
    final wrongFlagIds = <String>[];

    for (final question in _questions) {
      if (question.isCorrect == true) {
        correctFlagIds.add(question.correctFlag.id);
      } else if (question.isCorrect == false) {
        wrongFlagIds.add(question.correctFlag.id);
      }
    }

    // Update progress
    await progressRepository.updateAfterQuiz(
      continentId: _continentId,
      score: finalScore,
      correct: _correctCount,
      wrong: _wrongCount,
      correctFlagIds: correctFlagIds,
      wrongFlagIds: wrongFlagIds,
      totalFlagsInContinent: _questions.length,
    );

    // Calculate quiz duration
    final quizDuration = _quizStartTime != null
        ? DateTime.now().difference(_quizStartTime!).inSeconds
        : 0;

    // Get total quizzes for achievement checking
    final totalQuizzes = await progressRepository.getTotalQuizzesTaken();

    // Check for achievements
    final unlockedAchievements =
        await achievementRepository.checkAndUnlockAchievements(
      totalQuizzes: totalQuizzes,
      quizScore: finalScore,
      totalFlagsInQuiz: _questions.length,
      correctAnswers: _correctCount,
      quizDuration: quizDuration,
    );

    emit(QuizCompleted(
      finalScore: finalScore,
      correctCount: _correctCount,
      wrongCount: _wrongCount,
      isHighScore: isHighScore,
      continentId: _continentId,
      newlyUnlockedAchievements:
          unlockedAchievements.isEmpty ? null : unlockedAchievements,
    ));
  }

  /// Restart the current quiz
  Future<void> _onRestartQuiz(
    RestartQuiz event,
    Emitter<QuizState> emit,
  ) async {
    add(StartQuiz(_continentId));
  }

  /// Generate shuffled quiz questions (matches Kotlin logic)
  Future<List<QuizQuestion>> _generateQuestions(String continentId) async {
    // Load all flags for the continent
    final allFlags = await flagRepository.getFlagsByContinent(continentId);

    if (allFlags.isEmpty) {
      return [];
    }

    // Ensure unique flags per session by ID, then shuffle
    final uniqueFlags = <String, Flag>{};
    for (final flag in allFlags) {
      uniqueFlags.putIfAbsent(flag.id, () => flag);
    }
    final shuffledFlags = uniqueFlags.values.toList()..shuffle();

    // Generate questions
    final questions = <QuizQuestion>[];

    for (int i = 0; i < shuffledFlags.length; i++) {
      final correctFlag = shuffledFlags[i];

      // Generate 3 wrong options from the same continent
      final wrongOptions =
          allFlags.where((f) => f.id != correctFlag.id).toList()..shuffle();

      final selectedWrongOptions = wrongOptions.take(3).toList();

      // Create 4 options (1 correct + 3 wrong)
      final allOptions = [correctFlag, ...selectedWrongOptions];
      final displayOptions = List<Flag>.from(allOptions)..shuffle();

      questions.add(QuizQuestion(
        correctFlag: correctFlag,
        allOptions: allOptions,
        displayOptions: displayOptions,
        questionNumber: i + 1,
      ));
    }

    return questions;
  }

  /// Get current quiz state info
  String get continentId => _continentId;
  int get currentQuestionIndex => _currentIndex;
  int get totalQuestions => _questions.length;
  int get correctCount => _correctCount;
  int get wrongCount => _wrongCount;
  int get currentScore =>
      ScoringConfig.calculateScore(_correctCount, _wrongCount);
}
