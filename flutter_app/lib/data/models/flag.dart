import 'package:equatable/equatable.dart';

/// Flag model representing a country's flag with localization support
class Flag extends Equatable {
  final String id;
  final String countryName;
  final String countryCode;
  final Map<String, String> nameTranslations;
  final String flagImagePath;
  final String? capital;
  final String? difficulty;
  final String? funFact;

  const Flag({
    required this.id,
    required this.countryName,
    required this.countryCode,
    required this.nameTranslations,
    required this.flagImagePath,
    this.capital,
    this.difficulty,
    this.funFact,
  });

  /// Get localized country name based on language code
  String getLocalizedName(String languageCode) {
    return nameTranslations[languageCode] ?? countryName;
  }

  /// Create Flag from JSON
  factory Flag.fromJson(Map<String, dynamic> json) {
    return Flag(
      id: json['id'] as String,
      countryName: json['countryName'] as String,
      countryCode: json['countryCode'] as String,
      nameTranslations: json['nameTranslations'] != null
          ? Map<String, String>.from(json['nameTranslations'] as Map)
          : {'en': json['countryName'] as String},
      flagImagePath: json['flagImage'] as String,
      capital: json['capital'] as String?,
      difficulty: json['difficulty'] as String?,
      funFact: json['funFact'] as String?,
    );
  }

  /// Convert Flag to JSON
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'countryName': countryName,
      'countryCode': countryCode,
      'nameTranslations': nameTranslations,
      'flagImage': flagImagePath,
      if (capital != null) 'capital': capital,
      if (difficulty != null) 'difficulty': difficulty,
      if (funFact != null) 'funFact': funFact,
    };
  }

  /// Create a copy with modified fields
  Flag copyWith({
    String? id,
    String? countryName,
    String? countryCode,
    Map<String, String>? nameTranslations,
    String? flagImagePath,
    String? capital,
    String? difficulty,
    String? funFact,
  }) {
    return Flag(
      id: id ?? this.id,
      countryName: countryName ?? this.countryName,
      countryCode: countryCode ?? this.countryCode,
      nameTranslations: nameTranslations ?? this.nameTranslations,
      flagImagePath: flagImagePath ?? this.flagImagePath,
      capital: capital ?? this.capital,
      difficulty: difficulty ?? this.difficulty,
      funFact: funFact ?? this.funFact,
    );
  }

  @override
  List<Object?> get props => [id, countryCode];

  @override
  String toString() => 'Flag(id: $id, name: $countryName, code: $countryCode)';
}
