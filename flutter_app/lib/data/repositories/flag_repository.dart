import '../models/continent.dart';
import '../models/flag.dart';
import '../datasources/local/flag_local_source.dart';

/// Repository for accessing flag data
class FlagRepository {
  final FlagLocalSource _localSource;

  FlagRepository({FlagLocalSource? localSource})
      : _localSource = localSource ?? FlagLocalSource();

  /// Load all continents with their flags
  Future<List<Continent>> getAllContinents() async {
    return await _localSource.loadContinents();
  }

  /// Get a specific continent by ID
  Future<Continent> getContinentById(String continentId) async {
    return await _localSource.getContinentById(continentId);
  }

  /// Get all flags for a specific continent
  Future<List<Flag>> getFlagsByContinent(String continentId) async {
    return await _localSource.getFlagsByContinent(continentId);
  }

  /// Get a specific flag by ID
  Future<Flag> getFlagById(String continentId, String flagId) async {
    return await _localSource.getFlagById(continentId, flagId);
  }

  /// Get total number of flags across all continents
  Future<int> getTotalFlagCount() async {
    return await _localSource.getTotalFlagCount();
  }

  /// Search flags by country name or code
  Future<List<Flag>> searchFlags(String query) async {
    return await _localSource.searchFlags(query);
  }

  /// Get random flags from a continent (for quiz generation)
  Future<List<Flag>> getRandomFlags(String continentId, int count) async {
    final allFlags = await getFlagsByContinent(continentId);
    final shuffled = List<Flag>.from(allFlags)..shuffle();
    return shuffled.take(count).toList();
  }

  /// Get flags from all continents (for daily challenge)
  Future<List<Flag>> getAllFlags() async {
    final continents = await getAllContinents();
    return continents.expand((continent) => continent.flags).toList();
  }

  /// Get random flags from all continents
  Future<List<Flag>> getRandomFlagsFromAllContinents(int count) async {
    final allFlags = await getAllFlags();
    final shuffled = List<Flag>.from(allFlags)..shuffle();
    return shuffled.take(count).toList();
  }

  /// Clear cache (useful for testing)
  void clearCache() {
    _localSource.clearCache();
  }
}
