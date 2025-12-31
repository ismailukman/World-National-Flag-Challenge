import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import 'package:confetti/confetti.dart';
import 'package:go_router/go_router.dart';
import '../../../core/constants/app_routes.dart';
import '../../../data/repositories/flag_repository.dart';
import '../../../data/repositories/daily_challenge_repository.dart';
import '../../../data/repositories/settings_repository.dart';
import '../../blocs/daily_challenge/daily_challenge_bloc.dart';
import '../../blocs/daily_challenge/daily_challenge_event.dart';
import '../../blocs/daily_challenge/daily_challenge_state.dart';
import '../../blocs/audio/audio_bloc.dart';
import '../../blocs/audio/audio_event.dart';
import '../../widgets/quiz/flag_display.dart';
import '../../widgets/quiz/quiz_option_button.dart';
import '../../widgets/quiz/score_header.dart';
import '../../widgets/mascot/mascot_character.dart';

/// Daily Challenge Screen - special quiz with timer and bonus points
class DailyChallengeScreen extends StatelessWidget {
  const DailyChallengeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => DailyChallengeBloc(
        flagRepository: context.read<FlagRepository>(),
        dailyChallengeRepository: context.read<DailyChallengeRepository>(),
      )..add(const LoadDailyChallenge()),
      child: const _DailyChallengeScreenContent(),
    );
  }
}

class _DailyChallengeScreenContent extends StatefulWidget {
  const _DailyChallengeScreenContent();

  @override
  State<_DailyChallengeScreenContent> createState() =>
      _DailyChallengeScreenContentState();
}

class _DailyChallengeScreenContentState
    extends State<_DailyChallengeScreenContent> {
  Timer? _timer;
  int _elapsedSeconds = 0;
  late ConfettiController _confettiController;

  @override
  void initState() {
    super.initState();
    _confettiController = ConfettiController(duration: const Duration(seconds: 3));
  }

  @override
  void dispose() {
    _timer?.cancel();
    _confettiController.dispose();
    super.dispose();
  }

  void _startTimer() {
    _timer?.cancel();
    _elapsedSeconds = 0;
    _timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      setState(() {
        _elapsedSeconds++;
      });
    });
  }

  void _stopTimer() {
    _timer?.cancel();
  }

  String _formatTime(int seconds) {
    final minutes = seconds ~/ 60;
    final secs = seconds % 60;
    return '${minutes.toString().padLeft(2, '0')}:${secs.toString().padLeft(2, '0')}';
  }

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    return Scaffold(
      appBar: AppBar(
        title: Text(l10n.daily_challenge),
        centerTitle: true,
        elevation: 0,
        leading: IconButton(
          icon: const Icon(Icons.home),
          tooltip: 'Back to Menu',
          onPressed: () => context.go(AppRoutes.home),
        ),
      ),
      body: Stack(
        children: [
          BlocConsumer<DailyChallengeBloc, DailyChallengeState>(
            listener: (context, state) {
              // Start timer
              if (state is DailyChallengeInProgress) {
                if (_timer == null || !_timer!.isActive) {
                  _startTimer();
                }

                // Play sound effects based on answer
                if (state.wasLastAnswerCorrect == true) {
                  context.read<AudioBloc>().add(const PlaySoundEffect('correct'));
                } else if (state.wasLastAnswerCorrect == false) {
                  context.read<AudioBloc>().add(const PlaySoundEffect('wrong'));
                }
              }

              // Handle completion
              if (state is DailyChallengeCompleted) {
                _stopTimer();
                context.read<AudioBloc>().add(const PlaySoundEffect('complete'));
                _confettiController.play();
                _showCompletionDialog(context, state);
              }

              // Handle errors
              if (state is DailyChallengeError) {
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(
                    content: Text(state.message),
                    backgroundColor: Colors.red,
                  ),
                );
              }
            },
            builder: (context, state) {
              if (state is DailyChallengeLoading) {
                return const Center(
                  child: CircularProgressIndicator(),
                );
              }

              if (state is DailyChallengeReady) {
                return _buildReadyScreen(context, state);
              }

              if (state is DailyChallengeInProgress) {
                return _buildChallengeContent(context, state);
              }

              if (state is DailyChallengeError) {
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
          Colors.amber,
        ],
        numberOfParticles: 60,
        gravity: 0.3,
      ),
    );
  }

  Widget _buildMascot(BuildContext context) {
    final settingsRepo = context.read<SettingsRepository>();
    if (!kIsWeb && !settingsRepo.isMascotEnabled()) {
      return const SizedBox.shrink();
    }

    return BlocBuilder<DailyChallengeBloc, DailyChallengeState>(
      builder: (context, state) {
        final l10n = AppLocalizations.of(context)!;
        MascotState mascotState = MascotState.idle;
        String? message;

        if (state is DailyChallengeLoading) {
          mascotState = MascotState.thinking;
        } else if (state is DailyChallengeInProgress) {
          if (state.wasLastAnswerCorrect == true) {
            mascotState = MascotState.happy;
            message = l10n.mascot_correct;
          } else if (state.wasLastAnswerCorrect == false) {
            mascotState = MascotState.encourage;
            message = l10n.mascot_wrong;
          }
        } else if (state is DailyChallengeCompleted) {
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

  Widget _buildReadyScreen(BuildContext context, DailyChallengeReady state) {
    return Center(
      child: Padding(
        padding: const EdgeInsets.all(24.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // Trophy icon
            Container(
              width: 120,
              height: 120,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                gradient: LinearGradient(
                  colors: [
                    Colors.amber[300]!,
                    Colors.amber[600]!,
                  ],
                ),
              ),
              child: const Icon(
                Icons.emoji_events,
                size: 64,
                color: Colors.white,
              ),
            ),

            const SizedBox(height: 32),

            // Title
            const Text(
              'Daily Challenge',
              style: TextStyle(
                fontSize: 32,
                fontWeight: FontWeight.bold,
              ),
            ),

            const SizedBox(height: 16),

            // Bonus info
            Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                color: Colors.amber[50],
                borderRadius: BorderRadius.circular(12),
                border: Border.all(color: Colors.amber, width: 2),
              ),
              child: Column(
                children: [
                  Text(
                    '${state.bonusMultiplier.toStringAsFixed(1)}x BONUS POINTS!',
                    style: TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                      color: Colors.amber[900],
                    ),
                  ),
                  const SizedBox(height: 8),
                  Text(
                    '${state.totalQuestions} flags • Beat the clock!',
                    style: TextStyle(
                      fontSize: 14,
                      color: Colors.grey[700],
                    ),
                  ),
                ],
              ),
            ),

            const SizedBox(height: 32),

            // Already completed message
            if (state.isAlreadyCompleted) ...[
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: Colors.green[50],
                  borderRadius: BorderRadius.circular(12),
                  border: Border.all(color: Colors.green, width: 2),
                ),
                child: Column(
                  children: [
                    const Icon(
                      Icons.check_circle,
                      color: Colors.green,
                      size: 48,
                    ),
                    const SizedBox(height: 8),
                    const Text(
                      'Already Completed Today!',
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                        color: Colors.green,
                      ),
                    ),
                    const SizedBox(height: 4),
                    Text(
                      'Your score: ${state.previousScore}',
                      style: const TextStyle(fontSize: 14),
                    ),
                    const SizedBox(height: 8),
                    const Text(
                      'Come back tomorrow for a new challenge!',
                      style: TextStyle(fontSize: 12, color: Colors.grey),
                      textAlign: TextAlign.center,
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 24),
              OutlinedButton(
                onPressed: () => context.go(AppRoutes.home),
                child: const Text('Go Back'),
              ),
            ] else ...[
              // Start button
              ElevatedButton(
                onPressed: () {
                  context.read<DailyChallengeBloc>().add(
                        const StartDailyChallenge(),
                      );
                },
                style: ElevatedButton.styleFrom(
                  backgroundColor: const Color(0xFF399DC5),
                  padding: const EdgeInsets.symmetric(
                    horizontal: 48,
                    vertical: 16,
                  ),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                ),
                child: const Text(
                  'Start Challenge',
                  style: TextStyle(fontSize: 18, color: Colors.white),
                ),
              ),
            ],
          ],
        ),
      ),
    );
  }

  Widget _buildChallengeContent(
      BuildContext context, DailyChallengeInProgress state) {
    final l10n = AppLocalizations.of(context)!;
    final currentQuestion = state.currentQuestion;

    return Column(
      children: [
        // Score header with timer
        Column(
          children: [
            ScoreHeader(
              correctCount: state.correctCount,
              wrongCount: state.wrongCount,
              currentScore:
                  ((state.correctCount * 3 - state.wrongCount) *
                          state.bonusMultiplier)
                      .round(),
            ),

            // Timer and bonus display
            Container(
              width: double.infinity,
              padding: const EdgeInsets.symmetric(vertical: 8.0),
              color: Colors.amber[100],
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  Row(
                    children: [
                      const Icon(Icons.timer, size: 20),
                      const SizedBox(width: 4),
                      Text(
                        _formatTime(_elapsedSeconds),
                        style: const TextStyle(
                          fontSize: 16,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ],
                  ),
                  Row(
                    children: [
                      const Icon(Icons.bolt, color: Colors.amber, size: 20),
                      const SizedBox(width: 4),
                      Text(
                        '${state.bonusMultiplier.toStringAsFixed(1)}x BONUS',
                        style: TextStyle(
                          fontSize: 14,
                          fontWeight: FontWeight.bold,
                          color: Colors.amber[900],
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ],
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
                    Colors.amber,
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

        // Quiz content (reuse from quiz screen)
        Expanded(
          child: SingleChildScrollView(
            child: Column(
              children: [
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

                FlagDisplay(
                  flagImagePath: currentQuestion.correctFlag.flagImagePath,
                ),

                const SizedBox(height: 32),

                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 16.0),
                  child: Column(
                    children: currentQuestion.allOptions.map((option) {
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
                                  context.read<DailyChallengeBloc>().add(
                                        AnswerDailyChallengeQuestion(
                                          option.countryName,
                                        ),
                                      );

                                  Future.delayed(
                                    const Duration(milliseconds: 1500),
                                    () {
                                      if (context.mounted) {
                                        context.read<DailyChallengeBloc>().add(
                                              const NextDailyChallengeQuestion(),
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
              ],
            ),
          ),
        ),
      ],
    );
  }

  void _showCompletionDialog(
      BuildContext context, DailyChallengeCompleted state) {
    showDialog(
      context: context,
      barrierDismissible: false,
      builder: (dialogContext) => AlertDialog(
        title: const Text(
          'Challenge Complete!',
          textAlign: TextAlign.center,
        ),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            const Icon(
              Icons.emoji_events,
              size: 64,
              color: Colors.amber,
            ),
            const SizedBox(height: 16),
            Text(
              'Final Score: ${state.finalScore}',
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 8),
            Text(
              'Bonus: +${state.bonusScore}',
              style: TextStyle(
                fontSize: 16,
                color: Colors.amber[700],
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 12),
            Text('Correct: ${state.correctCount}'),
            Text('Wrong: ${state.wrongCount}'),
            Text('Time: ${_formatTime(state.completionTimeSeconds)}'),
            if (state.isNewRecord) ...[
              const SizedBox(height: 16),
              Container(
                padding: const EdgeInsets.all(8),
                decoration: BoxDecoration(
                  color: Colors.amber[50],
                  borderRadius: BorderRadius.circular(8),
                ),
                child: const Text(
                  '🎉 NEW RECORD! 🎉',
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    color: Colors.amber,
                  ),
                ),
              ),
            ],
          ],
        ),
        actions: [
          ElevatedButton(
            onPressed: () {
              Navigator.of(dialogContext).pop();
              context.go(AppRoutes.home);
            },
            child: const Text('Done'),
          ),
        ],
      ),
    );
  }
}
