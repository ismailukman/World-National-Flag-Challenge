import 'package:flutter/material.dart';
import 'dart:math' as math;

/// Variant of globe display
enum GlobeVariant {
  home, // For continent cards on home screen
  celebration, // For achievement celebrations
}

/// Lightweight 2D globe widget (no 3D engine)
class Globe3DWidget extends StatefulWidget {
  final double size;
  final bool autoRotate;
  final double rotationSpeed;
  final GlobeVariant variant;

  const Globe3DWidget({
    super.key,
    this.size = 32,
    this.autoRotate = true,
    this.rotationSpeed = 1.0,
    this.variant = GlobeVariant.home,
  });

  @override
  State<Globe3DWidget> createState() => _Globe3DWidgetState();
}

class _Globe3DWidgetState extends State<Globe3DWidget>
    with SingleTickerProviderStateMixin {
  late final AnimationController _controller;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: (6000 / widget.rotationSpeed).round()),
    );
    if (widget.autoRotate) {
      _controller.repeat();
    }
  }

  @override
  void didUpdateWidget(covariant Globe3DWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (oldWidget.rotationSpeed != widget.rotationSpeed) {
      _controller.duration =
          Duration(milliseconds: (6000 / widget.rotationSpeed).round());
      if (widget.autoRotate) {
        _controller.repeat();
      }
    }
    if (oldWidget.autoRotate != widget.autoRotate) {
      if (widget.autoRotate) {
        _controller.repeat();
      } else {
        _controller.stop();
      }
    }
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: widget.size,
      height: widget.size,
      child: AnimatedBuilder(
        animation: _controller,
        builder: (context, child) {
          final scale = 1.0 + 0.04 * math.sin(_controller.value * 2 * math.pi);
          return Transform.scale(
            scale: scale,
            child: CustomPaint(
              painter: _GlobePainter(
                progress: _controller.value,
              ),
            ),
          );
        },
      ),
    );
  }
}

class _GlobePainter extends CustomPainter {
  final double progress;

  _GlobePainter({required this.progress});

  @override
  void paint(Canvas canvas, Size size) {
    final center = Offset(size.width / 2, size.height / 2);
    final radius = math.min(size.width, size.height) / 2;
    final rect = Rect.fromCircle(center: center, radius: radius);

    final oceanPaint = Paint()
      ..shader = const RadialGradient(
        center: Alignment(-0.4, -0.3),
        colors: [
          Color(0xFF7FD2F5),
          Color(0xFF2B8FC5),
          Color(0xFF0B4C7A),
        ],
        stops: [0.0, 0.55, 1.0],
      ).createShader(rect);

    canvas.drawCircle(center, radius, oceanPaint);

    canvas.save();
    canvas.clipPath(Path()..addOval(rect));

    final gridPaint = Paint()
      ..color = Colors.white.withOpacity(0.18)
      ..style = PaintingStyle.stroke
      ..strokeWidth = radius * 0.02;

    // Latitude lines
    for (final lat in [-60, -30, 0, 30, 60]) {
      final latRad = lat * math.pi / 180;
      final height = radius * 2 * math.cos(latRad).abs();
      final oval = Rect.fromCenter(
        center: center,
        width: radius * 2,
        height: height,
      );
      canvas.drawOval(oval, gridPaint);
    }

    // Longitude lines (simulate rotation by shifting phase)
    final phase = progress * 2 * math.pi;
    const longitudes = 6;
    for (int i = 0; i < longitudes; i++) {
      final angle = (i / longitudes) * math.pi + phase;
      final cosVal = math.cos(angle);
      final width = radius * 2 * cosVal.abs();
      final opacity = 0.08 + 0.22 * cosVal.clamp(0.0, 1.0);
      final paint = gridPaint..color = Colors.white.withOpacity(opacity);
      final oval = Rect.fromCenter(
        center: center,
        width: width,
        height: radius * 2,
      );
      canvas.drawOval(oval, paint);
    }

    // Subtle shadow vignette for depth
    final shadowPaint = Paint()
      ..shader = const RadialGradient(
        center: Alignment(0.4, 0.4),
        colors: [
          Color(0x00000000),
          Color(0x55000000),
        ],
        stops: [0.6, 1.0],
      ).createShader(rect);
    canvas.drawCircle(center, radius, shadowPaint);

    canvas.restore();
  }

  @override
  bool shouldRepaint(covariant _GlobePainter oldDelegate) {
    return oldDelegate.progress != progress;
  }
}
