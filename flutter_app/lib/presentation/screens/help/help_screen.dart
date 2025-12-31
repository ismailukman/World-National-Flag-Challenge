import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import '../../../core/constants/app_routes.dart';

/// Help Screen - game instructions and tips
class HelpScreen extends StatelessWidget {
  const HelpScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    return Scaffold(
      appBar: AppBar(
        title: Text(l10n.help),
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
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // Header
              Center(
                child: Column(
                  children: [
                    Container(
                      width: 80,
                      height: 80,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: const Color(0xFF399DC5).withOpacity(0.2),
                      ),
                      child: const Icon(
                        Icons.help_outline,
                        size: 48,
                        color: Color(0xFF399DC5),
                      ),
                    ),
                    const SizedBox(height: 16),
                    const Text(
                      'How to Play',
                      style: TextStyle(
                        fontSize: 24,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),

              const SizedBox(height: 32),

              // Main instructions
              _buildInstructionCard(
                icon: Icons.flag,
                title: 'Quiz Mode',
                description: l10n.this_challenge_requires_you_to_select_the_correct_country_name_of_the_national_flag_on_current_display_from_the_options_provided_beginning_with_africa_play_across_all_continents_to_stand_a_chance_of_getting_higher_points_long_press_the_audio_icon_to_select_music,
                color: const Color(0xFF399DC5),
              ),

              const SizedBox(height: 16),

              _buildInstructionCard(
                icon: Icons.calculate,
                title: 'Scoring System',
                description:
                    'Each correct answer earns 3 points. Each wrong answer deducts 1 point. Final Score = (Correct × 3) - Wrong',
                color: Colors.green,
              ),

              const SizedBox(height: 16),

              _buildInstructionCard(
                icon: Icons.school,
                title: 'Practice Mode',
                description:
                    'Browse and study flags without pressure. Search for specific countries, bookmark your favorites, and learn capitals and fun facts!',
                color: const Color(0xFF9B59B6),
              ),

              const SizedBox(height: 16),

              _buildInstructionCard(
                icon: Icons.emoji_events,
                title: 'Daily Challenge',
                description:
                    'Complete a special quiz each day with 10 random flags. Earn 2x bonus points! Challenge resets daily at midnight.',
                color: const Color(0xFFF39C12),
              ),

              const SizedBox(height: 16),

              _buildInstructionCard(
                icon: Icons.stars,
                title: 'Achievements',
                description:
                    'Unlock badges by completing quizzes, maintaining streaks, and mastering continents. Track your progress and show off your skills!',
                color: Colors.amber,
              ),

              const SizedBox(height: 24),

              // Tips section
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
                          Icon(Icons.lightbulb_outline, color: Color(0xFFF39C12)),
                          SizedBox(width: 8),
                          Text(
                            'Pro Tips',
                            style: TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 16),
                      _buildTipItem(
                        '1. Use Practice Mode to familiarize yourself with flags before taking quizzes',
                      ),
                      _buildTipItem(
                        '2. Play daily to maintain your streak and unlock special achievements',
                      ),
                      _buildTipItem(
                        '3. Start with smaller continents like Oceania to build confidence',
                      ),
                      _buildTipItem(
                        '4. Focus on distinctive features like colors, symbols, and patterns',
                      ),
                      _buildTipItem(
                        '5. Complete the Daily Challenge first thing each day for maximum bonus!',
                      ),
                    ],
                  ),
                ),
              ),

              const SizedBox(height: 24),

              // Audio controls
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
                          Icon(Icons.music_note, color: Color(0xFF399DC5)),
                          SizedBox(width: 8),
                          Text(
                            'Audio Controls',
                            style: TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 16),
                      _buildAudioItem(
                        Icons.volume_up,
                        'Music Toggle',
                        'Tap to mute/unmute background music',
                      ),
                      _buildAudioItem(
                        Icons.music_note,
                        'Music Selection',
                        'Choose from Afro, Game, or War soundtracks',
                      ),
                      _buildAudioItem(
                        Icons.volume_down,
                        'Sound Effects',
                        'Hear feedback for correct and wrong answers',
                      ),
                    ],
                  ),
                ),
              ),

              const SizedBox(height: 32),

              // Enjoy message
              Center(
                child: Column(
                  children: [
                    const Icon(
                      Icons.emoji_emotions,
                      size: 48,
                      color: Color(0xFF399DC5),
                    ),
                    const SizedBox(height: 12),
                    Text(
                      l10n.enjoy,
                      style: const TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.w500,
                        color: Color(0xFF399DC5),
                      ),
                    ),
                  ],
                ),
              ),

              const SizedBox(height: 16),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildInstructionCard({
    required IconData icon,
    required String title,
    required String description,
    required Color color,
  }) {
    return Card(
      elevation: 3,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12),
      ),
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              width: 50,
              height: 50,
              decoration: BoxDecoration(
                color: color.withOpacity(0.2),
                borderRadius: BorderRadius.circular(10),
              ),
              child: Icon(icon, color: color, size: 28),
            ),
            const SizedBox(width: 16),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    title,
                    style: const TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 4),
                  Text(
                    description,
                    style: TextStyle(
                      fontSize: 14,
                      color: Colors.grey[700],
                      height: 1.4,
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildTipItem(String text) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 12.0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Icon(Icons.check_circle, size: 20, color: Colors.green),
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

  Widget _buildAudioItem(IconData icon, String title, String description) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 12.0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Icon(icon, size: 20, color: const Color(0xFF399DC5)),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  title,
                  style: const TextStyle(
                    fontSize: 14,
                    fontWeight: FontWeight.w600,
                  ),
                ),
                Text(
                  description,
                  style: TextStyle(
                    fontSize: 13,
                    color: Colors.grey[600],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
