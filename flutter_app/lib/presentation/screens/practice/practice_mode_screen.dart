import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';
import 'package:go_router/go_router.dart';
import '../../../core/constants/app_routes.dart';
import '../../../data/repositories/flag_repository.dart';
import '../../../data/repositories/settings_repository.dart';
import '../../blocs/practice/practice_bloc.dart';
import '../../blocs/practice/practice_event.dart';
import '../../blocs/practice/practice_state.dart';
import '../../widgets/practice/practice_flag_card.dart';

/// Practice Mode Screen - browse and study flags without pressure
class PracticeModeScreen extends StatelessWidget {
  final String? continentId;

  const PracticeModeScreen({
    super.key,
    this.continentId,
  });

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => PracticeBloc(
        flagRepository: context.read<FlagRepository>(),
        settingsRepository: context.read<SettingsRepository>(),
      )..add(LoadPracticeFlags(continentId: continentId)),
      child: _PracticeModeScreenContent(continentId: continentId),
    );
  }
}

class _PracticeModeScreenContent extends StatefulWidget {
  final String? continentId;

  const _PracticeModeScreenContent({this.continentId});

  @override
  State<_PracticeModeScreenContent> createState() =>
      _PracticeModeScreenContentState();
}

class _PracticeModeScreenContentState
    extends State<_PracticeModeScreenContent> {
  final TextEditingController _searchController = TextEditingController();
  bool _showBookmarkedOnly = false;

  @override
  void dispose() {
    _searchController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    return Scaffold(
      appBar: AppBar(
        title: Text(l10n.practice_mode),
        centerTitle: true,
        elevation: 0,
        leading: IconButton(
          icon: const Icon(Icons.home),
          tooltip: 'Back to Menu',
          onPressed: () => context.go(AppRoutes.home),
        ),
        actions: [
          // Bookmarked filter toggle
          IconButton(
            icon: Icon(
              _showBookmarkedOnly ? Icons.bookmark : Icons.bookmark_border,
            ),
            onPressed: () {
              setState(() {
                _showBookmarkedOnly = !_showBookmarkedOnly;
              });
              if (_showBookmarkedOnly) {
                context.read<PracticeBloc>().add(const ShowBookmarkedFlags());
              } else {
                context.read<PracticeBloc>().add(
                      LoadPracticeFlags(continentId: widget.continentId),
                    );
              }
            },
            tooltip: 'Show bookmarked flags',
          ),
        ],
      ),
      body: Column(
        children: [
          // Search bar
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: TextField(
              controller: _searchController,
              decoration: InputDecoration(
                hintText: 'Search flags...',
                prefixIcon: const Icon(Icons.search),
                suffixIcon: _searchController.text.isNotEmpty
                    ? IconButton(
                        icon: const Icon(Icons.clear),
                        onPressed: () {
                          _searchController.clear();
                          context.read<PracticeBloc>().add(
                                LoadPracticeFlags(continentId: widget.continentId),
                              );
                        },
                      )
                    : null,
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
                filled: true,
                fillColor: Colors.grey[100],
              ),
              onChanged: (query) {
                if (query.isEmpty) {
                  context.read<PracticeBloc>().add(
                        LoadPracticeFlags(continentId: widget.continentId),
                      );
                } else {
                  context.read<PracticeBloc>().add(
                        SearchPracticeFlags(query),
                      );
                }
              },
            ),
          ),

          // Flags list
          Expanded(
            child: BlocBuilder<PracticeBloc, PracticeState>(
              builder: (context, state) {
                if (state is PracticeLoading) {
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                }

                if (state is PracticeError) {
                  return Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const Icon(
                          Icons.error_outline,
                          size: 64,
                          color: Colors.red,
                        ),
                        const SizedBox(height: 16),
                        Text(
                          state.message,
                          style: const TextStyle(fontSize: 16),
                          textAlign: TextAlign.center,
                        ),
                        const SizedBox(height: 24),
                        ElevatedButton(
                          onPressed: () {
                            context.read<PracticeBloc>().add(
                                  LoadPracticeFlags(
                                      continentId: widget.continentId),
                                );
                          },
                          child: const Text('Retry'),
                        ),
                      ],
                    ),
                  );
                }

                if (state is PracticeFlagsLoaded) {
                  if (state.flags.isEmpty) {
                    return Center(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(
                            _showBookmarkedOnly
                                ? Icons.bookmark_border
                                : Icons.flag_outlined,
                            size: 64,
                            color: Colors.grey,
                          ),
                          const SizedBox(height: 16),
                          Text(
                            _showBookmarkedOnly
                                ? 'No bookmarked flags yet'
                                : 'No flags found',
                            style: const TextStyle(
                              fontSize: 16,
                              color: Colors.grey,
                            ),
                          ),
                        ],
                      ),
                    );
                  }

                  return ListView.builder(
                    padding: const EdgeInsets.symmetric(horizontal: 16.0),
                    itemCount: state.flags.length,
                    itemBuilder: (context, index) {
                      final flag = state.flags[index];
                      final isBookmarked =
                          state.bookmarkedFlagIds.contains(flag.id);

                      return PracticeFlagCard(
                        flag: flag,
                        isBookmarked: isBookmarked,
                        onBookmarkToggle: () {
                          context.read<PracticeBloc>().add(
                                ToggleFlagBookmark(flag.id),
                              );
                        },
                      );
                    },
                  );
                }

                // Initial state
                return const Center(
                  child: CircularProgressIndicator(),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
