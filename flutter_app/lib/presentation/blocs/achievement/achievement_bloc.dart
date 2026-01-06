import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../data/repositories/achievement_repository.dart';
import 'achievement_event.dart';
import 'achievement_state.dart';

/// BLoC for managing achievements display and tracking
class AchievementBloc extends Bloc<AchievementEvent, AchievementState> {
  final AchievementRepository achievementRepository;

  AchievementBloc({
    required this.achievementRepository,
  }) : super(const AchievementInitial()) {
    on<LoadAchievements>(_onLoadAchievements);
    on<RefreshAchievements>(_onRefreshAchievements);
    on<MarkAchievementSeen>(_onMarkAchievementSeen);
  }

  /// Load all achievements from repository
  Future<void> _onLoadAchievements(
    LoadAchievements event,
    Emitter<AchievementState> emit,
  ) async {
    try {
      emit(const AchievementLoading());

      final achievements = await achievementRepository.getAllAchievements();

      final unlockedCount =
          achievements.where((a) => a.isUnlocked).length;
      final totalCount = achievements.length;

      emit(AchievementsLoaded(
        achievements: achievements,
        unlockedCount: unlockedCount,
        totalCount: totalCount,
      ));
    } catch (e) {
      emit(AchievementError('Failed to load achievements: $e'));
    }
  }

  /// Refresh achievements (useful after quiz completion)
  Future<void> _onRefreshAchievements(
    RefreshAchievements event,
    Emitter<AchievementState> emit,
  ) async {
    try {
      // Don't show loading state for refresh
      final achievements = await achievementRepository.getAllAchievements();

      final unlockedCount =
          achievements.where((a) => a.isUnlocked).length;
      final totalCount = achievements.length;

      emit(AchievementsLoaded(
        achievements: achievements,
        unlockedCount: unlockedCount,
        totalCount: totalCount,
      ));
    } catch (e) {
      emit(AchievementError('Failed to refresh achievements: $e'));
    }
  }

  /// Mark achievement as seen (future feature for notifications)
  Future<void> _onMarkAchievementSeen(
    MarkAchievementSeen event,
    Emitter<AchievementState> emit,
  ) async {
    // Placeholder for future notification system
    // Could update a "lastSeen" timestamp in the achievement
  }
}
