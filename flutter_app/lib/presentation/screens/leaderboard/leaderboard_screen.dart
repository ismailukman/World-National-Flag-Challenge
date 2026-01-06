import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import 'package:intl/intl.dart';
import 'package:go_router/go_router.dart';
import '../../../core/constants/app_routes.dart';
import '../../../data/repositories/progress_repository.dart';
import '../../../data/repositories/settings_repository.dart';
import '../../blocs/leaderboard/leaderboard_bloc.dart';
import '../../blocs/leaderboard/leaderboard_event.dart';
import '../../blocs/leaderboard/leaderboard_state.dart';

/// Leaderboard Screen - displays local high scores
class LeaderboardScreen extends StatelessWidget {
  const LeaderboardScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => LeaderboardBloc(
        progressRepository: context.read<ProgressRepository>(),
        settingsRepository: context.read<SettingsRepository>(),
      )..add(const LoadLeaderboard()),
      child: const _LeaderboardScreenContent(),
    );
  }
}

class _LeaderboardScreenContent extends StatelessWidget {
  const _LeaderboardScreenContent();

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    return Scaffold(
      appBar: AppBar(
        title: Text(l10n.leaderboard),
        centerTitle: true,
        elevation: 0,
        leading: IconButton(
          icon: const Icon(Icons.home),
          tooltip: 'Back to Menu',
          onPressed: () => context.go(AppRoutes.home),
        ),
      ),
      body: BlocBuilder<LeaderboardBloc, LeaderboardState>(
        builder: (context, state) {
          if (state is LeaderboardLoading) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }

          if (state is LeaderboardError) {
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
                      context.read<LeaderboardBloc>().add(
                            const LoadLeaderboard(),
                          );
                    },
                    child: const Text('Retry'),
                  ),
                ],
              ),
            );
          }

          if (state is LeaderboardLoaded) {
            if (state.entries.isEmpty) {
              return const Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(
                      Icons.emoji_events_outlined,
                      size: 64,
                      color: Colors.grey,
                    ),
                    SizedBox(height: 16),
                    Text(
                      'No entries yet',
                      style: TextStyle(fontSize: 16, color: Colors.grey),
                    ),
                    SizedBox(height: 8),
                    Text(
                      'Complete quizzes to appear on the leaderboard!',
                      style: TextStyle(fontSize: 14, color: Colors.grey),
                      textAlign: TextAlign.center,
                    ),
                  ],
                ),
              );
            }

            return Column(
              children: [
                // Header with current player rank
                if (state.currentPlayerRank != null)
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
                        const Text(
                          'Your Rank',
                          style: TextStyle(
                            fontSize: 14,
                            color: Colors.white70,
                          ),
                        ),
                        const SizedBox(height: 4),
                        Text(
                          _getRankBadge(state.currentPlayerRank!),
                          style: const TextStyle(
                            fontSize: 32,
                            fontWeight: FontWeight.bold,
                            color: Colors.white,
                          ),
                        ),
                      ],
                    ),
                  ),

                // Leaderboard list
                Expanded(
                  child: RefreshIndicator(
                    onRefresh: () async {
                      context.read<LeaderboardBloc>().add(
                            const RefreshLeaderboard(),
                          );
                    },
                    child: ListView.builder(
                      padding: const EdgeInsets.all(16.0),
                      itemCount: state.entries.length,
                      itemBuilder: (context, index) {
                        final entry = state.entries[index];
                        final rank = index + 1;
                        final isCurrentPlayer = rank == state.currentPlayerRank;

                        return _buildLeaderboardCard(
                          entry: entry,
                          rank: rank,
                          isCurrentPlayer: isCurrentPlayer,
                        );
                      },
                    ),
                  ),
                ),
              ],
            );
          }

          return const Center(
            child: CircularProgressIndicator(),
          );
        },
      ),
    );
  }

  Widget _buildLeaderboardCard({
    required dynamic entry,
    required int rank,
    required bool isCurrentPlayer,
  }) {
    Color rankColor;
    if (rank == 1) {
      rankColor = Colors.amber;
    } else if (rank == 2) {
      rankColor = Colors.grey[400]!;
    } else if (rank == 3) {
      rankColor = Colors.brown[300]!;
    } else {
      rankColor = Colors.grey[300]!;
    }

    return Card(
      margin: const EdgeInsets.only(bottom: 12.0),
      elevation: isCurrentPlayer ? 6 : 2,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12),
        side: isCurrentPlayer
            ? const BorderSide(color: Color(0xFF399DC5), width: 2)
            : BorderSide.none,
      ),
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Row(
          children: [
            // Rank badge
            Container(
              width: 50,
              height: 50,
              decoration: BoxDecoration(
                color: rankColor,
                shape: BoxShape.circle,
              ),
              child: Center(
                child: Text(
                  _getRankBadge(rank),
                  style: TextStyle(
                    fontSize: rank <= 3 ? 24 : 18,
                    fontWeight: FontWeight.bold,
                    color: rank <= 3 ? Colors.white : Colors.black87,
                  ),
                ),
              ),
            ),

            const SizedBox(width: 16),

            // Player info
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      Expanded(
                        child: Text(
                          entry.playerName,
                          style: TextStyle(
                            fontSize: 16,
                            fontWeight: FontWeight.bold,
                            color: isCurrentPlayer
                                ? const Color(0xFF399DC5)
                                : Colors.black87,
                          ),
                          overflow: TextOverflow.ellipsis,
                        ),
                      ),
                      if (isCurrentPlayer)
                        Container(
                          padding: const EdgeInsets.symmetric(
                            horizontal: 8,
                            vertical: 4,
                          ),
                          decoration: BoxDecoration(
                            color: const Color(0xFF399DC5),
                            borderRadius: BorderRadius.circular(12),
                          ),
                          child: const Text(
                            'YOU',
                            style: TextStyle(
                              fontSize: 10,
                              fontWeight: FontWeight.bold,
                              color: Colors.white,
                            ),
                          ),
                        ),
                    ],
                  ),
                  const SizedBox(height: 4),
                  Row(
                    children: [
                      Icon(Icons.emoji_events, size: 14, color: Colors.grey[600]),
                      const SizedBox(width: 4),
                      Text(
                        '${entry.totalScore} pts',
                        style: TextStyle(
                          fontSize: 14,
                          color: Colors.grey[600],
                        ),
                      ),
                      const SizedBox(width: 16),
                      Icon(Icons.quiz, size: 14, color: Colors.grey[600]),
                      const SizedBox(width: 4),
                      Text(
                        '${entry.totalQuizzes} quizzes',
                        style: TextStyle(
                          fontSize: 14,
                          color: Colors.grey[600]),
                      ),
                    ],
                  ),
                  const SizedBox(height: 2),
                  Row(
                    children: [
                      Icon(Icons.flag, size: 14, color: Colors.grey[600]),
                      const SizedBox(width: 4),
                      Text(
                        '${entry.flagsLearned} flags',
                        style: TextStyle(
                          fontSize: 12,
                          color: Colors.grey[500],
                        ),
                      ),
                      if (entry.currentStreak > 0) ...[
                        const SizedBox(width: 12),
                        Icon(Icons.local_fire_department,
                            size: 14, color: Colors.orange[600]),
                        const SizedBox(width: 4),
                        Text(
                          '${entry.currentStreak} day streak',
                          style: TextStyle(
                            fontSize: 12,
                            color: Colors.orange[600],
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                      ],
                    ],
                  ),
                ],
              ),
            ),

            // Highest score
            Column(
              crossAxisAlignment: CrossAxisAlignment.end,
              children: [
                const Text(
                  'Best',
                  style: TextStyle(
                    fontSize: 10,
                    color: Colors.grey,
                  ),
                ),
                Text(
                  '${entry.highestQuizScore}',
                  style: const TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    color: Color(0xFF399DC5),
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  String _getRankBadge(int rank) {
    switch (rank) {
      case 1:
        return '🥇';
      case 2:
        return '🥈';
      case 3:
        return '🥉';
      default:
        return '#$rank';
    }
  }
}
