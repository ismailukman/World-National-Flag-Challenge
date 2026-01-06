import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../data/models/flag.dart';
import '../../../data/repositories/flag_repository.dart';
import '../../../data/repositories/settings_repository.dart';
import 'practice_event.dart';
import 'practice_state.dart';

/// BLoC for practice mode (study flags without pressure)
class PracticeBloc extends Bloc<PracticeEvent, PracticeState> {
  final FlagRepository flagRepository;
  final SettingsRepository settingsRepository;

  PracticeBloc({
    required this.flagRepository,
    required this.settingsRepository,
  }) : super(const PracticeInitial()) {
    on<LoadPracticeFlags>(_onLoadPracticeFlags);
    on<SearchPracticeFlags>(_onSearchPracticeFlags);
    on<ToggleFlagBookmark>(_onToggleFlagBookmark);
    on<ShowBookmarkedFlags>(_onShowBookmarkedFlags);
  }

  /// Load flags for practice (all or by continent)
  Future<void> _onLoadPracticeFlags(
    LoadPracticeFlags event,
    Emitter<PracticeState> emit,
  ) async {
    try {
      emit(const PracticeLoading());

      List<Flag> flags;
      if (event.continentId != null) {
        flags = await flagRepository.getFlagsByContinent(event.continentId!);
      } else {
        flags = await flagRepository.getAllFlags();
      }

      // Get bookmarked flags from settings
      final bookmarkedIds = settingsRepository.getBookmarkedFlags();

      emit(PracticeFlagsLoaded(
        flags: flags,
        continentId: event.continentId,
        bookmarkedFlagIds: bookmarkedIds,
      ));
    } catch (e) {
      emit(PracticeError('Failed to load flags: $e'));
    }
  }

  /// Search flags by name
  Future<void> _onSearchPracticeFlags(
    SearchPracticeFlags event,
    Emitter<PracticeState> emit,
  ) async {
    if (state is! PracticeFlagsLoaded) return;

    final currentState = state as PracticeFlagsLoaded;

    try {
      List<Flag> allFlags;
      if (currentState.continentId != null) {
        allFlags =
            await flagRepository.getFlagsByContinent(currentState.continentId!);
      } else {
        allFlags = await flagRepository.getAllFlags();
      }

      // Filter flags by search query
      final filteredFlags = allFlags.where((flag) {
        return flag.countryName
            .toLowerCase()
            .contains(event.query.toLowerCase());
      }).toList();

      emit(currentState.copyWith(flags: filteredFlags));
    } catch (e) {
      emit(PracticeError('Failed to search flags: $e'));
    }
  }

  /// Toggle flag bookmark
  Future<void> _onToggleFlagBookmark(
    ToggleFlagBookmark event,
    Emitter<PracticeState> emit,
  ) async {
    if (state is! PracticeFlagsLoaded) return;

    final currentState = state as PracticeFlagsLoaded;

    try {
      await settingsRepository.toggleFlagBookmark(event.flagId);
      final updatedBookmarks = settingsRepository.getBookmarkedFlags();

      emit(currentState.copyWith(bookmarkedFlagIds: updatedBookmarks));
    } catch (e) {
      // Silently fail - bookmarks are not critical
    }
  }

  /// Show only bookmarked flags
  Future<void> _onShowBookmarkedFlags(
    ShowBookmarkedFlags event,
    Emitter<PracticeState> emit,
  ) async {
    if (state is! PracticeFlagsLoaded) return;

    final currentState = state as PracticeFlagsLoaded;

    try {
      List<Flag> allFlags;
      if (currentState.continentId != null) {
        allFlags =
            await flagRepository.getFlagsByContinent(currentState.continentId!);
      } else {
        allFlags = await flagRepository.getAllFlags();
      }

      // Filter to only bookmarked flags
      final bookmarkedFlags = allFlags.where((flag) {
        return currentState.bookmarkedFlagIds.contains(flag.id);
      }).toList();

      emit(currentState.copyWith(flags: bookmarkedFlags));
    } catch (e) {
      emit(PracticeError('Failed to load bookmarked flags: $e'));
    }
  }
}
