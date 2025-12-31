import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../data/models/flag.dart';
import '../../../data/repositories/settings_repository.dart';

/// Practice flag card widget with flag details and bookmark
class PracticeFlagCard extends StatelessWidget {
  final Flag flag;
  final bool isBookmarked;
  final VoidCallback onBookmarkToggle;

  const PracticeFlagCard({
    super.key,
    required this.flag,
    required this.isBookmarked,
    required this.onBookmarkToggle,
  });

  @override
  Widget build(BuildContext context) {
    final languageCode = Localizations.localeOf(context).languageCode;
    final settingsRepo = context.read<SettingsRepository>();
    final style = settingsRepo.getFlagStyle();
    final displayPath = style == 'waving'
        ? flag.flagImagePath.replaceFirst('.gif', '_waving.gif')
        : flag.flagImagePath;

    return Card(
      margin: const EdgeInsets.only(bottom: 16.0),
      elevation: 4,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12),
      ),
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Flag image and bookmark
            Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // Flag image
                ClipRRect(
                  borderRadius: BorderRadius.circular(8),
                  child: SizedBox(
                    width: 120,
                    height: 80,
                    child: Padding(
                      padding: const EdgeInsets.all(6),
                      child: Image.asset(
                        displayPath,
                        fit: BoxFit.contain,
                        errorBuilder: (context, error, stackTrace) {
                          return Container(
                            color: Colors.grey[300],
                            child: const Icon(
                              Icons.flag,
                              size: 40,
                              color: Colors.grey,
                            ),
                          );
                        },
                      ),
                    ),
                  ),
                ),

                const SizedBox(width: 16),

                // Country name and code
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        flag.getLocalizedName(languageCode),
                        style: const TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        flag.countryCode,
                        style: TextStyle(
                          fontSize: 14,
                          color: Colors.grey[600],
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                    ],
                  ),
                ),

                // Bookmark button
                IconButton(
                  icon: Icon(
                    isBookmarked ? Icons.bookmark : Icons.bookmark_border,
                    color: isBookmarked ? Colors.amber : Colors.grey,
                  ),
                  onPressed: onBookmarkToggle,
                  tooltip: isBookmarked ? 'Remove bookmark' : 'Add bookmark',
                ),
              ],
            ),

            // Capital (if available)
            if (flag.capital != null && flag.capital!.isNotEmpty) ...[
              const SizedBox(height: 12),
              const Divider(),
              const SizedBox(height: 8),
              Row(
                children: [
                  Icon(
                    Icons.location_city,
                    size: 18,
                    color: Colors.grey[600],
                  ),
                  const SizedBox(width: 8),
                  Text(
                    'Capital: ',
                    style: TextStyle(
                      fontSize: 14,
                      fontWeight: FontWeight.bold,
                      color: Colors.grey[700],
                    ),
                  ),
                  Expanded(
                    child: Text(
                      flag.capital!,
                      style: const TextStyle(fontSize: 14),
                    ),
                  ),
                ],
              ),
            ],

            // Fun fact (if available)
            if (flag.funFact != null && flag.funFact!.isNotEmpty) ...[
              const SizedBox(height: 8),
              Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Icon(
                    Icons.lightbulb_outline,
                    size: 18,
                    color: Colors.amber[700],
                  ),
                  const SizedBox(width: 8),
                  Expanded(
                    child: Text(
                      flag.funFact!,
                      style: TextStyle(
                        fontSize: 13,
                        color: Colors.grey[700],
                        fontStyle: FontStyle.italic,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ],
        ),
      ),
    );
  }
}
