import 'package:flutter/material.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import 'package:intl/intl.dart';
import '../../../data/models/achievement.dart';

/// Achievement card widget displaying badge with unlock status
class AchievementCard extends StatelessWidget {
  final Achievement achievement;

  const AchievementCard({
    super.key,
    required this.achievement,
  });

  @override
  Widget build(BuildContext context) {
    final isUnlocked = achievement.isUnlocked;
    final l10n = AppLocalizations.of(context)!;

    return Card(
      elevation: isUnlocked ? 8 : 2,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12),
      ),
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(12),
          gradient: isUnlocked
              ? LinearGradient(
                  colors: [
                    Colors.amber[300]!,
                    Colors.amber[600]!,
                  ],
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                )
              : null,
          color: isUnlocked ? null : Colors.grey[300],
        ),
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              // Badge icon
              Container(
                width: 70,
                height: 70,
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  color: isUnlocked ? Colors.white : Colors.grey[400],
                  boxShadow: isUnlocked
                      ? [
                          BoxShadow(
                            color: Colors.black.withOpacity(0.2),
                            blurRadius: 8,
                            offset: const Offset(0, 4),
                          ),
                        ]
                      : null,
                ),
                child: Icon(
                  _getAchievementIcon(achievement.type),
                  size: 40,
                  color: isUnlocked ? Colors.amber[700] : Colors.grey[600],
                ),
              ),

              const SizedBox(height: 8),

              // Title
              Text(
                _getAchievementTitle(l10n),
                style: TextStyle(
                  fontSize: 13,
                  fontWeight: FontWeight.bold,
                  color: isUnlocked ? Colors.white : Colors.grey[800],
                ),
                textAlign: TextAlign.center,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
              ),

              const SizedBox(height: 3),

              // Description
              Text(
                _getAchievementDescription(l10n),
                style: TextStyle(
                  fontSize: 10,
                  color: isUnlocked ? Colors.white70 : Colors.grey[600],
                ),
                textAlign: TextAlign.center,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
              ),

              const SizedBox(height: 6),

              // Progress or unlock date
              if (isUnlocked)
                Text(
                  _formatUnlockDate(achievement.unlockedAt!),
                  style: const TextStyle(
                    fontSize: 10,
                    color: Colors.white60,
                    fontStyle: FontStyle.italic,
                  ),
                  textAlign: TextAlign.center,
                )
              else
                LinearProgressIndicator(
                  value: achievement.currentProgress / achievement.targetValue,
                  backgroundColor: Colors.grey[400],
                  valueColor: AlwaysStoppedAnimation<Color>(
                    const Color(0xFF399DC5),
                  ),
                  minHeight: 4,
                ),
            ],
          ),
        ),
      ),
    );
  }

  /// Get icon for achievement type
  IconData _getAchievementIcon(AchievementType type) {
    switch (type) {
      case AchievementType.firstQuiz:
        return Icons.flag;
      case AchievementType.explorer:
        return Icons.public;
      case AchievementType.continentMaster:
        return Icons.workspace_premium;
      case AchievementType.perfectionist:
        return Icons.stars;
      case AchievementType.streakWarrior:
        return Icons.event_repeat;
      case AchievementType.speedster:
        return Icons.bolt;
      case AchievementType.dedicated:
        return Icons.school;
      case AchievementType.practiceExpert:
        return Icons.menu_book;
      case AchievementType.dailyChampion:
        return Icons.emoji_events;
    }
  }

  String _getAchievementTitle(AppLocalizations l10n) {
    switch (achievement.nameKey) {
      case 'achievement_first_quiz':
        return l10n.achievement_first_quiz;
      case 'achievement_global_explorer':
        return l10n.achievement_global_explorer;
      case 'achievement_africa_expert':
        return l10n.achievement_africa_expert;
      case 'achievement_perfectionist':
        return l10n.achievement_perfectionist;
      case 'achievement_week_warrior':
        return l10n.achievement_week_warrior;
      case 'achievement_fortnight_fighter':
        return l10n.achievement_fortnight_fighter;
      case 'achievement_monthly_master':
        return l10n.achievement_monthly_master;
      case 'achievement_speedster':
        return l10n.achievement_speedster;
      case 'achievement_dedicated_learner':
        return l10n.achievement_dedicated_learner;
      case 'achievement_flag_fanatic':
        return l10n.achievement_flag_fanatic;
      default:
        return achievement.nameKey;
    }
  }

  String _getAchievementDescription(AppLocalizations l10n) {
    switch (achievement.descriptionKey) {
      case 'achievement_first_quiz_desc':
        return l10n.achievement_first_quiz_desc;
      case 'achievement_global_explorer_desc':
        return l10n.achievement_global_explorer_desc;
      case 'achievement_africa_expert_desc':
        return l10n.achievement_africa_expert_desc;
      case 'achievement_perfectionist_desc':
        return l10n.achievement_perfectionist_desc;
      case 'achievement_week_warrior_desc':
        return l10n.achievement_week_warrior_desc;
      case 'achievement_fortnight_fighter_desc':
        return l10n.achievement_fortnight_fighter_desc;
      case 'achievement_monthly_master_desc':
        return l10n.achievement_monthly_master_desc;
      case 'achievement_speedster_desc':
        return l10n.achievement_speedster_desc;
      case 'achievement_dedicated_learner_desc':
        return l10n.achievement_dedicated_learner_desc;
      case 'achievement_flag_fanatic_desc':
        return l10n.achievement_flag_fanatic_desc;
      default:
        return achievement.descriptionKey;
    }
  }

  /// Format unlock date
  String _formatUnlockDate(DateTime date) {
    final now = DateTime.now();
    final difference = now.difference(date);

    if (difference.inDays == 0) {
      return 'Unlocked today';
    } else if (difference.inDays == 1) {
      return 'Unlocked yesterday';
    } else if (difference.inDays < 7) {
      return 'Unlocked ${difference.inDays} days ago';
    } else {
      return 'Unlocked ${DateFormat('MMM d, y').format(date)}';
    }
  }
}
