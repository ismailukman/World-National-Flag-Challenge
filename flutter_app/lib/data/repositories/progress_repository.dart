import 'package:hive/hive.dart';
import '../models/user_progress.dart';
import '../datasources/local/hive_database.dart';

/// Repository for managing user progress data
class ProgressRepository {
  late Box<UserProgress> _box;

  ProgressRepository() {
    _box = HiveDatabase.getProgressBox();
  }

  /// Get progress for a specific continent
  Future<UserProgress> getProgress(String continentId) async {
    UserProgress? progress = _box.get(continentId);

    // If no progress exists, create a new one
    if (progress == null) {
      progress = UserProgress(
        continentId: continentId,
        isUnlocked: true, // FREE EXPLORATION: all continents unlocked
      );
      await saveProgress(progress);
    }

    return progress;
  }

  /// Save or update progress
  Future<void> saveProgress(UserProgress progress) async {
    await _box.put(progress.continentId, progress);
  }

  /// Get progress for all continents
  Future<List<UserProgress>> getAllProgress() async {
    return _box.values.toList();
  }

  /// Update high score for a continent
  Future<void> updateHighScore(String continentId, int score) async {
    final progress = await getProgress(continentId);

    if (score > progress.highScore) {
      progress.highScore = score;
      await progress.save();
    }
  }

  /// Update progress after completing a quiz
  Future<void> updateAfterQuiz({
    required String continentId,
    required int score,
    required int correct,
    required int wrong,
    required List<String> correctFlagIds,
    required List<String> wrongFlagIds,
    required int totalFlagsInContinent,
  }) async {
    final progress = await getProgress(continentId);

    progress.updateAfterQuiz(
      score: score,
      correct: correct,
      wrong: wrong,
      newLearnedFlags: correctFlagIds,
      newDifficultFlags: wrongFlagIds,
      totalFlagsInContinent: totalFlagsInContinent,
    );
  }

  /// Mark a flag as learned
  Future<void> markFlagAsLearned(String continentId, String flagId) async {
    final progress = await getProgress(continentId);

    if (!progress.learnedFlagIds.contains(flagId)) {
      progress.learnedFlagIds.add(flagId);
      await progress.save();
    }
  }

  /// Get overall completion percentage across all continents
  Future<double> getOverallCompletionPercentage() async {
    final allProgress = await getAllProgress();

    if (allProgress.isEmpty) return 0.0;

    final totalCompletion = allProgress.fold<double>(
      0.0,
      (sum, progress) => sum + progress.completionPercentage,
    );

    return totalCompletion / allProgress.length;
  }

  /// Get total quizzes taken across all continents
  Future<int> getTotalQuizzesTaken() async {
    final allProgress = await getAllProgress();
    return allProgress.fold<int>(
      0,
      (sum, progress) => sum + progress.totalQuizzesTaken,
    );
  }

  /// Get total flags learned across all continents
  Future<int> getTotalFlagsLearned() async {
    final allProgress = await getAllProgress();
    return allProgress.fold<int>(
      0,
      (sum, progress) => sum + progress.learnedFlagIds.length,
    );
  }

  /// Get overall accuracy across all continents
  Future<double> getOverallAccuracy() async {
    final allProgress = await getAllProgress();

    final totalCorrect = allProgress.fold<int>(
      0,
      (sum, progress) => sum + progress.totalCorrectAnswers,
    );

    final totalWrong = allProgress.fold<int>(
      0,
      (sum, progress) => sum + progress.totalWrongAnswers,
    );

    final total = totalCorrect + totalWrong;
    if (total == 0) return 0.0;

    return (totalCorrect / total) * 100;
  }

  /// Reset progress for a specific continent
  Future<void> resetProgress(String continentId) async {
    await _box.delete(continentId);
  }

  /// Reset all progress
  Future<void> resetAllProgress() async {
    await _box.clear();
  }

  /// Check if a continent has any progress
  Future<bool> hasProgress(String continentId) async {
    return _box.containsKey(continentId);
  }

  /// Get difficult flags across all continents (for practice mode)
  Future<List<String>> getAllDifficultFlags() async {
    final allProgress = await getAllProgress();
    final difficultFlags = <String>{};

    for (final progress in allProgress) {
      difficultFlags.addAll(progress.difficultFlagIds);
    }

    return difficultFlags.toList();
  }
}
