import 'package:equatable/equatable.dart';
import '../../../data/models/quiz_question.dart';

/// States for daily challenge
abstract class DailyChallengeState extends Equatable {
  const DailyChallengeState();

  @override
  List<Object?> get props => [];
}

/// Initial state
class DailyChallengeInitial extends DailyChallengeState {
  const DailyChallengeInitial();
}

/// Loading daily challenge
class DailyChallengeLoading extends DailyChallengeState {
  const DailyChallengeLoading();
}

/// Daily challenge ready to start
class DailyChallengeReady extends DailyChallengeState {
  final int totalQuestions;
  final double bonusMultiplier;
  final bool isAlreadyCompleted;
  final int? previousScore;

  const DailyChallengeReady({
    required this.totalQuestions,
    required this.bonusMultiplier,
    required this.isAlreadyCompleted,
    this.previousScore,
  });

  @override
  List<Object?> get props =>
      [totalQuestions, bonusMultiplier, isAlreadyCompleted, previousScore];
}

/// Daily challenge in progress
class DailyChallengeInProgress extends DailyChallengeState {
  final QuizQuestion currentQuestion;
  final int totalQuestions;
  final int currentIndex;
  final int correctCount;
  final int wrongCount;
  final double bonusMultiplier;
  final DateTime startTime;
  final bool showingFeedback;
  final bool? wasLastAnswerCorrect;

  const DailyChallengeInProgress({
    required this.currentQuestion,
    required this.totalQuestions,
    required this.currentIndex,
    required this.correctCount,
    required this.wrongCount,
    required this.bonusMultiplier,
    required this.startTime,
    this.showingFeedback = false,
    this.wasLastAnswerCorrect,
  });

  @override
  List<Object?> get props => [
        currentQuestion,
        totalQuestions,
        currentIndex,
        correctCount,
        wrongCount,
        bonusMultiplier,
        startTime,
        showingFeedback,
        wasLastAnswerCorrect,
      ];

  DailyChallengeInProgress copyWith({
    QuizQuestion? currentQuestion,
    int? totalQuestions,
    int? currentIndex,
    int? correctCount,
    int? wrongCount,
    double? bonusMultiplier,
    DateTime? startTime,
    bool? showingFeedback,
    bool? wasLastAnswerCorrect,
  }) {
    return DailyChallengeInProgress(
      currentQuestion: currentQuestion ?? this.currentQuestion,
      totalQuestions: totalQuestions ?? this.totalQuestions,
      currentIndex: currentIndex ?? this.currentIndex,
      correctCount: correctCount ?? this.correctCount,
      wrongCount: wrongCount ?? this.wrongCount,
      bonusMultiplier: bonusMultiplier ?? this.bonusMultiplier,
      startTime: startTime ?? this.startTime,
      showingFeedback: showingFeedback ?? this.showingFeedback,
      wasLastAnswerCorrect: wasLastAnswerCorrect ?? this.wasLastAnswerCorrect,
    );
  }
}

/// Daily challenge completed
class DailyChallengeCompleted extends DailyChallengeState {
  final int finalScore;
  final int bonusScore;
  final int correctCount;
  final int wrongCount;
  final int completionTimeSeconds;
  final bool isNewRecord;

  const DailyChallengeCompleted({
    required this.finalScore,
    required this.bonusScore,
    required this.correctCount,
    required this.wrongCount,
    required this.completionTimeSeconds,
    required this.isNewRecord,
  });

  @override
  List<Object?> get props => [
        finalScore,
        bonusScore,
        correctCount,
        wrongCount,
        completionTimeSeconds,
        isNewRecord,
      ];
}

/// Error loading daily challenge
class DailyChallengeError extends DailyChallengeState {
  final String message;

  const DailyChallengeError(this.message);

  @override
  List<Object?> get props => [message];
}
