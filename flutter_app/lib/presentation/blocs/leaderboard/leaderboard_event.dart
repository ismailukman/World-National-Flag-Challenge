import 'package:equatable/equatable.dart';

/// Events for leaderboard
abstract class LeaderboardEvent extends Equatable {
  const LeaderboardEvent();

  @override
  List<Object?> get props => [];
}

/// Load leaderboard
class LoadLeaderboard extends LeaderboardEvent {
  const LoadLeaderboard();
}

/// Refresh leaderboard
class RefreshLeaderboard extends LeaderboardEvent {
  const RefreshLeaderboard();
}

/// Update player entry (after quiz completion)
class UpdatePlayerEntry extends LeaderboardEvent {
  final String playerName;
  final int quizScore;
  final int flagsInQuiz;

  const UpdatePlayerEntry({
    required this.playerName,
    required this.quizScore,
    required this.flagsInQuiz,
  });

  @override
  List<Object?> get props => [playerName, quizScore, flagsInQuiz];
}
