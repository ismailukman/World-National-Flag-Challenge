import 'package:equatable/equatable.dart';
import '../../../data/models/achievement.dart';

/// States for achievement management
abstract class AchievementState extends Equatable {
  const AchievementState();

  @override
  List<Object?> get props => [];
}

/// Initial state
class AchievementInitial extends AchievementState {
  const AchievementInitial();
}

/// Loading achievements
class AchievementLoading extends AchievementState {
  const AchievementLoading();
}

/// Achievements loaded successfully
class AchievementsLoaded extends AchievementState {
  final List<Achievement> achievements;
  final int unlockedCount;
  final int totalCount;

  const AchievementsLoaded({
    required this.achievements,
    required this.unlockedCount,
    required this.totalCount,
  });

  @override
  List<Object?> get props => [achievements, unlockedCount, totalCount];

  AchievementsLoaded copyWith({
    List<Achievement>? achievements,
    int? unlockedCount,
    int? totalCount,
  }) {
    return AchievementsLoaded(
      achievements: achievements ?? this.achievements,
      unlockedCount: unlockedCount ?? this.unlockedCount,
      totalCount: totalCount ?? this.totalCount,
    );
  }
}

/// Error loading achievements
class AchievementError extends AchievementState {
  final String message;

  const AchievementError(this.message);

  @override
  List<Object?> get props => [message];
}
