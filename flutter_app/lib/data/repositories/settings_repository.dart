import 'package:hive/hive.dart';
import '../datasources/local/hive_database.dart';

/// Repository for managing app settings (language, audio, etc.)
class SettingsRepository {
  late Box _box;

  // Setting keys
  static const String _languageKey = 'language';
  static const String _musicEnabledKey = 'music_enabled';
  static const String _sfxEnabledKey = 'sfx_enabled';
  static const String _currentMusicTrackKey = 'current_music_track';
  static const String _lastPlayedDateKey = 'last_played_date';
  static const String _currentStreakKey = 'current_streak';
  static const String _longestStreakKey = 'longest_streak';
  static const String _mascotEnabledKey = 'mascot_enabled';
  static const String _bookmarkedFlagsKey = 'bookmarked_flags';
  static const String _flagStyleKey = 'flag_style';

  SettingsRepository() {
    _box = HiveDatabase.getSettingsBox();
  }

  // Language Settings
  /// Get current language code (default: 'en')
  String getLanguage() {
    return _box.get(_languageKey, defaultValue: 'en') as String;
  }

  /// Check if language has been set before (for first launch detection)
  bool hasLanguageBeenSet() {
    return _box.containsKey(_languageKey);
  }

  /// Save language preference
  Future<void> saveLanguage(String languageCode) async {
    await _box.put(_languageKey, languageCode);
  }

  // Audio Settings
  /// Check if background music is enabled (default: true)
  bool isMusicEnabled() {
    return _box.get(_musicEnabledKey, defaultValue: true) as bool;
  }

  /// Toggle background music
  Future<void> toggleMusic() async {
    final currentValue = isMusicEnabled();
    await _box.put(_musicEnabledKey, !currentValue);
  }

  /// Set music enabled state
  Future<void> setMusicEnabled(bool enabled) async {
    await _box.put(_musicEnabledKey, enabled);
  }

  /// Check if sound effects are enabled (default: true)
  bool isSfxEnabled() {
    return _box.get(_sfxEnabledKey, defaultValue: true) as bool;
  }

  /// Toggle sound effects
  Future<void> toggleSfx() async {
    final currentValue = isSfxEnabled();
    await _box.put(_sfxEnabledKey, !currentValue);
  }

  /// Set SFX enabled state
  Future<void> setSfxEnabled(bool enabled) async {
    await _box.put(_sfxEnabledKey, enabled);
  }

  /// Get current music track (default: 'afro')
  String getCurrentMusicTrack() {
    return _box.get(_currentMusicTrackKey, defaultValue: 'afro') as String;
  }

  /// Set current music track ('afro', 'game', or 'war')
  Future<void> setMusicTrack(String track) async {
    assert(['afro', 'game', 'war'].contains(track), 'Invalid music track');
    await _box.put(_currentMusicTrackKey, track);
  }

  // Streak Tracking
  /// Get last played date
  DateTime? getLastPlayedDate() {
    final timestamp = _box.get(_lastPlayedDateKey);
    if (timestamp == null) return null;
    return DateTime.fromMillisecondsSinceEpoch(timestamp as int);
  }

  /// Update last played date to today
  Future<void> updateLastPlayedDate() async {
    await _box.put(_lastPlayedDateKey, DateTime.now().millisecondsSinceEpoch);
  }

  /// Get current streak
  int getCurrentStreak() {
    return _box.get(_currentStreakKey, defaultValue: 0) as int;
  }

  /// Update streak (call this when user plays)
  Future<int> updateStreak() async {
    final lastPlayed = getLastPlayedDate();
    final today = DateTime.now();
    int currentStreak = getCurrentStreak();

    if (lastPlayed == null) {
      // First time playing
      currentStreak = 1;
    } else {
      final daysSinceLastPlayed = today.difference(lastPlayed).inDays;

      if (daysSinceLastPlayed == 0) {
        // Already played today, streak unchanged
        return currentStreak;
      } else if (daysSinceLastPlayed == 1) {
        // Played yesterday, increment streak
        currentStreak++;
      } else {
        // Streak broken, reset to 1
        currentStreak = 1;
      }
    }

    // Update current streak
    await _box.put(_currentStreakKey, currentStreak);

    // Update longest streak if needed
    final longestStreak = getLongestStreak();
    if (currentStreak > longestStreak) {
      await _box.put(_longestStreakKey, currentStreak);
    }

    // Update last played date
    await updateLastPlayedDate();

    return currentStreak;
  }

  // Flag Style Settings
  /// Get current flag style (default: 'squared')
  String getFlagStyle() {
    return _box.get(_flagStyleKey, defaultValue: 'squared') as String;
  }

  /// Set flag style ('squared' or 'waving')
  Future<void> setFlagStyle(String style) async {
    assert(['squared', 'waving'].contains(style), 'Invalid flag style');
    await _box.put(_flagStyleKey, style);
  }

  /// Get longest streak ever achieved
  int getLongestStreak() {
    return _box.get(_longestStreakKey, defaultValue: 0) as int;
  }

  /// Reset streak (for testing)
  Future<void> resetStreak() async {
    await _box.put(_currentStreakKey, 0);
    await _box.delete(_lastPlayedDateKey);
  }

  // Mascot Settings
  /// Check if mascot is enabled (default: true)
  bool isMascotEnabled() {
    return _box.get(_mascotEnabledKey, defaultValue: true) as bool;
  }

  /// Toggle mascot
  Future<void> toggleMascot() async {
    final currentValue = isMascotEnabled();
    await _box.put(_mascotEnabledKey, !currentValue);
  }

  /// Set mascot enabled state
  Future<void> setMascotEnabled(bool enabled) async {
    await _box.put(_mascotEnabledKey, enabled);
  }

  // Bookmark Settings (for Practice Mode)
  /// Get bookmarked flag IDs
  Set<String> getBookmarkedFlags() {
    final List<dynamic>? bookmarked = _box.get(_bookmarkedFlagsKey) as List<dynamic>?;
    if (bookmarked == null) return {};
    return Set<String>.from(bookmarked);
  }

  /// Toggle flag bookmark
  Future<void> toggleFlagBookmark(String flagId) async {
    final bookmarked = getBookmarkedFlags();
    if (bookmarked.contains(flagId)) {
      bookmarked.remove(flagId);
    } else {
      bookmarked.add(flagId);
    }
    await _box.put(_bookmarkedFlagsKey, bookmarked.toList());
  }

  /// Check if flag is bookmarked
  bool isFlagBookmarked(String flagId) {
    return getBookmarkedFlags().contains(flagId);
  }

  /// Clear all bookmarks
  Future<void> clearAllBookmarks() async {
    await _box.delete(_bookmarkedFlagsKey);
  }

  // General
  /// Get all settings as a map
  Map<String, dynamic> getAllSettings() {
    return {
      'language': getLanguage(),
      'musicEnabled': isMusicEnabled(),
      'sfxEnabled': isSfxEnabled(),
      'currentMusicTrack': getCurrentMusicTrack(),
      'currentStreak': getCurrentStreak(),
      'longestStreak': getLongestStreak(),
      'mascotEnabled': isMascotEnabled(),
    };
  }

  /// Reset all settings to defaults
  Future<void> resetAllSettings() async {
    await _box.clear();
  }
}
