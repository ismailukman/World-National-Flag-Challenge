import 'package:equatable/equatable.dart';
import 'package:hive/hive.dart';

part 'daily_challenge.g.dart';

/// Model for daily challenge (special quiz that changes daily)
@HiveType(typeId: 3)
class DailyChallenge extends HiveObject with EquatableMixin {
  /// Date of the challenge (YYYY-MM-DD format)
  @HiveField(0)
  final String date;

  /// Flag IDs included in this challenge
  @HiveField(1)
  final List<String> flagIds;

  /// Bonus multiplier for scores (e.g., 2.0 = double points)
  @HiveField(2)
  final double bonusMultiplier;

  /// Whether the challenge has been completed
  @HiveField(3)
  bool isCompleted;

  /// Score achieved (null if not completed)
  @HiveField(4)
  int? scoreAchieved;

  /// Completion time in seconds (null if not completed)
  @HiveField(5)
  int? completionTimeSeconds;

  DailyChallenge({
    required this.date,
    required this.flagIds,
    this.bonusMultiplier = 2.0,
    this.isCompleted = false,
    this.scoreAchieved,
    this.completionTimeSeconds,
  });

  /// Check if this challenge is for today
  bool isToday() {
    final today = DateTime.now();
    final todayString =
        '${today.year}-${today.month.toString().padLeft(2, '0')}-${today.day.toString().padLeft(2, '0')}';
    return date == todayString;
  }

  /// Mark challenge as completed
  void markCompleted({
    required int score,
    required int timeSeconds,
  }) {
    isCompleted = true;
    scoreAchieved = score;
    completionTimeSeconds = timeSeconds;
  }

  /// Get today's date string
  static String getTodayDateString() {
    final today = DateTime.now();
    return '${today.year}-${today.month.toString().padLeft(2, '0')}-${today.day.toString().padLeft(2, '0')}';
  }

  @override
  List<Object?> get props => [
        date,
        flagIds,
        bonusMultiplier,
        isCompleted,
        scoreAchieved,
        completionTimeSeconds,
      ];
}
