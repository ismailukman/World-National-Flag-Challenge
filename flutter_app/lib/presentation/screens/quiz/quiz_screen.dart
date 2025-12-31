import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import 'package:confetti/confetti.dart';
import 'package:go_router/go_router.dart';
import '../../../core/constants/app_routes.dart';
import '../../../data/repositories/flag_repository.dart';
import '../../../data/repositories/progress_repository.dart';
import '../../../data/repositories/achievement_repository.dart';
import '../../../data/repositories/settings_repository.dart';
import '../../blocs/quiz/quiz_bloc.dart';
import '../../blocs/quiz/quiz_event.dart';
import '../../blocs/quiz/quiz_state.dart';
import '../../blocs/audio/audio_bloc.dart';
import '../../blocs/audio/audio_event.dart';
import '../../widgets/quiz/flag_display.dart';
import '../../widgets/quiz/quiz_option_button.dart';
import '../../widgets/quiz/score_header.dart';
import '../../widgets/mascot/mascot_character.dart';

/// Unified quiz screen for all continents (replaces 6 Kotlin activities)
class QuizScreen extends StatelessWidget {
  final String continentId;

  const QuizScreen({
    super.key,
    required this.continentId,
  });

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => QuizBloc(
        flagRepository: context.read<FlagRepository>(),
        progressRepository: context.read<ProgressRepository>(),
        achievementRepository: context.read<AchievementRepository>(),
      )..add(StartQuiz(continentId)),
      child: const _QuizScreenContent(),
    );
  }
}

class _QuizScreenContent extends StatefulWidget {
  const _QuizScreenContent();

  @override
  State<_QuizScreenContent> createState() => _QuizScreenContentState();
}

class _QuizScreenContentState extends State<_QuizScreenContent> {
  late ConfettiController _confettiController;

  @override
  void initState() {
    super.initState();
    _confettiController = ConfettiController(duration: const Duration(seconds: 3));
  }

  @override
  void dispose() {
    _confettiController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    return Scaffold(
      appBar: AppBar(
        title: Text(l10n.quiz),
        centerTitle: true,
        elevation: 0,
        leading: IconButton(
          icon: const Icon(Icons.home),
          tooltip: 'Back to Menu',
          onPressed: () {
            // Show confirmation dialog before leaving quiz
            showDialog(
              context: context,
              builder: (dialogContext) => AlertDialog(
                title: const Text('Exit Quiz?'),
                content: const Text(
                  'Your progress will be lost. Are you sure you want to return to the main menu?',
                ),
                actions: [
                  TextButton(
                    onPressed: () => Navigator.of(dialogContext).pop(),
                    child: const Text('Cancel'),
                  ),
                  TextButton(
                    onPressed: () {
                      Navigator.of(dialogContext).pop();
                      context.go(AppRoutes.home);
                    },
                    child: const Text('Exit'),
                    style: TextButton.styleFrom(
                      foregroundColor: Colors.red,
                    ),
                  ),
                ],
              ),
            );
          },
        ),
      ),
      body: Stack(
        children: [
          BlocConsumer<QuizBloc, QuizState>(
            listener: (context, state) {
              // Play sound effects based on quiz state
              if (state is QuizInProgress) {
                if (state.wasLastAnswerCorrect == true) {
                  context.read<AudioBloc>().add(const PlaySoundEffect('correct'));
                } else if (state.wasLastAnswerCorrect == false) {
                  context.read<AudioBloc>().add(const PlaySoundEffect('wrong'));
                }
              }

              // Play completion sound and trigger confetti
              if (state is QuizCompleted) {
                context.read<AudioBloc>().add(const PlaySoundEffect('complete'));
                _confettiController.play();

                // Play achievement sound if achievements unlocked
                if (state.newlyUnlockedAchievements != null &&
                    state.newlyUnlockedAchievements!.isNotEmpty) {
                  Future.delayed(const Duration(milliseconds: 500), () {
                    if (mounted) {
                      context.read<AudioBloc>()
                          .add(const PlaySoundEffect('achievement'));
                    }
                  });
                }
              }

              // Navigate to result screen when quiz is completed
              if (state is QuizCompleted) {
                // TODO: Navigate to QuizResultScreen in Phase 2
                // For now, show a dialog
                _showCompletionDialog(context, state);
              }

              // Show error dialog
              if (state is QuizError) {
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(
                    content: Text(state.message),
                    backgroundColor: Colors.red,
                  ),
                );
              }
            },
            builder: (context, state) {
              if (state is QuizLoading) {
                return const Center(
                  child: CircularProgressIndicator(),
                );
              }

              if (state is QuizInProgress) {
                return _buildQuizContent(context, state);
              }

              if (state is QuizError) {
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
                    onPressed: () => context.go(AppRoutes.home),
                    child: Text(l10n.goBack),
                  ),
                    ],
                  ),
                );
              }

              // Initial state
              return const Center(
                child: CircularProgressIndicator(),
              );
            },
          ),

          _buildMascot(context),

          // Confetti overlay
          _buildConfetti(),
        ],
      ),
    );
  }

  Widget _buildQuizContent(BuildContext context, QuizInProgress state) {
    final l10n = AppLocalizations.of(context)!;
    final currentQuestion = state.currentQuestion;

    return Column(
      children: [
        // Score header
        ScoreHeader(
          correctCount: state.correctCount,
          wrongCount: state.wrongCount,
          currentScore: state.correctCount * 3 - state.wrongCount,
        ),

        // Progress indicator
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 8.0),
          child: Row(
            children: [
              Expanded(
                child: LinearProgressIndicator(
                  value: (state.currentIndex + 1) / state.totalQuestions,
                  backgroundColor: Colors.grey[300],
                  valueColor: const AlwaysStoppedAnimation<Color>(
                    Color(0xFF399DC5),
                  ),
                  minHeight: 8,
                ),
              ),
              const SizedBox(width: 12),
              Text(
                '${state.currentIndex + 1}/${state.totalQuestions}',
                style: const TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
        ),

        const SizedBox(height: 24),

        // Flag display
        Expanded(
          child: SingleChildScrollView(
            child: Column(
              children: [
                // Question prompt
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 16.0),
                  child: Text(
                    l10n.whichCountryFlag,
                    style: Theme.of(context).textTheme.headlineSmall?.copyWith(
                          fontWeight: FontWeight.bold,
                          color: const Color(0xFF0B5761),
                        ),
                    textAlign: TextAlign.center,
                  ),
                ),

                const SizedBox(height: 24),

                // Flag image
                FlagDisplay(
                  flagImagePath: currentQuestion.correctFlag.flagImagePath,
                ),

                const SizedBox(height: 32),

                // Answer options (4 buttons)
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 16.0),
                  child: Column(
                    children: currentQuestion.displayOptions
                        .asMap()
                        .entries
                        .map((entry) {
                      final option = entry.value;
                      final optionIndex = entry.key;

                      // Determine button state
                      QuizButtonState buttonState = QuizButtonState.normal;

                      if (state.showingFeedback) {
                        if (option.id == currentQuestion.correctFlag.id) {
                          buttonState = QuizButtonState.correct;
                        } else if (currentQuestion.userAnswer ==
                            option.countryName) {
                          buttonState = QuizButtonState.wrong;
                        }
                      }

                      return Padding(
                        padding: const EdgeInsets.only(bottom: 12.0),
                        child: QuizOptionButton(
                          text: option.getLocalizedName(
                            Localizations.localeOf(context).languageCode,
                          ),
                          state: buttonState,
                          onPressed: state.showingFeedback
                              ? null
                              : () {
                                  context.read<QuizBloc>().add(
                                        AnswerQuestion(option.countryName),
                                      );

                                  // Auto-advance to next question after 1.5 seconds
                                  Future.delayed(
                                    const Duration(milliseconds: 1500),
                                    () {
                                      if (context.mounted) {
                                        context.read<QuizBloc>().add(
                                              const NextQuestion(),
                                            );
                                      }
                                    },
                                  );
                                },
                        ),
                      );
                    }).toList(),
                  ),
                ),

                const SizedBox(height: 24),

                // Navigation buttons
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 16.0),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      // Previous button
                      OutlinedButton.icon(
                        onPressed: state.currentIndex > 0 && !state.showingFeedback
                            ? () {
                                // Navigate to previous question
                                // Note: This would require adding a PreviousQuestion event to quiz_bloc
                                ScaffoldMessenger.of(context).showSnackBar(
                                  const SnackBar(
                                    content: Text('Cannot go back'),
                                    duration: Duration(seconds: 1),
                                  ),
                                );
                              }
                            : null,
                        icon: const Icon(Icons.arrow_back),
                        label: const Text('Previous'),
                        style: OutlinedButton.styleFrom(
                          padding: const EdgeInsets.symmetric(
                            horizontal: 16,
                            vertical: 12,
                          ),
                        ),
                      ),

                      // Question counter
                      Container(
                        padding: const EdgeInsets.symmetric(
                          horizontal: 16,
                          vertical: 8,
                        ),
                        decoration: BoxDecoration(
                          color: const Color(0xFF399DC5).withOpacity(0.1),
                          borderRadius: BorderRadius.circular(20),
                        ),
                        child: Text(
                          '${state.currentIndex + 1} / ${state.totalQuestions}',
                          style: const TextStyle(
                            fontSize: 14,
                            fontWeight: FontWeight.bold,
                            color: Color(0xFF0B5761),
                          ),
                        ),
                      ),

                      // Next/Skip button
                      ElevatedButton(
                        onPressed: !state.showingFeedback
                            ? () {
                                // Skip current question
                                context.read<QuizBloc>().add(
                                      const NextQuestion(),
                                    );
                              }
                            : null,
                        child: const Row(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            Text(
                              'Next',
                              style: TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.w600,
                              ),
                            ),
                            SizedBox(width: 8),
                            Icon(Icons.arrow_forward, color: Colors.white),
                          ],
                        ),
                        style: ElevatedButton.styleFrom(
                          padding: const EdgeInsets.symmetric(
                            horizontal: 16,
                            vertical: 12,
                          ),
                          backgroundColor: const Color(0xFF399DC5),
                          foregroundColor: Colors.white,
                          disabledBackgroundColor:
                              const Color(0xFF399DC5).withOpacity(0.5),
                          disabledForegroundColor: Colors.white70,
                        ),
                      ),
                    ],
                  ),
                ),

                const SizedBox(height: 80),
              ],
            ),
          ),
        ),
      ],
    );
  }

  Widget _buildConfetti() {
    return Align(
      alignment: Alignment.topCenter,
      child: ConfettiWidget(
        confettiController: _confettiController,
        blastDirectionality: BlastDirectionality.explosive,
        shouldLoop: false,
        colors: const [
          Colors.green,
          Colors.blue,
          Colors.pink,
          Colors.orange,
          Colors.purple,
          Colors.red,
          Colors.yellow,
        ],
        numberOfParticles: 50,
        gravity: 0.3,
      ),
    );
  }

  Widget _buildMascot(BuildContext context) {
    final settingsRepo = context.read<SettingsRepository>();
    if (!kIsWeb && !settingsRepo.isMascotEnabled()) {
      return const SizedBox.shrink();
    }

    return BlocBuilder<QuizBloc, QuizState>(
      builder: (context, state) {
        final l10n = AppLocalizations.of(context)!;
        MascotState mascotState = MascotState.idle;
        String? message;

        if (state is QuizLoading) {
          mascotState = MascotState.thinking;
        } else if (state is QuizInProgress) {
          if (state.wasLastAnswerCorrect == true) {
            mascotState = MascotState.happy;
            message = l10n.mascot_correct;
          } else if (state.wasLastAnswerCorrect == false) {
            mascotState = MascotState.encourage;
            message = l10n.mascot_wrong;
          }
        } else if (state is QuizCompleted) {
          mascotState = MascotState.celebrate;
          message = l10n.mascot_quiz_complete(state.correctCount);
        }

        return MascotCharacter(
          state: mascotState,
          message: message,
        );
      },
    );
  }

  void _showCompletionDialog(BuildContext context, QuizCompleted state) {
    final l10n = AppLocalizations.of(context)!;

    showDialog(
      context: context,
      barrierDismissible: false,
      builder: (dialogContext) => AlertDialog(
        title: Text(
          state.isHighScore ? l10n.new_high_score : l10n.quizComplete,
          textAlign: TextAlign.center,
        ),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            if (state.isHighScore)
              const Icon(
                Icons.emoji_events,
                size: 64,
                color: Colors.amber,
              ),
            const SizedBox(height: 16),
            Text(
              '${l10n.finalScore}: ${state.finalScore}',
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 12),
            Text('${l10n.correct}: ${state.correctCount}'),
            Text('${l10n.wrong}: ${state.wrongCount}'),
            if (state.newlyUnlockedAchievements != null &&
                state.newlyUnlockedAchievements!.isNotEmpty) ...[
              const SizedBox(height: 16),
              const Divider(),
              const SizedBox(height: 8),
              Text(
                l10n.achievementsUnlocked,
                style: const TextStyle(fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 8),
              ...state.newlyUnlockedAchievements!.map((achievement) {
                return Padding(
                  padding: const EdgeInsets.symmetric(vertical: 4.0),
                  child: Row(
                    children: [
                      const Icon(Icons.star, color: Colors.amber, size: 20),
                      const SizedBox(width: 8),
                      Expanded(
                        child: Text(
                          _getAchievementTitle(l10n, achievement.nameKey),
                          style: const TextStyle(fontSize: 14),
                        ),
                      ),
                    ],
                  ),
                );
              }).toList(),
            ],
          ],
        ),
        actions: [
          TextButton(
            onPressed: () {
              Navigator.of(dialogContext).pop();
              context.go(AppRoutes.home);
            },
            child: Text(l10n.goBack),
          ),
          ElevatedButton(
            onPressed: () {
              Navigator.of(dialogContext).pop();
              context.read<QuizBloc>().add(const RestartQuiz());
            },
            child: Text(l10n.playAgain),
          ),
        ],
      ),
    );
  }

  String _getAchievementTitle(AppLocalizations l10n, String nameKey) {
    switch (nameKey) {
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
        return nameKey;
    }
  }
}
