import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../data/repositories/settings_repository.dart';
import 'language_event.dart';
import 'language_state.dart';

/// BLoC for managing language selection
class LanguageBloc extends Bloc<LanguageEvent, LanguageState> {
  final SettingsRepository settingsRepository;

  LanguageBloc({required this.settingsRepository})
      : super(const LanguageInitial()) {
    on<LoadLanguage>(_onLoadLanguage);
    on<LanguageSelected>(_onLanguageSelected);
  }

  /// Load saved language from settings
  Future<void> _onLoadLanguage(
    LoadLanguage event,
    Emitter<LanguageState> emit,
  ) async {
    final languageCode = settingsRepository.getLanguage();
    final locale = Locale(languageCode);
    emit(LanguageLoaded(locale));
  }

  /// Handle language selection
  Future<void> _onLanguageSelected(
    LanguageSelected event,
    Emitter<LanguageState> emit,
  ) async {
    // Save language preference
    await settingsRepository.saveLanguage(event.languageCode);

    // Emit new locale
    final locale = Locale(event.languageCode);
    emit(LanguageChanged(locale));
  }

  /// Get current locale
  Locale? get currentLocale {
    if (state is LanguageLoaded) {
      return (state as LanguageLoaded).locale;
    } else if (state is LanguageChanged) {
      return (state as LanguageChanged).locale;
    }
    return null;
  }
}
