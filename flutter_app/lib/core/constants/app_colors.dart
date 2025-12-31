import 'package:flutter/material.dart';

/// App color palette matching the original Android app theme
class AppColors {
  // Primary colors from Android values/colors.xml
  static const Color primary = Color(0xFF399DC5);        // blue
  static const Color primaryDark = Color(0xFF074561);
  static const Color secondary = Color(0xFF7AC4E1);      // lblue
  static const Color accent = Color(0xFF0B5761);         // ddblue

  // Additional colors
  static const Color lightGreen = Color(0xFF03DAC5);     // lgreen
  static const Color gray = Color(0xFFAAAEAE);
  static const Color background = Color(0xFFF5F5F5);
  static const Color cardBackground = Colors.white;

  // Feedback colors
  static const Color correct = Color(0xFF55A643);        // Green for correct answers
  static const Color wrong = Color(0xFFCD1026);          // Red for wrong answers

  // Gradient colors for buttons (matching Android btn1, btn2, btn3)
  static const LinearGradient gradient1 = LinearGradient(
    colors: [Color(0xFFEF4444), Color(0xFF0000FF)],
    begin: Alignment.topLeft,
    end: Alignment.bottomRight,
  );

  static const LinearGradient gradient2 = LinearGradient(
    colors: [Color(0xFF800080), Color(0xFF808000)],
    begin: Alignment.topLeft,
    end: Alignment.bottomRight,
  );

  static const LinearGradient gradient3 = LinearGradient(
    colors: [Color(0xFF0000FF), Color(0xFF808000)],
    begin: Alignment.topLeft,
    end: Alignment.bottomRight,
  );

  // Prevent instantiation
  AppColors._();
}
