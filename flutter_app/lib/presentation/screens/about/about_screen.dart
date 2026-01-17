import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import '../../../core/constants/app_routes.dart';

/// About Screen - app information and developer credit
class AboutScreen extends StatelessWidget {
  const AboutScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    return Scaffold(
      appBar: AppBar(
        title: Text(l10n.about),
        centerTitle: true,
        elevation: 0,
        leading: IconButton(
          icon: const Icon(Icons.home),
          tooltip: 'Back to Menu',
          onPressed: () => context.go(AppRoutes.home),
        ),
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [
              const Color(0xFF399DC5).withOpacity(0.1),
              Colors.white,
            ],
          ),
        ),
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24.0),
          child: Column(
            children: [
              // App icon/logo
              Container(
                width: 120,
                height: 120,
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  gradient: LinearGradient(
                    colors: [
                      const Color(0xFF399DC5),
                      const Color(0xFF7AC4E1),
                    ],
                  ),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black.withOpacity(0.2),
                      blurRadius: 10,
                      offset: const Offset(0, 4),
                    ),
                  ],
                ),
                child: const Icon(
                  Icons.flag,
                  size: 64,
                  color: Colors.white,
                ),
              ),

              const SizedBox(height: 24),

              // App name
              Text(
                l10n.world_national_flag_challenge,
                style: const TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
                textAlign: TextAlign.center,
              ),

              const SizedBox(height: 8),

              // Version
              Text(
                'Version 1.0.3 (Flutter)',
                style: TextStyle(
                  fontSize: 14,
                  color: Colors.grey[600],
                ),
              ),

              const SizedBox(height: 32),

              // Description
              Card(
                elevation: 2,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: Column(
                    children: [
                      Icon(
                        Icons.info_outline,
                        size: 48,
                        color: const Color(0xFF399DC5),
                      ),
                      const SizedBox(height: 16),
                      Text(
                        l10n.this_application_is_designed_to_ease_familiarity_with_world_national_flags_world_national_flag_challenge_provides_the_educative_and_entertaining_opportunity,
                        textAlign: TextAlign.center,
                        style: const TextStyle(
                          fontSize: 16,
                          height: 1.5,
                        ),
                      ),
                    ],
                  ),
                ),
              ),

              const SizedBox(height: 24),

              // Features
              Card(
                elevation: 2,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Row(
                        children: [
                          Icon(Icons.stars, color: Color(0xFF399DC5)),
                          SizedBox(width: 8),
                          Text(
                            'Features',
                            style: TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 16),
                      _buildFeatureItem(
                        icon: Icons.public,
                        text: '204+ flags across 6 continents',
                      ),
                      _buildFeatureItem(
                        icon: Icons.language,
                        text: '4 languages (English, Arabic, French, Turkish)',
                      ),
                      _buildFeatureItem(
                        icon: Icons.emoji_events,
                        text: 'Achievements and badges system',
                      ),
                      _buildFeatureItem(
                        icon: Icons.school,
                        text: 'Practice mode for learning',
                      ),
                      _buildFeatureItem(
                        icon: Icons.calendar_today,
                        text: 'Daily challenges with bonus points',
                      ),
                      _buildFeatureItem(
                        icon: Icons.leaderboard,
                        text: 'Local leaderboard rankings',
                      ),
                      _buildFeatureItem(
                        icon: Icons.music_note,
                        text: 'Background music and sound effects',
                      ),
                    ],
                  ),
                ),
              ),

              const SizedBox(height: 24),

              // Developer credit
              Card(
                elevation: 2,
                color: const Color(0xFF399DC5),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: Column(
                    children: [
                      const Icon(
                        Icons.code,
                        size: 48,
                        color: Colors.white,
                      ),
                      const SizedBox(height: 12),
                      Text(
                        l10n.by_ismaila_lukman_enegi,
                        style: const TextStyle(
                          fontSize: 16,
                          fontStyle: FontStyle.italic,
                          color: Colors.white,
                        ),
                        textAlign: TextAlign.center,
                      ),
                      const SizedBox(height: 8),
                      Text(
                        l10n.nigeria,
                        style: TextStyle(
                          fontSize: 14,
                          color: Colors.white.withOpacity(0.9),
                        ),
                      ),
                    ],
                  ),
                ),
              ),

              const SizedBox(height: 24),

              // Copyright
              Text(
                '© 2015-2026 World Nation Flag Challenge',
                style: TextStyle(
                  fontSize: 12,
                  color: Colors.grey[600],
                ),
                textAlign: TextAlign.center,
              ),

              const SizedBox(height: 8),

              Text(
                'All rights reserved',
                style: TextStyle(
                  fontSize: 12,
                  color: Colors.grey[600],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildFeatureItem({required IconData icon, required String text}) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 12.0),
      child: Row(
        children: [
          Icon(icon, size: 20, color: const Color(0xFF399DC5)),
          const SizedBox(width: 12),
          Expanded(
            child: Text(
              text,
              style: const TextStyle(fontSize: 14),
            ),
          ),
        ],
      ),
    );
  }
}
