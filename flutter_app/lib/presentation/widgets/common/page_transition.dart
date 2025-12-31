import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';

/// Page transition wrapper for smooth animations
class PageTransitionWrapper extends StatelessWidget {
  final Widget child;
  final PageTransitionType type;

  const PageTransitionWrapper({
    super.key,
    required this.child,
    this.type = PageTransitionType.fade,
  });

  @override
  Widget build(BuildContext context) {
    switch (type) {
      case PageTransitionType.fade:
        return child.animate().fadeIn(
              duration: const Duration(milliseconds: 300),
              curve: Curves.easeIn,
            );

      case PageTransitionType.slide:
        return child
            .animate()
            .slideX(
              begin: 1.0,
              end: 0.0,
              duration: const Duration(milliseconds: 300),
              curve: Curves.easeOut,
            )
            .fadeIn(
              duration: const Duration(milliseconds: 200),
            );

      case PageTransitionType.scale:
        return child.animate().scale(
              begin: const Offset(0.8, 0.8),
              end: const Offset(1.0, 1.0),
              duration: const Duration(milliseconds: 300),
              curve: Curves.easeOut,
            ).fadeIn(
              duration: const Duration(milliseconds: 200),
            );

      case PageTransitionType.scaleSlide:
        return child
            .animate()
            .slideY(
              begin: 0.2,
              end: 0.0,
              duration: const Duration(milliseconds: 400),
              curve: Curves.easeOut,
            )
            .scale(
              begin: const Offset(0.9, 0.9),
              end: const Offset(1.0, 1.0),
              duration: const Duration(milliseconds: 400),
              curve: Curves.easeOut,
            )
            .fadeIn(
              duration: const Duration(milliseconds: 300),
            );
    }
  }
}

enum PageTransitionType {
  fade,
  slide,
  scale,
  scaleSlide,
}

/// Card entrance animation for list items
class CardEntranceAnimation extends StatelessWidget {
  final Widget child;
  final int index;

  const CardEntranceAnimation({
    super.key,
    required this.child,
    this.index = 0,
  });

  @override
  Widget build(BuildContext context) {
    return child
        .animate(
          delay: Duration(milliseconds: 50 * index),
        )
        .slideY(
          begin: 0.3,
          end: 0.0,
          duration: const Duration(milliseconds: 400),
          curve: Curves.easeOut,
        )
        .fadeIn(
          duration: const Duration(milliseconds: 300),
        );
  }
}

/// Button tap animation
class ButtonTapAnimation extends StatelessWidget {
  final Widget child;
  final VoidCallback? onTap;

  const ButtonTapAnimation({
    super.key,
    required this.child,
    this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: child
          .animate(
            onPlay: (controller) => controller.repeat(reverse: true),
          )
          .shimmer(
            duration: const Duration(seconds: 2),
            color: Colors.white.withOpacity(0.3),
          ),
    );
  }
}

/// Pulse animation for important elements
class PulseAnimation extends StatelessWidget {
  final Widget child;
  final bool enabled;

  const PulseAnimation({
    super.key,
    required this.child,
    this.enabled = true,
  });

  @override
  Widget build(BuildContext context) {
    if (!enabled) return child;

    return child
        .animate(
          onPlay: (controller) => controller.repeat(reverse: true),
        )
        .scale(
          begin: const Offset(1.0, 1.0),
          end: const Offset(1.05, 1.05),
          duration: const Duration(milliseconds: 1000),
        );
  }
}

/// Shake animation for wrong answers
class ShakeAnimation extends StatelessWidget {
  final Widget child;
  final bool trigger;

  const ShakeAnimation({
    super.key,
    required this.child,
    required this.trigger,
  });

  @override
  Widget build(BuildContext context) {
    if (!trigger) return child;

    return child.animate().shake(
          duration: const Duration(milliseconds: 500),
          hz: 4,
          curve: Curves.easeInOut,
        );
  }
}
