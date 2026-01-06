import 'package:logger/logger.dart';

/// Cache manager for flag images from FlagsAPI
/// Flags are cached automatically by cached_network_image package
class FlagCacheManager {
  static final _logger = Logger();

  /// Get flag image URL from FlagsAPI.com
  /// Code parameter should be ISO 3166-1 alpha-2 country code (lowercase)
  /// Example: getFlagUrl('us') returns 'https://flagsapi.com/US/flat/64.png'
  static String getFlagUrl(String countryCode, {int size = 256}) {
    // FlagsAPI uses uppercase country codes
    final code = countryCode.toUpperCase();
    // Available sizes: 16, 24, 32, 48, 64, 128, 256, 512, 1024
    return 'https://flagsapi.com/$code/flat/$size.png';
  }

  /// Log cache information
  static void logCacheInfo(String message) {
    _logger.d(message);
  }
}
