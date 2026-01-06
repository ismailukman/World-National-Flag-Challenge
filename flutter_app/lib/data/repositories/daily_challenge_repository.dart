import 'package:flutter/foundation.dart';
import '../models/daily_challenge.dart';

/// Repository for daily challenges using in-memory storage
/// TODO: Switch back to Hive once adapter issue is resolved
class DailyChallengeRepository {
  // In-memory storage for daily challenges
  static final Map<String, DailyChallenge> _challenges = {};

  /// Get challenge for a specific date (YYYY-MM-DD)
  DailyChallenge? getChallenge(String date) {
    try {
      return _challenges[date];
    } catch (e) {
      debugPrint('Error getting daily challenge: $e');
      return null;
    }
  }

  /// Save or update a challenge
  Future<void> saveChallenge(DailyChallenge challenge) async {
    try {
      _challenges[challenge.date] = challenge;
      debugPrint('Daily challenge saved for ${challenge.date}');
    } catch (e) {
      debugPrint('Error saving daily challenge: $e');
      throw Exception('Failed to save daily challenge: $e');
    }
  }

  /// Delete a challenge for a specific date
  Future<void> deleteChallenge(String date) async {
    try {
      _challenges.remove(date);
      debugPrint('Daily challenge deleted for $date');
    } catch (e) {
      debugPrint('Error deleting daily challenge: $e');
      throw Exception('Failed to delete daily challenge: $e');
    }
  }

  /// Get all completed challenges
  List<DailyChallenge> getAllCompletedChallenges() {
    try {
      return _challenges.values
          .where((challenge) => challenge.isCompleted)
          .toList();
    } catch (e) {
      debugPrint('Error getting completed challenges: $e');
      return [];
    }
  }

  /// Get challenge history (last N days)
  List<DailyChallenge> getChallengeHistory({int days = 7}) {
    try {
      final now = DateTime.now();
      final challenges = <DailyChallenge>[];

      for (int i = 0; i < days; i++) {
        final date = now.subtract(Duration(days: i));
        final dateString =
            '${date.year}-${date.month.toString().padLeft(2, '0')}-${date.day.toString().padLeft(2, '0')}';
        final challenge = getChallenge(dateString);
        if (challenge != null) {
          challenges.add(challenge);
        }
      }

      return challenges;
    } catch (e) {
      debugPrint('Error getting challenge history: $e');
      return [];
    }
  }

  /// Clear all challenges (for testing)
  void clearAll() {
    _challenges.clear();
    debugPrint('All daily challenges cleared');
  }
}
