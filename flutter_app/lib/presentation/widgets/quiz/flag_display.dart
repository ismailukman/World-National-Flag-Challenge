import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../data/repositories/settings_repository.dart';

/// Widget to display the flag image in the quiz
class FlagDisplay extends StatelessWidget {
  final String flagImagePath;

  const FlagDisplay({
    super.key,
    required this.flagImagePath,
  });

  @override
  Widget build(BuildContext context) {
    final settingsRepo = context.read<SettingsRepository>();
    final style = settingsRepo.getFlagStyle();
    final isForcedWaving = flagImagePath.contains('cape_verde.gif') ||
        flagImagePath.contains('south_sudan.gif');
    final displayPath = (style == 'waving' || isForcedWaving)
        ? flagImagePath.replaceFirst('.gif', '_waving.gif')
        : flagImagePath;

    return Center(
      child: ConstrainedBox(
        constraints: const BoxConstraints(maxWidth: 240),
        child: Container(
          margin: const EdgeInsets.symmetric(vertical: 8.0),
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(12),
            boxShadow: [
              BoxShadow(
                color: Colors.black.withOpacity(0.2),
                blurRadius: 10,
                offset: const Offset(0, 4),
              ),
            ],
          ),
          child: ClipRRect(
            borderRadius: BorderRadius.circular(12),
            child: SizedBox(
              width: double.infinity,
              height: 130,
              child: Padding(
                padding: const EdgeInsets.all(16),
                child: Image.asset(
                  displayPath,
                  fit: BoxFit.contain,
                  filterQuality: FilterQuality.high,
                  errorBuilder: (context, error, stackTrace) {
                    return Container(
                      color: Colors.grey[300],
                      child: const Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(
                            Icons.flag,
                            size: 64,
                            color: Colors.grey,
                          ),
                          SizedBox(height: 8),
                          Text(
                            'Flag not found',
                            style: TextStyle(color: Colors.grey),
                          ),
                        ],
                      ),
                    );
                  },
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
