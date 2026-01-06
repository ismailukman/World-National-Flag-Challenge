import 'package:equatable/equatable.dart';

/// Events for daily challenge
abstract class DailyChallengeEvent extends Equatable {
  const DailyChallengeEvent();

  @override
  List<Object?> get props => [];
}

/// Load today's daily challenge
class LoadDailyChallenge extends DailyChallengeEvent {
  const LoadDailyChallenge();
}

/// Start the daily challenge
class StartDailyChallenge extends DailyChallengeEvent {
  const StartDailyChallenge();
}

/// Answer a daily challenge question
class AnswerDailyChallengeQuestion extends DailyChallengeEvent {
  final String selectedAnswer;

  const AnswerDailyChallengeQuestion(this.selectedAnswer);

  @override
  List<Object?> get props => [selectedAnswer];
}

/// Move to next daily challenge question
class NextDailyChallengeQuestion extends DailyChallengeEvent {
  const NextDailyChallengeQuestion();
}

/// Complete the daily challenge
class CompleteDailyChallenge extends DailyChallengeEvent {
  const CompleteDailyChallenge();
}
