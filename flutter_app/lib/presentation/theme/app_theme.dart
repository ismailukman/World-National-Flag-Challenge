import 'package:flutter/material.dart';
import '../../core/constants/app_colors.dart';
import '../../core/constants/app_dimensions.dart';

/// Application theme configuration
class AppTheme {
  static ThemeData get lightTheme {
    return ThemeData(
      useMaterial3: true,

      // Color scheme
      colorScheme: ColorScheme.fromSeed(
        seedColor: AppColors.primary,
        primary: AppColors.primary,
        secondary: AppColors.secondary,
        tertiary: AppColors.accent,
        surface: AppColors.cardBackground,
        background: AppColors.background,
      ),

      // App bar theme
      appBarTheme: AppBarTheme(
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
        elevation: 4,
        centerTitle: true,
        titleTextStyle: TextStyle(
          fontFamily: 'Rubik',
          fontSize: 20,
          fontWeight: FontWeight.bold,
          color: Colors.white,
        ),
      ),

      // Card theme (matching Android MaterialCardView)
      cardTheme: CardThemeData(
        elevation: AppDimensions.cardElevation,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(AppDimensions.cardBorderRadius),
        ),
        color: AppColors.cardBackground,
      ),

      // Elevated button theme
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          padding: EdgeInsets.symmetric(
            vertical: AppDimensions.paddingMedium,
            horizontal: AppDimensions.paddingLarge,
          ),
          textStyle: TextStyle(
            fontFamily: 'Rubik',
            fontSize: 18,
            fontWeight: FontWeight.w600,
          ),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(AppDimensions.buttonBorderRadius),
          ),
          elevation: AppDimensions.buttonElevation,
        ),
      ),

      // Text button theme
      textButtonTheme: TextButtonThemeData(
        style: TextButton.styleFrom(
          textStyle: TextStyle(
            fontFamily: 'Rubik',
            fontSize: 16,
            fontWeight: FontWeight.w500,
          ),
        ),
      ),

      // Input decoration theme
      inputDecorationTheme: InputDecorationTheme(
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppDimensions.buttonBorderRadius),
        ),
        filled: true,
        fillColor: Colors.white,
      ),

      // Typography
      textTheme: TextTheme(
        // Display styles (large titles)
        displayLarge: TextStyle(
          fontFamily: 'BerkshireSwash',
          fontSize: 32,
          fontWeight: FontWeight.bold,
          color: Colors.black87,
        ),
        displayMedium: TextStyle(
          fontFamily: 'BerkshireSwash',
          fontSize: 28,
          fontWeight: FontWeight.bold,
          color: Colors.black87,
        ),

        // Headline styles (headings)
        headlineLarge: TextStyle(
          fontFamily: 'BlackOpsOne',
          fontSize: 24,
          color: Colors.black87,
        ),
        headlineMedium: TextStyle(
          fontFamily: 'Rubik',
          fontSize: 20,
          fontWeight: FontWeight.bold,
          color: Colors.black87,
        ),

        // Title styles (subtitles)
        titleLarge: TextStyle(
          fontFamily: 'Rubik',
          fontSize: 18,
          fontWeight: FontWeight.w600,
          color: Colors.black87,
        ),
        titleMedium: TextStyle(
          fontFamily: 'Rubik',
          fontSize: 16,
          fontWeight: FontWeight.w500,
          color: Colors.black87,
        ),

        // Body text
        bodyLarge: TextStyle(
          fontFamily: 'Rubik',
          fontSize: 16,
          color: Colors.black87,
        ),
        bodyMedium: TextStyle(
          fontFamily: 'Rubik',
          fontSize: 14,
          color: Colors.black87,
        ),

        // Labels
        labelLarge: TextStyle(
          fontFamily: 'Rubik',
          fontSize: 14,
          fontWeight: FontWeight.w500,
          color: Colors.black87,
        ),
      ),

      // Page transitions
      pageTransitionsTheme: PageTransitionsTheme(
        builders: {
          TargetPlatform.android: ZoomPageTransitionsBuilder(),
          TargetPlatform.iOS: CupertinoPageTransitionsBuilder(),
        },
      ),
    );
  }

  // Prevent instantiation
  AppTheme._();
}
