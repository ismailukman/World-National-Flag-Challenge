import 'package:equatable/equatable.dart';
import '../../../data/models/leaderboard_entry.dart';

/// States for leaderboard
abstract class LeaderboardState extends Equatable {
  const LeaderboardState();

  @override
  List<Object?> get props => [];
}

/// Initial state
class LeaderboardInitial extends LeaderboardState {
  const LeaderboardInitial();
}

/// Loading leaderboard
class LeaderboardLoading extends LeaderboardState {
  const LeaderboardLoading();
}

/// Leaderboard loaded
class LeaderboardLoaded extends LeaderboardState {
  final List<LeaderboardEntry> entries;
  final int? currentPlayerRank;

  const LeaderboardLoaded({
    required this.entries,
    this.currentPlayerRank,
  });

  @override
  List<Object?> get props => [entries, currentPlayerRank];
}

/// Leaderboard error
class LeaderboardError extends LeaderboardState {
  final String message;

  const LeaderboardError(this.message);

  @override
  List<Object?> get props => [message];
}
