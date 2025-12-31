import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../../blocs/language/language_bloc.dart';
import '../../blocs/language/language_event.dart';
import '../../blocs/language/language_state.dart';

/// Language selector widget for home screen
class LanguageSelector extends StatelessWidget {
  const LanguageSelector({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<LanguageBloc, LanguageState>(
      builder: (context, state) {
        String currentLanguage = 'en';
        if (state is LanguageLoaded) {
          currentLanguage = state.locale.languageCode;
        } else if (state is LanguageChanged) {
          currentLanguage = state.locale.languageCode;
        }

        return IconButton(
          icon: const Icon(
            Icons.language,
            color: Colors.white,
            size: 28,
          ),
          onPressed: () => _selectLanguage(context, currentLanguage),
          tooltip: 'Select language',
        );
      },
    );
  }

  Future<void> _selectLanguage(
    BuildContext context,
    String currentLanguage,
  ) async {
    final selectedLanguage = await showDialog<String>(
      context: context,
      builder: (dialogContext) => AlertDialog(
        title: const Text('Select Language'),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            _buildLanguageOption(
              dialogContext,
              'English',
              'en',
              currentLanguage,
              '🇬🇧',
            ),
            _buildLanguageOption(
              dialogContext,
              'العربية',
              'ar',
              currentLanguage,
              '🇸🇦',
            ),
            _buildLanguageOption(
              dialogContext,
              'Français',
              'fr',
              currentLanguage,
              '🇫🇷',
            ),
            _buildLanguageOption(
              dialogContext,
              'Türkçe',
              'tr',
              currentLanguage,
              '🇹🇷',
            ),
          ],
        ),
      ),
    );

    if (selectedLanguage != null && selectedLanguage != currentLanguage) {
      if (context.mounted) {
        context.read<LanguageBloc>().add(LanguageSelected(selectedLanguage));

        final languageNames = {
          'en': 'English',
          'ar': 'العربية',
          'fr': 'Français',
          'tr': 'Türkçe',
        };

        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Language changed to ${languageNames[selectedLanguage]}'),
            duration: const Duration(seconds: 2),
          ),
        );
      }
    }
  }

  Widget _buildLanguageOption(
    BuildContext context,
    String name,
    String code,
    String currentLanguage,
    String flag,
  ) {
    final isSelected = code == currentLanguage;

    return ListTile(
      title: Row(
        children: [
          Text(flag, style: const TextStyle(fontSize: 24)),
          const SizedBox(width: 12),
          Text(name),
        ],
      ),
      leading: Icon(
        isSelected ? Icons.check_circle : Icons.circle_outlined,
        color: isSelected ? const Color(0xFF399DC5) : Colors.grey,
      ),
      selected: isSelected,
      onTap: () => Navigator.pop(context, code),
    );
  }
}
