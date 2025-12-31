import 'package:equatable/equatable.dart';

/// Events for achievement management
abstract class AchievementEvent extends Equatable {
  const AchievementEvent();

  @override
  List<Object?> get props => [];
}

/// Load all achievements
class LoadAchievements extends AchievementEvent {
  const LoadAchievements();
}

/// Refresh achievements (check for new unlocks)
class RefreshAchievements extends AchievementEvent {
  const RefreshAchievements();
}

/// Mark achievement as seen (dismiss notification)
class MarkAchievementSeen extends AchievementEvent {
  final String achievementId;

  const MarkAchievementSeen(this.achievementId);

  @override
  List<Object?> get props => [achievementId];
}
