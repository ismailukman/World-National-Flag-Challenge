import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import '../../../core/constants/app_routes.dart';
import '../../../data/repositories/achievement_repository.dart';
import '../../blocs/achievement/achievement_bloc.dart';
import '../../blocs/achievement/achievement_event.dart';
import '../../blocs/achievement/achievement_state.dart';
import '../../widgets/achievements/achievement_card.dart';

/// Achievements Gallery Screen - displays all unlocked and locked achievements
class AchievementsScreen extends StatelessWidget {
  const AchievementsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => AchievementBloc(
        achievementRepository: context.read<AchievementRepository>(),
      )..add(const LoadAchievements()),
      child: const _AchievementsScreenContent(),
    );
  }
}

class _AchievementsScreenContent extends StatelessWidget {
  const _AchievementsScreenContent();

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    return Scaffold(
      appBar: AppBar(
        title: Text(l10n.achievements),
        centerTitle: true,
        elevation: 0,
        leading: IconButton(
          icon: const Icon(Icons.home),
          tooltip: 'Back to Menu',
          onPressed: () => context.go(AppRoutes.home),
        ),
      ),
      body: BlocBuilder<AchievementBloc, AchievementState>(
        builder: (context, state) {
          if (state is AchievementLoading) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }

          if (state is AchievementError) {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Icon(
                    Icons.error_outline,
                    size: 64,
                    color: Colors.red,
                  ),
                  const SizedBox(height: 16),
                  Text(
                    state.message,
                    style: const TextStyle(fontSize: 16),
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(height: 24),
                  ElevatedButton(
                    onPressed: () {
                      context.read<AchievementBloc>().add(
                            const LoadAchievements(),
                          );
                    },
                    child: const Text('Retry'),
                  ),
                ],
              ),
            );
          }

          if (state is AchievementsLoaded) {
            final content = Column(
              children: [
                // Progress header
                Container(
                  width: double.infinity,
                  padding: const EdgeInsets.all(16.0),
                  decoration: BoxDecoration(
                    gradient: LinearGradient(
                      colors: [
                        const Color(0xFF399DC5),
                        const Color(0xFF7AC4E1),
                      ],
                      begin: Alignment.topLeft,
                      end: Alignment.bottomRight,
                    ),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black.withOpacity(0.1),
                        blurRadius: 4,
                        offset: const Offset(0, 2),
                      ),
                    ],
                  ),
                  child: Column(
                    children: [
                      Text(
                        'Achievements Unlocked',
                        style: const TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                      const SizedBox(height: 8),
                      Text(
                        '${state.unlockedCount} / ${state.totalCount}',
                        style: const TextStyle(
                          fontSize: 32,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                      const SizedBox(height: 8),
                      LinearProgressIndicator(
                        value: state.totalCount > 0
                            ? state.unlockedCount / state.totalCount
                            : 0.0,
                        backgroundColor: Colors.white.withOpacity(0.3),
                        valueColor: const AlwaysStoppedAnimation<Color>(
                          Colors.amber,
                        ),
                        minHeight: 8,
                      ),
                    ],
                  ),
                ),

                // Achievements grid
                Expanded(
                  child: state.achievements.isEmpty
                      ? const Center(
                          child: Text(
                            'No achievements available',
                            style: TextStyle(fontSize: 16),
                          ),
                        )
                      : RefreshIndicator(
                          onRefresh: () async {
                            context.read<AchievementBloc>().add(
                                  const RefreshAchievements(),
                                );
                          },
                          child: Center(
                            child: ConstrainedBox(
                              constraints: const BoxConstraints(maxWidth: 960),
                              child: GridView.builder(
                                padding: const EdgeInsets.all(16.0),
                                gridDelegate:
                                    const SliverGridDelegateWithFixedCrossAxisCount(
                                  crossAxisCount: 2,
                                  childAspectRatio: 0.72,
                                  crossAxisSpacing: 16,
                                  mainAxisSpacing: 16,
                                ),
                                itemCount: state.achievements.length,
                                itemBuilder: (context, index) {
                                  final achievement = state.achievements[index];
                                  return AchievementCard(
                                    achievement: achievement,
                                  );
                                },
                              ),
                            ),
                          ),
                        ),
                ),
              ],
            );
            if (!kIsWeb) {
              return content;
            }
            return Center(
              child: ConstrainedBox(
                constraints: const BoxConstraints(maxWidth: 1000),
                child: content,
              ),
            );
          }

          // Initial state
          return const Center(
            child: CircularProgressIndicator(),
          );
        },
      ),
    );
  }
}
