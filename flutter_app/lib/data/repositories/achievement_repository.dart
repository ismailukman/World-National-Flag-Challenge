import 'dart:async';
import 'package:hive/hive.dart';
import '../models/achievement.dart';
import '../datasources/local/hive_database.dart';

/// Repository for managing achievements and badges
class AchievementRepository {
  late Box<Achievement> _box;
  final StreamController<Achievement> _achievementUnlockedController =
      StreamController<Achievement>.broadcast();

  AchievementRepository() {
    _box = HiveDatabase.getAchievementsBox();
    _initializeAchievements();
  }

  /// Stream of newly unlocked achievements
  Stream<Achievement> get achievementUnlocked => _achievementUnlockedController.stream;

  /// Initialize predefined achievements if not already present
  Future<void> _initializeAchievements() async {
    if (_box.isEmpty) {
      for (final achievement in Achievements.all) {
        await _box.put(achievement.id, achievement);
      }
    }
  }

  /// Get all achievements
  Future<List<Achievement>> getAllAchievements() async {
    return _box.values.toList();
  }

  /// Get a specific achievement by ID
  Future<Achievement?> getAchievementById(String id) async {
    return _box.get(id);
  }

  /// Get unlocked achievements
  Future<List<Achievement>> getUnlockedAchievements() async {
    final allAchievements = await getAllAchievements();
    return allAchievements.where((achievement) => achievement.isUnlocked).toList();
  }

  /// Get locked achievements
  Future<List<Achievement>> getLockedAchievements() async {
    final allAchievements = await getAllAchievements();
    return allAchievements.where((achievement) => !achievement.isUnlocked).toList();
  }

  /// Get achievements in progress (started but not completed)
  Future<List<Achievement>> getInProgressAchievements() async {
    final allAchievements = await getAllAchievements();
    return allAchievements.where((achievement) => achievement.isInProgress).toList();
  }

  /// Get achievements by type
  Future<List<Achievement>> getAchievementsByType(AchievementType type) async {
    final allAchievements = await getAllAchievements();
    return allAchievements.where((achievement) => achievement.type == type).toList();
  }

  /// Update achievement progress
  Future<void> updateProgress(String achievementId, int progress) async {
    final achievement = await getAchievementById(achievementId);
    if (achievement == null) return;

    final wasLocked = !achievement.isUnlocked;
    achievement.updateProgress(progress);

    // If achievement was just unlocked, notify listeners
    if (wasLocked && achievement.isUnlocked) {
      _achievementUnlockedController.add(achievement);
    }
  }

  /// Unlock an achievement immediately
  Future<void> unlockAchievement(String achievementId) async {
    final achievement = await getAchievementById(achievementId);
    if (achievement == null) return;

    if (!achievement.isUnlocked) {
      achievement.unlock();
      _achievementUnlockedController.add(achievement);
    }
  }

  /// Check and unlock achievements based on game events
  Future<List<Achievement>> checkAndUnlockAchievements({
    int? totalQuizzes,
    int? currentStreak,
    int? quizScore,
    int? totalFlagsInQuiz,
    int? correctAnswers,
    int? quizDuration, // in seconds
    bool? triedAllContinents,
  }) async {
    final unlockedAchievements = <Achievement>[];

    // Check First Quiz
    if (totalQuizzes != null && totalQuizzes >= 1) {
      final achievement = await getAchievementById('first_quiz');
      if (achievement != null && !achievement.isUnlocked) {
        await unlockAchievement('first_quiz');
        unlockedAchievements.add(achievement);
      }
    }

    // Check Global Explorer
    if (triedAllContinents == true) {
      final achievement = await getAchievementById('global_explorer');
      if (achievement != null && !achievement.isUnlocked) {
        await unlockAchievement('global_explorer');
        unlockedAchievements.add(achievement);
      }
    }

    // Check Perfectionist (100% correct)
    if (correctAnswers != null && totalFlagsInQuiz != null && correctAnswers == totalFlagsInQuiz) {
      final achievement = await getAchievementById('perfectionist');
      if (achievement != null && !achievement.isUnlocked) {
        await unlockAchievement('perfectionist');
        unlockedAchievements.add(achievement);
      }
    }

    // Check Africa Expert (perfect score 162)
    if (quizScore != null && quizScore >= 162) {
      final achievement = await getAchievementById('africa_expert');
      if (achievement != null && !achievement.isUnlocked) {
        await unlockAchievement('africa_expert');
        unlockedAchievements.add(achievement);
      }
    }

    // Check Streak achievements
    if (currentStreak != null) {
      if (currentStreak >= 7) {
        final achievement = await getAchievementById('week_warrior');
        if (achievement != null && !achievement.isUnlocked) {
          await unlockAchievement('week_warrior');
          unlockedAchievements.add(achievement);
        }
      }
      if (currentStreak >= 14) {
        final achievement = await getAchievementById('fortnight_fighter');
        if (achievement != null && !achievement.isUnlocked) {
          await unlockAchievement('fortnight_fighter');
          unlockedAchievements.add(achievement);
        }
      }
      if (currentStreak >= 30) {
        final achievement = await getAchievementById('monthly_master');
        if (achievement != null && !achievement.isUnlocked) {
          await unlockAchievement('monthly_master');
          unlockedAchievements.add(achievement);
        }
      }
    }

    // Check Speedster (quiz completed in under 90 seconds)
    if (quizDuration != null && quizDuration <= 90) {
      final achievement = await getAchievementById('speedster');
      if (achievement != null && !achievement.isUnlocked) {
        await unlockAchievement('speedster');
        unlockedAchievements.add(achievement);
      }
    }

    // Check Dedication achievements
    if (totalQuizzes != null) {
      if (totalQuizzes >= 50) {
        final achievement = await getAchievementById('dedicated_learner');
        if (achievement != null && !achievement.isUnlocked) {
          await unlockAchievement('dedicated_learner');
          unlockedAchievements.add(achievement);
        }
      }
      if (totalQuizzes >= 100) {
        final achievement = await getAchievementById('flag_fanatic');
        if (achievement != null && !achievement.isUnlocked) {
          await unlockAchievement('flag_fanatic');
          unlockedAchievements.add(achievement);
        }
      }
    }

    return unlockedAchievements;
  }

  /// Get completion percentage (unlocked / total)
  Future<double> getCompletionPercentage() async {
    final allAchievements = await getAllAchievements();
    if (allAchievements.isEmpty) return 0.0;

    final unlockedCount = allAchievements.where((a) => a.isUnlocked).length;
    return (unlockedCount / allAchievements.length) * 100;
  }

  /// Get total unlocked achievements count
  Future<int> getUnlockedCount() async {
    final unlockedAchievements = await getUnlockedAchievements();
    return unlockedAchievements.length;
  }

  /// Reset all achievements (for testing)
  Future<void> resetAllAchievements() async {
    await _box.clear();
    await _initializeAchievements();
  }

  /// Dispose stream controller
  void dispose() {
    _achievementUnlockedController.close();
  }
}
