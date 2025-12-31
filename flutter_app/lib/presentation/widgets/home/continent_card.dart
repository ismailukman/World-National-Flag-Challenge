import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../data/repositories/progress_repository.dart';
import '../../../core/constants/app_dimensions.dart';
import '../globe/globe_3d_widget.dart';

/// Continent selection card for home screen
class ContinentCard extends StatelessWidget {
  final String continentId;
  final String continentName;
  final IconData icon;
  final Color color;
  final VoidCallback onTap;

  const ContinentCard({
    super.key,
    required this.continentId,
    required this.continentName,
    required this.icon,
    required this.color,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    final progressRepo = context.read<ProgressRepository>();

    return FutureBuilder(
      future: progressRepo.getProgress(continentId),
      builder: (context, snapshot) {
        final progress = snapshot.data;
        final highScore = progress?.highScore ?? 0;
        final completion = progress?.completionPercentage ?? 0.0;

        return Card(
          elevation: AppDimensions.cardElevation,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(AppDimensions.cardBorderRadius),
          ),
          child: InkWell(
            onTap: onTap,
            borderRadius: BorderRadius.circular(AppDimensions.cardBorderRadius),
            child: Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                  colors: [
                    color,
                    color.withOpacity(0.7),
                  ],
                ),
                borderRadius: BorderRadius.circular(AppDimensions.cardBorderRadius),
              ),
              child: Row(
                children: [
                  // Icon
                  Container(
                    width: 56,
                    height: 56,
                    decoration: BoxDecoration(
                      color: Colors.white.withOpacity(0.3),
                      shape: BoxShape.circle,
                    ),
                    alignment: Alignment.center,
                    child: const Globe3DWidget(
                      size: 48,
                      autoRotate: true,
                      rotationSpeed: 1.0,
                      variant: GlobeVariant.home,
                    ),
                  ),
                  const SizedBox(width: 16),

                  // Content
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        // Continent name
                        Text(
                          continentName,
                          style: const TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                            color: Colors.white,
                          ),
                        ),
                        const SizedBox(height: 4),

                        // Progress info
                        if (highScore > 0) ...[
                          Row(
                            children: [
                              const Icon(
                                Icons.stars,
                                size: 16,
                                color: Colors.white70,
                              ),
                              const SizedBox(width: 4),
                              Text(
                                'High Score: $highScore',
                                style: const TextStyle(
                                  fontSize: 13,
                                  color: Colors.white70,
                                ),
                              ),
                            ],
                          ),
                          const SizedBox(height: 2),
                          Row(
                            children: [
                              const Icon(
                                Icons.check_circle,
                                size: 16,
                                color: Colors.white70,
                              ),
                              const SizedBox(width: 4),
                              Text(
                                '${completion.toStringAsFixed(0)}% Complete',
                                style: const TextStyle(
                                  fontSize: 13,
                                  color: Colors.white70,
                                ),
                              ),
                            ],
                          ),
                        ] else ...[
                          const Text(
                            'Tap to start!',
                            style: TextStyle(
                              fontSize: 13,
                              color: Colors.white70,
                              fontStyle: FontStyle.italic,
                            ),
                          ),
                        ],
                      ],
                    ),
                  ),

                  // Arrow
                  const Icon(
                    Icons.arrow_forward_ios,
                    color: Colors.white,
                    size: 24,
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}

// Legacy 2D Globe Animation (kept as reference)
// This was replaced with Globe3DWidget for enhanced 3D rendering
/*
class _AnimatedGlobeIcon extends StatefulWidget {
  const _AnimatedGlobeIcon();

  @override
  State<_AnimatedGlobeIcon> createState() => _AnimatedGlobeIconState();
}

class _AnimatedGlobeIconState extends State<_AnimatedGlobeIcon>
    with SingleTickerProviderStateMixin {
  late final AnimationController _controller;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      vsync: this,
      duration: const Duration(seconds: 6),
    )..repeat();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      animation: _controller,
      builder: (context, child) {
        final angle = _controller.value * 2 * math.pi;
        final scale = 1.0 + 0.06 * math.sin(angle);
        return Transform(
          alignment: Alignment.center,
          transform: Matrix4.identity()
            ..setEntry(3, 2, 0.0015)
            ..rotateY(angle)
            ..scale(scale),
          child: child,
        );
      },
      child: const Text(
        '🌍',
        style: TextStyle(fontSize: 32),
      ),
    );
  }
}
*/
