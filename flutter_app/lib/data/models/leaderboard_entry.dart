import 'package:equatable/equatable.dart';
import 'package:hive/hive.dart';

part 'leaderboard_entry.g.dart';

/// Model for leaderboard entry (local rankings)
@HiveType(typeId: 4)
class LeaderboardEntry extends HiveObject with EquatableMixin {
  /// Player name
  @HiveField(0)
  final String playerName;

  /// Total score across all quizzes
  @HiveField(1)
  final int totalScore;

  /// Total number of quizzes taken
  @HiveField(2)
  final int totalQuizzes;

  /// Total flags learned
  @HiveField(3)
  final int flagsLearned;

  /// Highest single quiz score
  @HiveField(4)
  final int highestQuizScore;

  /// Date of last quiz
  @HiveField(5)
  final DateTime lastPlayedDate;

  /// Current streak
  @HiveField(6)
  final int currentStreak;

  LeaderboardEntry({
    required this.playerName,
    required this.totalScore,
    required this.totalQuizzes,
    required this.flagsLearned,
    required this.highestQuizScore,
    required this.lastPlayedDate,
    this.currentStreak = 0,
  });

  /// Calculate average score
  double get averageScore {
    if (totalQuizzes == 0) return 0.0;
    return totalScore / totalQuizzes;
  }

  /// Get rank badge
  String getRankBadge(int rank) {
    switch (rank) {
      case 1:
        return '🥇'; // Gold
      case 2:
        return '🥈'; // Silver
      case 3:
        return '🥉'; // Bronze
      default:
        return '#$rank';
    }
  }

  @override
  List<Object?> get props => [
        playerName,
        totalScore,
        totalQuizzes,
        flagsLearned,
        highestQuizScore,
        lastPlayedDate,
        currentStreak,
      ];
}
