import 'package:equatable/equatable.dart';
import '../../../data/models/quiz_question.dart';
import '../../../data/models/achievement.dart';

/// States for quiz flow
abstract class QuizState extends Equatable {
  const QuizState();

  @override
  List<Object?> get props => [];
}

/// Initial state
class QuizInitial extends QuizState {
  const QuizInitial();
}

/// Loading quiz data
class QuizLoading extends QuizState {
  const QuizLoading();
}

/// Quiz in progress - showing a question
class QuizInProgress extends QuizState {
  final QuizQuestion currentQuestion;
  final int totalQuestions;
  final int currentIndex;
  final int correctCount;
  final int wrongCount;
  final bool showingFeedback; // Show answer feedback (green/red)
  final bool? wasLastAnswerCorrect;
  final String continentId;

  const QuizInProgress({
    required this.currentQuestion,
    required this.totalQuestions,
    required this.currentIndex,
    required this.correctCount,
    required this.wrongCount,
    required this.continentId,
    this.showingFeedback = false,
    this.wasLastAnswerCorrect,
  });

  /// Get current score using Kotlin formula: correct * 3 - wrong
  int get currentScore => (correctCount * 3) - wrongCount;

  /// Get progress percentage
  double get progressPercentage {
    if (totalQuestions == 0) return 0.0;
    return (currentIndex / totalQuestions) * 100;
  }

  @override
  List<Object?> get props => [
        currentQuestion,
        totalQuestions,
        currentIndex,
        correctCount,
        wrongCount,
        showingFeedback,
        wasLastAnswerCorrect,
        continentId,
      ];

  /// Create a copy with modified fields
  QuizInProgress copyWith({
    QuizQuestion? currentQuestion,
    int? totalQuestions,
    int? currentIndex,
    int? correctCount,
    int? wrongCount,
    bool? showingFeedback,
    bool? wasLastAnswerCorrect,
    String? continentId,
  }) {
    return QuizInProgress(
      currentQuestion: currentQuestion ?? this.currentQuestion,
      totalQuestions: totalQuestions ?? this.totalQuestions,
      currentIndex: currentIndex ?? this.currentIndex,
      correctCount: correctCount ?? this.correctCount,
      wrongCount: wrongCount ?? this.wrongCount,
      continentId: continentId ?? this.continentId,
      showingFeedback: showingFeedback ?? this.showingFeedback,
      wasLastAnswerCorrect: wasLastAnswerCorrect ?? this.wasLastAnswerCorrect,
    );
  }
}

/// Quiz paused (session saved)
class QuizPaused extends QuizState {
  final String sessionId;

  const QuizPaused(this.sessionId);

  @override
  List<Object?> get props => [sessionId];
}

/// Quiz completed
class QuizCompleted extends QuizState {
  final int finalScore;
  final int correctCount;
  final int wrongCount;
  final bool isHighScore;
  final String continentId;
  final List<Achievement>? newlyUnlockedAchievements;

  const QuizCompleted({
    required this.finalScore,
    required this.correctCount,
    required this.wrongCount,
    required this.isHighScore,
    required this.continentId,
    this.newlyUnlockedAchievements,
  });

  /// Get accuracy percentage
  double get accuracy {
    final total = correctCount + wrongCount;
    if (total == 0) return 0.0;
    return (correctCount / total) * 100;
  }

  @override
  List<Object?> get props => [
        finalScore,
        correctCount,
        wrongCount,
        isHighScore,
        continentId,
        newlyUnlockedAchievements,
      ];
}

/// Error state
class QuizError extends QuizState {
  final String message;

  const QuizError(this.message);

  @override
  List<Object?> get props => [message];
}
