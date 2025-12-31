import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';

/// States for language selection
abstract class LanguageState extends Equatable {
  const LanguageState();

  @override
  List<Object?> get props => [];
}

/// Initial state
class LanguageInitial extends LanguageState {
  const LanguageInitial();
}

/// Language loaded from settings
class LanguageLoaded extends LanguageState {
  final Locale locale;

  const LanguageLoaded(this.locale);

  @override
  List<Object?> get props => [locale];
}

/// Language changed by user
class LanguageChanged extends LanguageState {
  final Locale locale;

  const LanguageChanged(this.locale);

  @override
  List<Object?> get props => [locale];
}
