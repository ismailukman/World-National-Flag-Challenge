import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';
import '../../../core/constants/app_routes.dart';
import '../../blocs/language/language_bloc.dart';
import '../../blocs/language/language_event.dart';
import '../../blocs/language/language_state.dart';

/// Language selection screen (replaces Main.kt from Android)
class LanguageSelectionScreen extends StatelessWidget {
  const LanguageSelectionScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [
              Color(0xFF399DC5), // Primary blue
              Color(0xFF0B5761), // Accent dark blue
            ],
          ),
        ),
        child: SafeArea(
          child: LayoutBuilder(
            builder: (context, constraints) {
              return SingleChildScrollView(
                padding: const EdgeInsets.all(24.0),
                child: ConstrainedBox(
                  constraints: BoxConstraints(
                    minHeight: constraints.maxHeight - 48,
                  ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                // App title
                const Text(
                  'World Nation Flag\nChallenge',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 32,
                    fontWeight: FontWeight.bold,
                    color: Colors.white,
                    height: 1.2,
                  ),
                ),
                const SizedBox(height: 48),

                // App logo
                Container(
                  width: 150,
                  height: 150,
                  decoration: BoxDecoration(
                    color: Colors.white,
                    shape: BoxShape.circle,
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black.withOpacity(0.2),
                        blurRadius: 10,
                        offset: const Offset(0, 5),
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
                const SizedBox(height: 48),

                // Language selection title
                const Text(
                  'Select Default Language',
                  style: TextStyle(
                    fontSize: 18,
                    color: Colors.white,
                    fontWeight: FontWeight.w500,
                  ),
                ),
                const SizedBox(height: 24),

                // Language options
                _buildLanguageCard(
                  context: context,
                  languageCode: 'en',
                  languageName: 'English',
                  flag: '🇬🇧',
                ),
                const SizedBox(height: 12),
                _buildLanguageCard(
                  context: context,
                  languageCode: 'ar',
                  languageName: 'العربية',
                  flag: '🇸🇦',
                ),
                const SizedBox(height: 12),
                _buildLanguageCard(
                  context: context,
                  languageCode: 'fr',
                  languageName: 'Français',
                  flag: '🇫🇷',
                ),
                const SizedBox(height: 12),
                _buildLanguageCard(
                  context: context,
                  languageCode: 'tr',
                  languageName: 'Türkçe',
                  flag: '🇹🇷',
                ),
                const SizedBox(height: 48),

                // Footer
                const Text(
                  'by Ismaila Lukman Enegi',
                  style: TextStyle(
                    fontSize: 14,
                    color: Colors.white70,
                  ),
                ),
              ],
                  ),
                ),
              );
            },
          ),
        ),
      ),
    );
  }

  Widget _buildLanguageCard({
    required BuildContext context,
    required String languageCode,
    required String languageName,
    required String flag,
  }) {
    return BlocBuilder<LanguageBloc, LanguageState>(
      builder: (context, state) {
        final isSelected = state is LanguageLoaded &&
            state.locale.languageCode == languageCode;

        return Card(
          elevation: isSelected ? 8 : 4,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
            side: isSelected
                ? const BorderSide(color: Colors.white, width: 2)
                : BorderSide.none,
          ),
          child: InkWell(
            borderRadius: BorderRadius.circular(12),
            onTap: () {
              // Select language
              context.read<LanguageBloc>().add(LanguageSelected(languageCode));

              // Navigate to home after a short delay
              Future.delayed(const Duration(milliseconds: 300), () {
                if (context.mounted) {
                  context.go(AppRoutes.home);
                }
              });
            },
            child: Padding(
              padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 20),
              child: Row(
                children: [
                  // Flag emoji
                  Text(
                    flag,
                    style: const TextStyle(fontSize: 32),
                  ),
                  const SizedBox(width: 16),

                  // Language name
                  Expanded(
                    child: Text(
                      languageName,
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ),

                  // Selected indicator
                  if (isSelected)
                    const Icon(
                      Icons.check_circle,
                      color: Color(0xFF399DC5),
                      size: 28,
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
