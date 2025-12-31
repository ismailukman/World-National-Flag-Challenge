import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import 'package:flutter_animate/flutter_animate.dart';
import '../../../core/constants/app_routes.dart';
import '../../../data/repositories/settings_repository.dart';
import '../../widgets/home/continent_card.dart';
import '../../widgets/home/audio_control.dart';
import '../../widgets/home/flag_style_selector.dart';
import '../../widgets/home/language_selector.dart';
import '../../widgets/mascot/mascot_character.dart';

/// Home screen with continent selection (replaces Home.kt from Android)
class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  Timer? _mascotMessageTimer;
  bool _showMascotMessage = true;

  @override
  void initState() {
    super.initState();
    // Update streak when user opens the app
    _updateStreak();
    _showMascotMessageTemporarily();
  }

  @override
  void dispose() {
    _mascotMessageTimer?.cancel();
    super.dispose();
  }

  Future<void> _updateStreak() async {
    final settingsRepo = context.read<SettingsRepository>();
    await settingsRepo.updateStreak();
  }

  void _showMascotMessageTemporarily() {
    _mascotMessageTimer?.cancel();
    setState(() {
      _showMascotMessage = true;
    });
    _mascotMessageTimer = Timer(const Duration(seconds: 3), () {
      if (mounted) {
        setState(() {
          _showMascotMessage = false;
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;
    final settingsRepo = context.read<SettingsRepository>();
    final showMascot = kIsWeb || settingsRepo.isMascotEnabled();

    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
            colors: [
              Color(0xFF399DC5), // Primary blue
              Color(0xFF7AC4E1), // Secondary light blue
            ],
          ),
        ),
        child: SafeArea(
          child: Stack(
            children: [
              Column(
                children: [
                  // App bar
                  Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        // Logo and Title
                        Row(
                          children: [
                            // App logo
                            Container(
                              width: 40,
                              height: 40,
                              decoration: BoxDecoration(
                                color: Colors.white,
                                shape: BoxShape.circle,
                                boxShadow: [
                                  BoxShadow(
                                    color: Colors.black.withOpacity(0.2),
                                    blurRadius: 4,
                                    offset: const Offset(0, 2),
                                  ),
                                ],
                              ),
                              child: ClipOval(
                                child: Image.asset(
                                  'assets/images/icons/app_logo.png',
                                  fit: BoxFit.cover,
                                ),
                              ),
                            ),
                          ],
                        ),

                        // Language and Audio controls
                        const Row(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            FlagStyleSelector(),
                            LanguageSelector(),
                            SizedBox(width: 8),
                            AudioControl(),
                          ],
                        ),
                      ],
                    ),
                  ),

                  // Main content
                  Expanded(
                    child: SingleChildScrollView(
                      padding: const EdgeInsets.fromLTRB(16, 16, 16, 32),
                      child: Column(
                        children: [
                          // Welcome message
                          Card(
                            elevation: 4,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(12),
                            ),
                            child: Padding(
                              padding: const EdgeInsets.all(16.0),
                              child: Column(
                                children: [
                                  const Icon(
                                    Icons.flag,
                                    size: 48,
                                    color: Color(0xFF399DC5),
                                  ),
                                  const SizedBox(height: 12),
                                  Text(
                                    l10n.world_national_flag_challenge,
                                    textAlign: TextAlign.center,
                                    style: const TextStyle(
                                      fontSize: 18,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                  const SizedBox(height: 8),
                                  Text(
                                    l10n.enjoy,
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      fontSize: 14,
                                      color: Colors.grey[600],
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ),
                          const SizedBox(height: 24),

                          // Feature cards row (Practice Mode & Daily Challenge)
                          Row(
                            children: [
                              // Practice Mode
                              Expanded(
                                child: _buildFeatureCard(
                                  context: context,
                                  icon: Icons.school,
                                  title: l10n.practice_mode,
                                  subtitle: 'Study flags',
                                  color: const Color(0xFF9B59B6), // Purple
                                  onTap: () => context.go(AppRoutes.practice),
                                ),
                              ),
                              const SizedBox(width: 12),
                              // Daily Challenge
                              Expanded(
                                child: _buildFeatureCard(
                                  context: context,
                                  icon: Icons.emoji_events,
                                  title: l10n.daily_challenge,
                                  subtitle: '2x Bonus!',
                                  color: const Color(0xFFF39C12), // Orange/Gold
                                  onTap: () => context.go(AppRoutes.dailyChallenge),
                                  showBadge: true,
                                ),
                              ),
                            ],
                          ),
                          const SizedBox(height: 24),

                          // Section title
                          const Text(
                            'Select a Continent',
                            style: TextStyle(
                              fontSize: 22,
                              fontWeight: FontWeight.bold,
                              color: Colors.white,
                            ),
                          ),
                          const SizedBox(height: 16),

                          // Continent cards
                          ContinentCard(
                            continentId: 'africa',
                            continentName: l10n.africa,
                            icon: Icons.public,
                            color: const Color(0xFFE74C3C), // Red
                            onTap: () => context.go('${AppRoutes.quiz}/africa'),
                          ),
                          const SizedBox(height: 12),

                          ContinentCard(
                            continentId: 'asia',
                            continentName: l10n.asia,
                            icon: Icons.public,
                            color: const Color(0xFFF39C12), // Orange
                            onTap: () => context.go('${AppRoutes.quiz}/asia'),
                          ),
                          const SizedBox(height: 12),

                          ContinentCard(
                            continentId: 'europe',
                            continentName: l10n.europe,
                            icon: Icons.public,
                            color: const Color(0xFF3498DB), // Blue
                            onTap: () => context.go('${AppRoutes.quiz}/europe'),
                          ),
                          const SizedBox(height: 12),

                          ContinentCard(
                            continentId: 'north_america',
                            continentName: l10n.north_america,
                            icon: Icons.public,
                            color: const Color(0xFF2ECC71), // Green
                            onTap: () =>
                                context.go('${AppRoutes.quiz}/north_america'),
                          ),
                          const SizedBox(height: 12),

                          ContinentCard(
                            continentId: 'south_america',
                            continentName: l10n.south_america,
                            icon: Icons.public,
                            color: const Color(0xFF9B59B6), // Purple
                            onTap: () =>
                                context.go('${AppRoutes.quiz}/south_america'),
                          ),
                          const SizedBox(height: 12),

                          ContinentCard(
                            continentId: 'oceania',
                            continentName: l10n.oceania,
                            icon: Icons.public,
                            color: const Color(0xFF1ABC9C), // Teal
                            onTap: () => context.go('${AppRoutes.quiz}/oceania'),
                          ),
                          const SizedBox(height: 24),

                          // Bottom navigation
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: [
                              // Help button
                              Expanded(
                                child: Padding(
                                  padding:
                                      const EdgeInsets.symmetric(horizontal: 4.0),
                                  child: _buildBottomButton(
                                    context: context,
                                    icon: Icons.help_outline,
                                    label: l10n.help,
                                    onTap: () => context.go(AppRoutes.help),
                                  ),
                                ),
                              ),

                              // About button
                              Expanded(
                                child: Padding(
                                  padding:
                                      const EdgeInsets.symmetric(horizontal: 4.0),
                                  child: _buildBottomButton(
                                    context: context,
                                    icon: Icons.info_outline,
                                    label: l10n.about,
                                    onTap: () => context.go(AppRoutes.about),
                                  ),
                                ),
                              ),

                              // Achievements button
                              Expanded(
                                child: Padding(
                                  padding:
                                      const EdgeInsets.symmetric(horizontal: 4.0),
                                  child: _buildBottomButton(
                                    context: context,
                                    icon: Icons.emoji_events,
                                    label: l10n.achievements,
                                    onTap: () => context.go(AppRoutes.achievements),
                                  ),
                                ),
                              ),

                              // Leaderboard button (new)
                              Expanded(
                                child: Padding(
                                  padding:
                                      const EdgeInsets.symmetric(horizontal: 4.0),
                                  child: _buildBottomButton(
                                    context: context,
                                    icon: Icons.leaderboard,
                                    label: l10n.leaderboard,
                                    onTap: () => context.go(AppRoutes.leaderboard),
                                  ),
                                ),
                              ),
                            ],
                          ),
                          const SizedBox(height: 80),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
              if (showMascot)
                MascotCharacter(
                  state: MascotState.idle,
                  message: l10n.mascot_greeting,
                  showMessage: _showMascotMessage,
                  onTap: _showMascotMessageTemporarily,
                ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildBottomButton({
    required BuildContext context,
    required IconData icon,
    required String label,
    required VoidCallback onTap,
  }) {
    return Tooltip(
      message: label,
      child: InkWell(
        onTap: onTap,
        borderRadius: BorderRadius.circular(12),
        child: Container(
          padding: const EdgeInsets.symmetric(vertical: 14, horizontal: 16),
          decoration: BoxDecoration(
            color: Colors.white.withOpacity(0.9),
            borderRadius: BorderRadius.circular(12),
            boxShadow: [
              BoxShadow(
                color: Colors.black.withOpacity(0.1),
                blurRadius: 4,
                offset: const Offset(0, 2),
              ),
            ],
          ),
          child: Center(
            child: Icon(
              icon,
              size: 34,
              color: const Color(0xFF399DC5),
            )
                .animate(
                  onPlay: (controller) => controller.repeat(reverse: true),
                )
                .tint(
                  color: const Color(0xFF1ABC9C),
                  duration: const Duration(milliseconds: 1400),
                ),
          ),
        ),
      ),
    );
  }

  Widget _buildFeatureCard({
    required BuildContext context,
    required IconData icon,
    required String title,
    required String subtitle,
    required Color color,
    required VoidCallback onTap,
    bool showBadge = false,
  }) {
    return InkWell(
      onTap: onTap,
      borderRadius: BorderRadius.circular(12),
      child: Container(
        height: 120,
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [color, color.withOpacity(0.7)],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
          borderRadius: BorderRadius.circular(12),
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.2),
              blurRadius: 8,
              offset: const Offset(0, 4),
            ),
          ],
        ),
        child: Stack(
          children: [
            Padding(
              padding: const EdgeInsets.all(12.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(icon, size: 36, color: Colors.white),
                  const SizedBox(height: 6),
                  Text(
                    title,
                    style: const TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                  ),
                  const SizedBox(height: 2),
                  Text(
                    subtitle,
                    style: TextStyle(
                      fontSize: 12,
                      color: Colors.white.withOpacity(0.9),
                    ),
                  ),
                ],
              ),
            ),
            if (showBadge)
              Positioned(
                top: 8,
                right: 8,
                child: Container(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 8,
                    vertical: 4,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.red,
                    borderRadius: BorderRadius.circular(12),
                  ),
                  child: const Text(
                    'NEW',
                    style: TextStyle(
                      fontSize: 10,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                ),
              ),
          ],
        ),
      ),
    );
  }
}
