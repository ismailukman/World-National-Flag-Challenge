import 'dart:math';
import 'package:flutter/foundation.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../core/config/scoring_config.dart';
import '../../../data/models/daily_challenge.dart';
import '../../../data/models/flag.dart';
import '../../../data/models/quiz_question.dart';
import '../../../data/repositories/daily_challenge_repository.dart';
import '../../../data/repositories/flag_repository.dart';
import 'daily_challenge_event.dart';
import 'daily_challenge_state.dart';

/// BLoC for daily challenge (special quiz with bonus points)
class DailyChallengeBloc
    extends Bloc<DailyChallengeEvent, DailyChallengeState> {
  final FlagRepository flagRepository;
  final DailyChallengeRepository dailyChallengeRepository;

  // Daily challenge settings
  static const int challengeFlagCount = 10; // 10 flags per daily challenge
  static const double bonusMultiplier = 2.0; // 2x points

  // Challenge session data
  List<QuizQuestion> _questions = [];
  int _currentIndex = 0;
  int _correctCount = 0;
  int _wrongCount = 0;
  DateTime? _startTime;
  DailyChallenge? _currentChallenge;

  DailyChallengeBloc({
    required this.flagRepository,
    required this.dailyChallengeRepository,
  }) : super(const DailyChallengeInitial()) {
    on<LoadDailyChallenge>(_onLoadDailyChallenge);
    on<StartDailyChallenge>(_onStartDailyChallenge);
    on<AnswerDailyChallengeQuestion>(_onAnswerQuestion);
    on<NextDailyChallengeQuestion>(_onNextQuestion);
    on<CompleteDailyChallenge>(_onCompleteChallenge);
  }

  /// Load or generate today's daily challenge
  Future<void> _onLoadDailyChallenge(
    LoadDailyChallenge event,
    Emitter<DailyChallengeState> emit,
  ) async {
    try {
      emit(const DailyChallengeLoading());

      // Load or generate today's challenge
      final today = DailyChallenge.getTodayDateString();
      var challenge = dailyChallengeRepository.getChallenge(today);

      if (challenge == null) {
        // Generate new challenge
        challenge = await _generateDailyChallenge();

        if (challenge.flagIds.isEmpty) {
          emit(const DailyChallengeError('No flags available for daily challenge. Please check your data.'));
          return;
        }

        await dailyChallengeRepository.saveChallenge(challenge);
      }

      _currentChallenge = challenge;

      emit(DailyChallengeReady(
        totalQuestions: challenge.flagIds.length,
        bonusMultiplier: challenge.bonusMultiplier,
        isAlreadyCompleted: challenge.isCompleted,
        previousScore: challenge.scoreAchieved,
      ));
    } catch (e, stackTrace) {
      debugPrint('Daily Challenge Error: $e');
      debugPrint('Stack trace: $stackTrace');
      emit(const DailyChallengeError('Failed to load daily challenge. Please try again later.'));
    }
  }

  /// Start the daily challenge
  Future<void> _onStartDailyChallenge(
    StartDailyChallenge event,
    Emitter<DailyChallengeState> emit,
  ) async {
    if (_currentChallenge == null) {
      emit(const DailyChallengeError('No challenge loaded. Please go back and try again.'));
      return;
    }

    try {
      emit(const DailyChallengeLoading());

      // Generate questions from challenge flag IDs
      _questions = await _generateQuestions(_currentChallenge!.flagIds);
      _currentIndex = 0;
      _correctCount = 0;
      _wrongCount = 0;
      _startTime = DateTime.now();

      if (_questions.isEmpty) {
        emit(const DailyChallengeError('Failed to generate challenge questions. Please check your connection.'));
        return;
      }

      emit(DailyChallengeInProgress(
        currentQuestion: _questions[0],
        totalQuestions: _questions.length,
        currentIndex: 0,
        correctCount: 0,
        wrongCount: 0,
        bonusMultiplier: _currentChallenge!.bonusMultiplier,
        startTime: _startTime!,
      ));
    } catch (e, stackTrace) {
      debugPrint('Failed to start daily challenge: $e');
      debugPrint('Stack trace: $stackTrace');
      emit(const DailyChallengeError('Failed to start challenge. Please try again.'));
    }
  }

  /// Handle user's answer
  Future<void> _onAnswerQuestion(
    AnswerDailyChallengeQuestion event,
    Emitter<DailyChallengeState> emit,
  ) async {
    if (state is! DailyChallengeInProgress) return;

    final currentState = state as DailyChallengeInProgress;
    final currentQuestion = _questions[_currentIndex];

    // Check if answer is correct
    final isCorrect = currentQuestion.checkAnswer(event.selectedAnswer);

    // Update counters
    if (isCorrect) {
      _correctCount++;
    } else {
      _wrongCount++;
    }

    // Show feedback
    emit(currentState.copyWith(
      correctCount: _correctCount,
      wrongCount: _wrongCount,
      showingFeedback: true,
      wasLastAnswerCorrect: isCorrect,
    ));
  }

  /// Move to next question or complete challenge
  Future<void> _onNextQuestion(
    NextDailyChallengeQuestion event,
    Emitter<DailyChallengeState> emit,
  ) async {
    if (state is! DailyChallengeInProgress) return;

    final currentState = state as DailyChallengeInProgress;

    _currentIndex++;

    // Check if challenge is complete
    if (_currentIndex >= _questions.length) {
      add(const CompleteDailyChallenge());
      return;
    }

    // Show next question
    emit(DailyChallengeInProgress(
      currentQuestion: _questions[_currentIndex],
      totalQuestions: _questions.length,
      currentIndex: _currentIndex,
      correctCount: _correctCount,
      wrongCount: _wrongCount,
      bonusMultiplier: currentState.bonusMultiplier,
      startTime: currentState.startTime,
    ));
  }

  /// Complete the daily challenge
  Future<void> _onCompleteChallenge(
    CompleteDailyChallenge event,
    Emitter<DailyChallengeState> emit,
  ) async {
    if (state is! DailyChallengeInProgress) return;

    final currentState = state as DailyChallengeInProgress;

    // Calculate base score
    final baseScore = ScoringConfig.calculateScore(_correctCount, _wrongCount);

    // Apply bonus multiplier
    final bonusScore = (baseScore * currentState.bonusMultiplier).round();

    // Calculate completion time
    final completionTime = DateTime.now().difference(currentState.startTime);
    final completionTimeSeconds = completionTime.inSeconds;

    final wasCompleted = _currentChallenge?.isCompleted ?? false;
    final previousScore = _currentChallenge?.scoreAchieved;

    // Mark challenge as completed and persist
    _currentChallenge?.markCompleted(
      score: bonusScore,
      timeSeconds: completionTimeSeconds,
    );
    if (_currentChallenge != null) {
      await dailyChallengeRepository.saveChallenge(_currentChallenge!);
    }

    // Check if this is a new record (for now, always true on first completion)
    final isNewRecord = !wasCompleted ||
        (previousScore != null && bonusScore > previousScore);

    emit(DailyChallengeCompleted(
      finalScore: bonusScore,
      bonusScore: bonusScore - baseScore,
      correctCount: _correctCount,
      wrongCount: _wrongCount,
      completionTimeSeconds: completionTimeSeconds,
      isNewRecord: isNewRecord,
    ));
  }

  /// Generate today's daily challenge (deterministic based on date)
  Future<DailyChallenge> _generateDailyChallenge() async {
    try {
      final todayString = DailyChallenge.getTodayDateString();

      // Use date as seed for consistent daily challenges
      final seed = todayString.hashCode;
      final random = Random(seed);

      // Get all flags
      final allFlags = await flagRepository.getAllFlags();

      if (allFlags.isEmpty) {
        debugPrint('No flags available to generate daily challenge');
        // Return empty challenge that will be caught by validation
        return DailyChallenge(
          date: todayString,
          flagIds: [],
          bonusMultiplier: bonusMultiplier,
        );
      }

      // Ensure we don't try to take more flags than available
      final flagCount = allFlags.length < challengeFlagCount
          ? allFlags.length
          : challengeFlagCount;

      // Shuffle and select random flags
      final shuffled = List<Flag>.from(allFlags)..shuffle(random);
      final selectedFlags = shuffled.take(flagCount).toList();

      final flagIds = selectedFlags.map((flag) => flag.id).toList();

      debugPrint('Generated daily challenge with ${flagIds.length} flags');

      return DailyChallenge(
        date: todayString,
        flagIds: flagIds,
        bonusMultiplier: bonusMultiplier,
      );
    } catch (e, stackTrace) {
      debugPrint('Error generating daily challenge: $e');
      debugPrint('Stack trace: $stackTrace');
      // Return empty challenge as fallback
      return DailyChallenge(
        date: DailyChallenge.getTodayDateString(),
        flagIds: [],
        bonusMultiplier: bonusMultiplier,
      );
    }
  }

  /// Generate quiz questions from flag IDs
  Future<List<QuizQuestion>> _generateQuestions(List<String> flagIds) async {
    try {
      final allFlags = await flagRepository.getAllFlags();

      if (allFlags.isEmpty) {
        debugPrint('No flags available in repository');
        return [];
      }

      // Get the selected flags
      final selectedFlags =
          allFlags.where((flag) => flagIds.contains(flag.id)).toList();

      if (selectedFlags.isEmpty) {
        debugPrint('No matching flags found for daily challenge IDs');
        return [];
      }

      if (allFlags.length < 4) {
        debugPrint('Not enough flags to create questions (need at least 4)');
        return [];
      }

      // Shuffle for randomization
      selectedFlags.shuffle();

      // Generate questions
      final questions = <QuizQuestion>[];

      for (int i = 0; i < selectedFlags.length; i++) {
        final correctFlag = selectedFlags[i];

        // Generate 3 wrong options
        final wrongOptions = allFlags
            .where((f) => f.id != correctFlag.id)
            .toList()
          ..shuffle();

        if (wrongOptions.length < 3) {
          debugPrint('Not enough wrong options for question ${i + 1}');
          continue;
        }

        final selectedWrongOptions = wrongOptions.take(3).toList();

        // Create 4 options (1 correct + 3 wrong)
        final allOptions = [correctFlag, ...selectedWrongOptions]..shuffle();

        questions.add(QuizQuestion(
          correctFlag: correctFlag,
          allOptions: allOptions,
          questionNumber: i + 1,
        ));
      }

      return questions;
    } catch (e, stackTrace) {
      debugPrint('Error generating questions: $e');
      debugPrint('Stack trace: $stackTrace');
      return [];
    }
  }
}
