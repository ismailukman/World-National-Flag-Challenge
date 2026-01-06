import 'package:equatable/equatable.dart';
import 'flag.dart';

/// Continent model containing flag collection and metadata
class Continent extends Equatable {
  final String id;
  final String name;
  final Map<String, String> nameTranslations;
  final int flagCount;
  final int minimumScore;
  final List<Flag> flags;
  final String? backgroundImage;

  const Continent({
    required this.id,
    required this.name,
    required this.nameTranslations,
    required this.flagCount,
    required this.minimumScore,
    required this.flags,
    this.backgroundImage,
  });

  /// Get localized continent name
  String getLocalizedName(String languageCode) {
    return nameTranslations[languageCode] ?? name;
  }

  /// Create Continent from JSON
  factory Continent.fromJson(Map<String, dynamic> json) {
    return Continent(
      id: json['id'] as String,
      name: json['name'] as String,
      nameTranslations: json['nameTranslations'] != null
          ? Map<String, String>.from(json['nameTranslations'] as Map)
          : {'en': json['name'] as String},
      flagCount: json['flagCount'] as int,
      minimumScore: json['minimumScore'] as int,
      flags: (json['flags'] as List<dynamic>?)
              ?.map((flagJson) => Flag.fromJson(flagJson as Map<String, dynamic>))
              .toList() ??
          [],
      backgroundImage: json['backgroundImage'] as String?,
    );
  }

  /// Convert Continent to JSON
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'nameTranslations': nameTranslations,
      'flagCount': flagCount,
      'minimumScore': minimumScore,
      'flags': flags.map((flag) => flag.toJson()).toList(),
      if (backgroundImage != null) 'backgroundImage': backgroundImage,
    };
  }

  @override
  List<Object?> get props => [id];

  @override
  String toString() => 'Continent(id: $id, name: $name, flags: ${flags.length})';
}

/// Continent enum for type-safe continent selection
enum ContinentType {
  africa,
  asia,
  europe,
  northAmerica,
  southAmerica,
  oceania;

  String get id {
    switch (this) {
      case ContinentType.africa:
        return 'africa';
      case ContinentType.asia:
        return 'asia';
      case ContinentType.europe:
        return 'europe';
      case ContinentType.northAmerica:
        return 'north_america';
      case ContinentType.southAmerica:
        return 'south_america';
      case ContinentType.oceania:
        return 'oceania';
    }
  }

  static ContinentType fromId(String id) {
    switch (id) {
      case 'africa':
        return ContinentType.africa;
      case 'asia':
        return ContinentType.asia;
      case 'europe':
        return ContinentType.europe;
      case 'north_america':
        return ContinentType.northAmerica;
      case 'south_america':
        return ContinentType.southAmerica;
      case 'oceania':
        return ContinentType.oceania;
      default:
        throw ArgumentError('Invalid continent ID: $id');
    }
  }
}
