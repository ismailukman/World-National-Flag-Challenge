import 'dart:math' as math;
import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import '../../../data/models/flag.dart';
import 'flag_cache_manager.dart';

/// A widget that displays a flag image with optional high-resolution loading and wave animation
/// Supports both local assets and remote FlagsAPI images with automatic fallback
class AnimatedFlagWidget extends StatefulWidget {
  final Flag flag;
  final double? width;
  final double? height;
  final bool useHighResolution;
  final BoxFit fit;
  final bool enableWaveAnimation;
  final double waveIntensity;

  const AnimatedFlagWidget({
    super.key,
    required this.flag,
    this.width,
    this.height,
    this.useHighResolution = true,
    this.fit = BoxFit.contain,
    this.enableWaveAnimation = true,
    this.waveIntensity = 0.015,
  });

  @override
  State<AnimatedFlagWidget> createState() => _AnimatedFlagWidgetState();
}

class _AnimatedFlagWidgetState extends State<AnimatedFlagWidget>
    with SingleTickerProviderStateMixin {
  late AnimationController _waveController;

  @override
  void initState() {
    super.initState();
    _waveController = AnimationController(
      vsync: this,
      duration: const Duration(seconds: 3),
    );

    if (widget.enableWaveAnimation) {
      _waveController.repeat();
    }
  }

  @override
  void dispose() {
    _waveController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final flagWidget = widget.useHighResolution
        ? _buildHighResFlag()
        : _buildLocalFlag();

    // Add wave animation if enabled
    if (widget.enableWaveAnimation) {
      return AnimatedBuilder(
        animation: _waveController,
        builder: (context, child) {
          return ClipRect(
            child: Transform(
              alignment: Alignment.center,
              transform: Matrix4.identity()
                ..setEntry(3, 2, 0.001) // Add perspective
                ..rotateY(math.sin(_waveController.value * 2 * math.pi) * widget.waveIntensity),
              child: child,
            ),
          );
        },
        child: flagWidget,
      );
    }

    return flagWidget;
  }

  /// Build high-resolution flag from FlagsAPI with local fallback
  Widget _buildHighResFlag() {
    final flagUrl = FlagCacheManager.getFlagUrl(
      widget.flag.countryCode.toLowerCase(),
      size: 256,
    );

    return CachedNetworkImage(
      imageUrl: flagUrl,
      width: widget.width,
      height: widget.height,
      fit: widget.fit,
      placeholder: (context, url) => _buildLoadingPlaceholder(),
      errorWidget: (context, url, error) => _buildLocalFlag(),
    );
  }

  /// Build local asset flag image
  Widget _buildLocalFlag() {
    return Image.asset(
      widget.flag.flagImagePath,
      width: widget.width,
      height: widget.height,
      fit: widget.fit,
    );
  }

  /// Build loading placeholder (shows local flag while loading)
  Widget _buildLoadingPlaceholder() {
    return Opacity(
      opacity: 0.7,
      child: _buildLocalFlag(),
    );
  }
}
