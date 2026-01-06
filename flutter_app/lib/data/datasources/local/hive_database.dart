import 'package:hive_flutter/hive_flutter.dart';
import '../../models/user_progress.dart';
import '../../models/achievement.dart';
import '../../models/quiz_session.dart';
import '../../models/daily_challenge.dart';

/// Hive database initialization and management
class HiveDatabase {
  // Box names
  static const String progressBox = 'user_progress';
  static const String achievementsBox = 'achievements';
  static const String sessionsBox = 'quiz_sessions';
  static const String settingsBox = 'settings';
  static const String dailyChallengeBox = 'daily_challenges';

  static bool _isInitialized = false;

  /// Initialize Hive database
  static Future<void> init() async {
    if (_isInitialized) return;

    // Initialize Hive for Flutter
    await Hive.initFlutter();

    // Register type adapters
    if (!Hive.isAdapterRegistered(0)) {
      Hive.registerAdapter(UserProgressAdapter());
    }
    if (!Hive.isAdapterRegistered(1)) {
      Hive.registerAdapter(AchievementAdapter());
    }
    if (!Hive.isAdapterRegistered(10)) {
      Hive.registerAdapter(AchievementTypeAdapter());
    }
    if (!Hive.isAdapterRegistered(2)) {
      Hive.registerAdapter(QuizSessionAdapter());
    }
    if (!Hive.isAdapterRegistered(3)) {
      Hive.registerAdapter(DailyChallengeAdapter());
    }

    // Open boxes
    await Hive.openBox<UserProgress>(progressBox);
    await Hive.openBox<Achievement>(achievementsBox);
    await Hive.openBox<QuizSession>(sessionsBox);
    await Hive.openBox(settingsBox);
    await Hive.openBox<DailyChallenge>(dailyChallengeBox);

    _isInitialized = true;
  }

  /// Get progress box
  static Box<UserProgress> getProgressBox() {
    return Hive.box<UserProgress>(progressBox);
  }

  /// Get achievements box
  static Box<Achievement> getAchievementsBox() {
    return Hive.box<Achievement>(achievementsBox);
  }

  /// Get sessions box
  static Box<QuizSession> getSessionsBox() {
    return Hive.box<QuizSession>(sessionsBox);
  }

  /// Get settings box (for language, audio preferences, etc.)
  static Box getSettingsBox() {
    return Hive.box(settingsBox);
  }

  /// Get daily challenges box
  static Box<DailyChallenge> getDailyChallengesBox() {
    return Hive.box<DailyChallenge>(dailyChallengeBox);
  }

  /// Close all boxes
  static Future<void> close() async {
    await Hive.close();
    _isInitialized = false;
  }

  /// Clear all data (for testing or reset)
  static Future<void> clearAll() async {
    await getProgressBox().clear();
    await getAchievementsBox().clear();
    await getSessionsBox().clear();
    await getSettingsBox().clear();
    await getDailyChallengesBox().clear();
  }

  /// Delete all boxes (complete reset)
  static Future<void> deleteAll() async {
    await Hive.deleteBoxFromDisk(progressBox);
    await Hive.deleteBoxFromDisk(achievementsBox);
    await Hive.deleteBoxFromDisk(sessionsBox);
    await Hive.deleteBoxFromDisk(settingsBox);
    await Hive.deleteBoxFromDisk(dailyChallengeBox);
    _isInitialized = false;
  }
}
