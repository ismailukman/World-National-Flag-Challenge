import 'package:equatable/equatable.dart';

/// Events for language selection
abstract class LanguageEvent extends Equatable {
  const LanguageEvent();

  @override
  List<Object?> get props => [];
}

/// Load saved language from settings
class LoadLanguage extends LanguageEvent {
  const LoadLanguage();
}

/// User selected a new language
class LanguageSelected extends LanguageEvent {
  final String languageCode;

  const LanguageSelected(this.languageCode);

  @override
  List<Object?> get props => [languageCode];
}
