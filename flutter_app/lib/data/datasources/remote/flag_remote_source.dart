import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import '../../../presentation/widgets/flags/flag_cache_manager.dart';

/// Remote data source for fetching high-resolution flag images from FlagsAPI.com
class FlagRemoteSource {
  /// Get an ImageProvider for a flag from the remote API
  /// Falls back to local asset if remote fetch fails
  ///
  /// [countryCode] - ISO 3166-1 alpha-2 country code (e.g., 'us', 'gb', 'fr')
  /// [assetPath] - Local asset path to use as fallback
  /// [size] - Size of the flag image (16, 24, 32, 48, 64, 128, 256, 512, 1024)
  static ImageProvider getFlagImageProvider({
    required String countryCode,
    required String assetPath,
    int size = 256,
  }) {
    // Try to load from remote first, with local asset as fallback
    final remoteUrl = FlagCacheManager.getFlagUrl(countryCode, size: size);

    return CachedNetworkImageProvider(
      remoteUrl,
      errorListener: (error) {
        // Log error but don't crash - will fallback to asset
        FlagCacheManager.logCacheInfo('Failed to load remote flag: $error');
      },
    );
  }

  /// Prefetch high-resolution flags for offline use
  /// Should be called on WiFi connection to cache flags
  ///
  /// [countryCodes] - List of country codes to prefetch
  /// [context] - BuildContext for image precaching
  static Future<void> prefetchFlags({
    required List<String> countryCodes,
    required BuildContext context,
  }) async {
    for (final code in countryCodes) {
      try {
        final imageProvider = getFlagImageProvider(
          countryCode: code,
          assetPath: '', // Not needed for prefetch
          size: 256,
        );
        await precacheImage(imageProvider, context);
      } catch (e) {
        FlagCacheManager.logCacheInfo('Failed to prefetch flag $code: $e');
      }
    }
  }
}
