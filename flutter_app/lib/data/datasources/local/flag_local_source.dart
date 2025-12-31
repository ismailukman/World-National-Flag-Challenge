import 'dart:convert';
import 'package:flutter/services.dart';
import '../../models/continent.dart';
import '../../models/flag.dart';

/// Local data source for loading flags from JSON asset
class FlagLocalSource {
  static const String _flagsJsonPath = 'assets/data/flags_data.json';

  List<Continent>? _cachedContinents;

  /// Load all continents and flags from JSON
  Future<List<Continent>> loadContinents() async {
    // Return cached data if available
    if (_cachedContinents != null) {
      return _cachedContinents!;
    }

    try {
      // Load JSON string from assets
      final jsonString = await rootBundle.loadString(_flagsJsonPath);

      // Parse JSON
      final Map<String, dynamic> jsonData = json.decode(jsonString);

      // Extract continents array
      final List<dynamic> continentsJson = jsonData['continents'] as List<dynamic>;

      // Convert to Continent objects
      _cachedContinents = continentsJson
          .map((continentJson) => Continent.fromJson(continentJson as Map<String, dynamic>))
          .toList();

      return _cachedContinents!;
    } catch (e) {
      throw Exception('Failed to load flags data: $e');
    }
  }

  /// Get a specific continent by ID
  Future<Continent> getContinentById(String continentId) async {
    final continents = await loadContinents();

    try {
      return continents.firstWhere(
        (continent) => continent.id == continentId,
      );
    } catch (e) {
      throw Exception('Continent not found: $continentId');
    }
  }

  /// Get all flags for a specific continent
  Future<List<Flag>> getFlagsByContinent(String continentId) async {
    final continent = await getContinentById(continentId);
    return continent.flags;
  }

  /// Get a specific flag by ID and continent
  Future<Flag> getFlagById(String continentId, String flagId) async {
    final flags = await getFlagsByContinent(continentId);

    try {
      return flags.firstWhere(
        (flag) => flag.id == flagId,
      );
    } catch (e) {
      throw Exception('Flag not found: $flagId in $continentId');
    }
  }

  /// Get total number of flags across all continents
  Future<int> getTotalFlagCount() async {
    final continents = await loadContinents();
    return continents.fold<int>(
      0,
      (sum, continent) => sum + continent.flags.length,
    );
  }

  /// Search flags by country name (case-insensitive)
  Future<List<Flag>> searchFlags(String query) async {
    if (query.isEmpty) return [];

    final continents = await loadContinents();
    final allFlags = continents.expand((continent) => continent.flags).toList();

    final lowerQuery = query.toLowerCase();

    return allFlags.where((flag) {
      return flag.countryName.toLowerCase().contains(lowerQuery) ||
          flag.countryCode.toLowerCase().contains(lowerQuery);
    }).toList();
  }

  /// Clear cached data (useful for testing or force refresh)
  void clearCache() {
    _cachedContinents = null;
  }
}
