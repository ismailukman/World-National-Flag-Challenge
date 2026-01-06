import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';

/// Mascot character widget - placeholder for Rive animation
///
/// This is a simple implementation using emojis/icons.
/// In production, replace with Rive animation for better quality.
///
/// Mascot states:
/// - idle: Default resting state
/// - happy: Correct answer feedback
/// - encourage: Wrong answer feedback
/// - celebrate: Quiz/challenge completion
/// - thinking: Processing/loading
class MascotCharacter extends StatelessWidget {
  final MascotState state;
  final String? message;
  final bool showMessage;
  final VoidCallback? onTap;

  const MascotCharacter({
    super.key,
    this.state = MascotState.idle,
    this.message,
    this.showMessage = true,
    this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return Positioned(
      bottom: 16,
      right: 16,
      child: GestureDetector(
        onTap: onTap,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.end,
          children: [
            // Speech bubble with message
            if (showMessage && message != null) ...[
              Container(
                padding: const EdgeInsets.symmetric(
                  horizontal: 12,
                  vertical: 8,
                ),
                constraints: const BoxConstraints(maxWidth: 200),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(12),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black.withOpacity(0.1),
                      blurRadius: 8,
                      offset: const Offset(0, 2),
                    ),
                  ],
                ),
                child: Text(
                  message!,
                  style: const TextStyle(
                    fontSize: 14,
                    fontWeight: FontWeight.w500,
                  ),
                ),
              ).animate().fadeIn(duration: const Duration(milliseconds: 300)).scale(
                    begin: const Offset(0.8, 0.8),
                    end: const Offset(1.0, 1.0),
                    duration: const Duration(milliseconds: 200),
                  ),
              const SizedBox(height: 8),
            ],

            // Mascot character
            _animateMascot(state, _buildMascotAvatar(state)),
          ],
        ),
      ),
    );
  }

  Widget _buildMascotAvatar(MascotState state) {
    return _buildMascotIcon(state);
  }

  Widget _buildMascotIcon(MascotState state) {
    switch (state) {
      case MascotState.idle:
        return const Text(
          '🙂',
          style: TextStyle(fontSize: 48),
        )
            .animate(
              onPlay: (controller) => controller.repeat(reverse: true),
            )
            .moveY(
              begin: 0,
              end: -4,
              duration: const Duration(milliseconds: 1200),
              curve: Curves.easeInOut,
            )
            .scale(
              begin: const Offset(1.0, 1.0),
              end: const Offset(1.05, 1.05),
              duration: const Duration(milliseconds: 1200),
              curve: Curves.easeInOut,
            );

      case MascotState.happy:
        return const Text(
          '😊',
          style: TextStyle(fontSize: 48),
        )
            .animate(
              onPlay: (controller) => controller.repeat(reverse: true),
            )
            .scale(
              begin: const Offset(1.0, 1.0),
              end: const Offset(1.2, 1.2),
              duration: const Duration(milliseconds: 500),
            );

      case MascotState.encourage:
        return const Text(
          '💪',
          style: TextStyle(fontSize: 48),
        ).animate().shake(
              duration: const Duration(milliseconds: 400),
              hz: 4,
            );

      case MascotState.celebrate:
        return const Text(
          '🎉',
          style: TextStyle(fontSize: 48),
        )
            .animate(
              onPlay: (controller) => controller.repeat(reverse: true),
            )
            .rotate(
              begin: -0.1,
              end: 0.1,
              duration: const Duration(milliseconds: 300),
            );

      case MascotState.thinking:
        return const Text(
          '🤔',
          style: TextStyle(fontSize: 48),
        )
            .animate(
              onPlay: (controller) => controller.repeat(reverse: true),
            )
            .scale(
              begin: const Offset(1.0, 1.0),
              end: const Offset(1.1, 1.1),
              duration: const Duration(milliseconds: 800),
            );
    }
  }

  List<Color> _getMascotColors(MascotState state) {
    switch (state) {
      case MascotState.idle:
        return [const Color(0xFF399DC5), const Color(0xFF7AC4E1)];
      case MascotState.happy:
        return [Colors.green[400]!, Colors.green[600]!];
      case MascotState.encourage:
        return [Colors.orange[400]!, Colors.orange[600]!];
      case MascotState.celebrate:
        return [Colors.purple[400]!, Colors.purple[600]!];
      case MascotState.thinking:
        return [Colors.grey[400]!, Colors.grey[600]!];
    }
  }

  Widget _animateMascot(MascotState state, Widget child) {
    switch (state) {
      case MascotState.idle:
        return child
            .animate(
              onPlay: (controller) => controller.repeat(reverse: true),
            )
            .moveY(
              begin: 0,
              end: -6,
              duration: const Duration(milliseconds: 1400),
              curve: Curves.easeInOut,
            );
      case MascotState.happy:
      case MascotState.celebrate:
        return child
            .animate(
              onPlay: (controller) => controller.repeat(reverse: true),
            )
            .scale(
              begin: const Offset(1.0, 1.0),
              end: const Offset(1.04, 1.04),
              duration: const Duration(milliseconds: 900),
              curve: Curves.easeInOut,
            );
      case MascotState.encourage:
      case MascotState.thinking:
        return child;
    }
  }

  /// Get default message for each state
  static String getDefaultMessage(MascotState state) {
    switch (state) {
      case MascotState.idle:
        return "Let's learn flags!";
      case MascotState.happy:
        return 'Great job! 🎯';
      case MascotState.encourage:
        return 'Keep trying! 💪';
      case MascotState.celebrate:
        return 'Amazing work! 🎉';
      case MascotState.thinking:
        return 'Hmm... 🤔';
    }
  }
}

enum MascotState {
  idle,
  happy,
  encourage,
  celebrate,
  thinking,
}

/// Rive Mascot Widget (for future implementation)
///
/// To implement with Rive:
/// 1. Create/commission a mascot character in Rive
/// 2. Export .riv file to assets/animations/
/// 3. Add rive package to pubspec.yaml
/// 4. Replace this widget with RiveAnimation
///
/// Example Rive implementation:
/// ```dart
/// class RiveMascot extends StatefulWidget {
///   final MascotState state;
///
///   @override
///   Widget build(BuildContext context) {
///     return RiveAnimation.asset(
///       'assets/animations/mascot.riv',
///       stateMachines: ['State Machine'],
///       onInit: (artboard) {
///         final controller = StateMachineController.fromArtboard(
///           artboard,
///           'State Machine',
///         );
///         // Trigger state based on mascot state
///       },
///     );
///   }
/// }
/// ```
class RiveMascotPlaceholder extends StatelessWidget {
  const RiveMascotPlaceholder({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      margin: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.amber[100],
        borderRadius: BorderRadius.circular(12),
        border: Border.all(color: Colors.amber, width: 2),
      ),
      child: const Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Icon(Icons.animation, size: 48, color: Colors.amber),
          SizedBox(height: 8),
          Text(
            'Rive Mascot Placeholder',
            style: TextStyle(fontWeight: FontWeight.bold),
          ),
          SizedBox(height: 4),
          Text(
            'Add mascot.riv to assets/animations/',
            style: TextStyle(fontSize: 12),
            textAlign: TextAlign.center,
          ),
        ],
      ),
    );
  }
}
