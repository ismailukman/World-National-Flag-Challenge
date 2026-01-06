import 'dart:io';
import 'package:flutter/foundation.dart';

/// Performance tier for device capability detection
enum PerformanceTier {
  high,
  medium,
  low,
}

/// Detects device performance capabilities for optimizing 3D rendering
class DevicePerformanceDetector {
  static PerformanceTier? _cachedTier;

  /// Detects the performance tier of the current device
  static PerformanceTier detectPerformanceTier() {
    // Return cached result if available
    if (_cachedTier != null) {
      return _cachedTier!;
    }

    // Web platform always gets medium quality (conservative approach)
    if (kIsWeb) {
      _cachedTier = PerformanceTier.medium;
      return _cachedTier!;
    }

    // iOS devices: generally high performance
    if (Platform.isIOS) {
      _cachedTier = PerformanceTier.high;
      return _cachedTier!;
    }

    // Android devices: more varied, use conservative defaults
    // In production, you could check:
    // - Number of processor cores
    // - Available memory
    // - GPU capabilities
    // - Android API level
    if (Platform.isAndroid) {
      // For now, default to medium for stability
      // TODO: Implement more sophisticated Android performance detection
      _cachedTier = PerformanceTier.medium;
      return _cachedTier!;
    }

    // Desktop platforms: assume high performance
    if (Platform.isMacOS || Platform.isWindows || Platform.isLinux) {
      _cachedTier = PerformanceTier.high;
      return _cachedTier!;
    }

    // Fallback to low tier for unknown platforms
    _cachedTier = PerformanceTier.low;
    return _cachedTier!;
  }

  /// Resets the cached performance tier (useful for testing)
  static void resetCache() {
    _cachedTier = null;
  }

  /// Returns recommended 3D model quality based on performance tier
  static String getRecommendedModelQuality(PerformanceTier tier) {
    switch (tier) {
      case PerformanceTier.high:
        return 'high';
      case PerformanceTier.medium:
        return 'medium';
      case PerformanceTier.low:
        return 'low';
    }
  }

  /// Returns recommended frame rate target based on performance tier
  static int getTargetFrameRate(PerformanceTier tier) {
    switch (tier) {
      case PerformanceTier.high:
        return 60;
      case PerformanceTier.medium:
        return 30;
      case PerformanceTier.low:
        return 24;
    }
  }
}
