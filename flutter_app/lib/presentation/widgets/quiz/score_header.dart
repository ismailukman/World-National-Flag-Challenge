import 'package:flutter/material.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';

/// Score header widget showing correct, wrong, and current score
class ScoreHeader extends StatelessWidget {
  final int correctCount;
  final int wrongCount;
  final int currentScore;

  const ScoreHeader({
    super.key,
    required this.correctCount,
    required this.wrongCount,
    required this.currentScore,
  });

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    return Container(
      width: double.infinity,
      padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 12.0),
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
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          // Correct count
          _buildScoreItem(
            icon: Icons.check_circle,
            label: l10n.correct,
            value: correctCount,
            color: Colors.green[300]!,
          ),

          // Divider
          Container(
            height: 40,
            width: 1,
            color: Colors.white.withOpacity(0.3),
          ),

          // Wrong count
          _buildScoreItem(
            icon: Icons.cancel,
            label: l10n.wrong,
            value: wrongCount,
            color: Colors.red[300]!,
          ),

          // Divider
          Container(
            height: 40,
            width: 1,
            color: Colors.white.withOpacity(0.3),
          ),

          // Current score
          _buildScoreItem(
            icon: Icons.stars,
            label: l10n.score,
            value: currentScore,
            color: Colors.amber[300]!,
          ),
        ],
      ),
    );
  }

  Widget _buildScoreItem({
    required IconData icon,
    required String label,
    required int value,
    required Color color,
  }) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Icon(
          icon,
          color: color,
          size: 24,
        ),
        const SizedBox(height: 4),
        Text(
          value.toString(),
          style: const TextStyle(
            fontSize: 20,
            fontWeight: FontWeight.bold,
            color: Colors.white,
          ),
        ),
        Text(
          label,
          style: TextStyle(
            fontSize: 12,
            color: Colors.white.withOpacity(0.9),
          ),
        ),
      ],
    );
  }
}
