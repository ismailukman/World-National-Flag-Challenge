import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../data/models/leaderboard_entry.dart';
import '../../../data/repositories/progress_repository.dart';
import '../../../data/repositories/settings_repository.dart';
import 'leaderboard_event.dart';
import 'leaderboard_state.dart';

/// BLoC for local leaderboard (simple in-memory for Phase 4)
/// In production, this would use Hive or a backend
class LeaderboardBloc extends Bloc<LeaderboardEvent, LeaderboardState> {
  final ProgressRepository progressRepository;
  final SettingsRepository settingsRepository;
  // Mock leaderboard data (in production, use Hive)
  final List<LeaderboardEntry> _entries = [];
  static const int maxEntries = 100;
  static const String defaultPlayerName = 'Player';

  LeaderboardBloc({
    required this.progressRepository,
    required this.settingsRepository,
  }) : super(const LeaderboardInitial()) {
    on<LoadLeaderboard>(_onLoadLeaderboard);
    on<RefreshLeaderboard>(_onRefreshLeaderboard);
    on<UpdatePlayerEntry>(_onUpdatePlayerEntry);

    // Initialize with some mock data
    _initializeMockData();
  }

  /// Initialize with mock data
  void _initializeMockData() {
    _entries.addAll([
      LeaderboardEntry(
        playerName: 'Flag Master',
        totalScore: 450,
        totalQuizzes: 15,
        flagsLearned: 120,
        highestQuizScore: 150,
        lastPlayedDate: DateTime.now().subtract(const Duration(days: 1)),
        currentStreak: 7,
      ),
      LeaderboardEntry(
        playerName: 'Geography Pro',
        totalScore: 380,
        totalQuizzes: 12,
        flagsLearned: 95,
        highestQuizScore: 135,
        lastPlayedDate: DateTime.now().subtract(const Duration(days: 2)),
        currentStreak: 5,
      ),
      LeaderboardEntry(
        playerName: 'World Explorer',
        totalScore: 320,
        totalQuizzes: 10,
        flagsLearned: 78,
        highestQuizScore: 120,
        lastPlayedDate: DateTime.now().subtract(const Duration(days: 3)),
        currentStreak: 3,
      ),
      LeaderboardEntry(
        playerName: defaultPlayerName,
        totalScore: 0,
        totalQuizzes: 0,
        flagsLearned: 0,
        highestQuizScore: 0,
        lastPlayedDate: DateTime.now(),
        currentStreak: 0,
      ),
    ]);
  }

  /// Load leaderboard
  Future<void> _onLoadLeaderboard(
    LoadLeaderboard event,
    Emitter<LeaderboardState> emit,
  ) async {
    try {
      emit(const LeaderboardLoading());

      await _syncPlayerEntry();

      // Sort by total score descending
      _entries.sort((a, b) => b.totalScore.compareTo(a.totalScore));

      // Find current player rank
      final playerIndex =
          _entries.indexWhere((e) => e.playerName == defaultPlayerName);
      final currentPlayerRank = playerIndex >= 0 ? playerIndex + 1 : null;

      emit(LeaderboardLoaded(
        entries: List.from(_entries),
        currentPlayerRank: currentPlayerRank,
      ));
    } catch (e) {
      emit(LeaderboardError('Failed to load leaderboard: $e'));
    }
  }

  /// Refresh leaderboard
  Future<void> _onRefreshLeaderboard(
    RefreshLeaderboard event,
    Emitter<LeaderboardState> emit,
  ) async {
    add(const LoadLeaderboard());
  }

  /// Update player entry
  Future<void> _onUpdatePlayerEntry(
    UpdatePlayerEntry event,
    Emitter<LeaderboardState> emit,
  ) async {
    try {
      // Find or create player entry
      var playerEntry =
          _entries.firstWhere(
            (e) => e.playerName == event.playerName,
            orElse: () => LeaderboardEntry(
              playerName: event.playerName,
              totalScore: 0,
              totalQuizzes: 0,
              flagsLearned: 0,
              highestQuizScore: 0,
              lastPlayedDate: DateTime.now(),
            ),
          );

      // Update entry
      final updatedEntry = LeaderboardEntry(
        playerName: event.playerName,
        totalScore: playerEntry.totalScore + event.quizScore,
        totalQuizzes: playerEntry.totalQuizzes + 1,
        flagsLearned: playerEntry.flagsLearned + event.flagsInQuiz,
        highestQuizScore: event.quizScore > playerEntry.highestQuizScore
            ? event.quizScore
            : playerEntry.highestQuizScore,
        lastPlayedDate: DateTime.now(),
        currentStreak: playerEntry.currentStreak,
      );

      // Remove old entry and add updated one
      _entries.removeWhere((e) => e.playerName == event.playerName);
      _entries.add(updatedEntry);

      // Keep only top entries
      if (_entries.length > maxEntries) {
        _entries.sort((a, b) => b.totalScore.compareTo(a.totalScore));
        _entries.removeRange(maxEntries, _entries.length);
      }

      // Reload leaderboard
      add(const LoadLeaderboard());
    } catch (e) {
      emit(LeaderboardError('Failed to update player entry: $e'));
    }
  }

  Future<void> _syncPlayerEntry() async {
    final progressList = await progressRepository.getAllProgress();
    final totalScore = progressList.fold<int>(
      0,
      (sum, progress) => sum + progress.highScore,
    );
    final totalQuizzes = progressList.fold<int>(
      0,
      (sum, progress) => sum + progress.totalQuizzesTaken,
    );
    final flagsLearned = progressList.fold<int>(
      0,
      (sum, progress) => sum + progress.learnedFlagIds.length,
    );
    final highestQuizScore = progressList.fold<int>(
      0,
      (maxScore, progress) =>
          progress.highScore > maxScore ? progress.highScore : maxScore,
    );
    final lastPlayedDate = progressList.fold<DateTime?>(
      null,
      (latest, progress) {
        final playedAt = progress.lastPlayedAt;
        if (playedAt == null) return latest;
        if (latest == null || playedAt.isAfter(latest)) {
          return playedAt;
        }
        return latest;
      },
    );

    final currentStreak = settingsRepository.getCurrentStreak();

    final playerEntry = LeaderboardEntry(
      playerName: defaultPlayerName,
      totalScore: totalScore,
      totalQuizzes: totalQuizzes,
      flagsLearned: flagsLearned,
      highestQuizScore: highestQuizScore,
      lastPlayedDate: lastPlayedDate ?? DateTime.now(),
      currentStreak: currentStreak,
    );

    _entries.removeWhere((e) => e.playerName == defaultPlayerName);
    _entries.add(playerEntry);
  }
}
