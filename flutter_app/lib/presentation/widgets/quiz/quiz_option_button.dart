import 'package:flutter/material.dart';

/// States for quiz option buttons
enum QuizButtonState {
  normal,
  correct,
  wrong,
}

/// Quiz option button with 3 states (normal, correct, wrong)
class QuizOptionButton extends StatelessWidget {
  final String text;
  final QuizButtonState state;
  final VoidCallback? onPressed;

  const QuizOptionButton({
    super.key,
    required this.text,
    required this.state,
    this.onPressed,
  });

  @override
  Widget build(BuildContext context) {
    // Determine colors based on state
    Color backgroundColor;
    Color textColor;
    IconData? icon;

    switch (state) {
      case QuizButtonState.correct:
        backgroundColor = Colors.green;
        textColor = Colors.white;
        icon = Icons.check_circle;
        break;
      case QuizButtonState.wrong:
        backgroundColor = Colors.red;
        textColor = Colors.white;
        icon = Icons.cancel;
        break;
      case QuizButtonState.normal:
      default:
        backgroundColor = const Color(0xFF399DC5);
        textColor = Colors.white;
        icon = null;
    }

    return SizedBox(
      width: double.infinity,
      height: 56,
      child: ElevatedButton(
        onPressed: onPressed,
        style: ElevatedButton.styleFrom(
          backgroundColor: backgroundColor,
          foregroundColor: textColor,
          elevation: state == QuizButtonState.normal ? 3 : 5,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
          padding: const EdgeInsets.symmetric(horizontal: 16),
        ),
        child: Row(
          children: [
            Expanded(
              child: Text(
                text,
                style: const TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.w600,
                ),
                textAlign: TextAlign.center,
              ),
            ),
            if (icon != null) ...[
              const SizedBox(width: 8),
              Icon(
                icon,
                size: 24,
              ),
            ],
          ],
        ),
      ),
    );
  }
}
