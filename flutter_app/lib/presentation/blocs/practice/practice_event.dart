import 'package:equatable/equatable.dart';

/// Events for practice mode
abstract class PracticeEvent extends Equatable {
  const PracticeEvent();

  @override
  List<Object?> get props => [];
}

/// Load flags for practice (all or by continent)
class LoadPracticeFlags extends PracticeEvent {
  final String? continentId; // null = all flags

  const LoadPracticeFlags({this.continentId});

  @override
  List<Object?> get props => [continentId];
}

/// Search flags by name
class SearchPracticeFlags extends PracticeEvent {
  final String query;

  const SearchPracticeFlags(this.query);

  @override
  List<Object?> get props => [query];
}

/// Toggle flag bookmark
class ToggleFlagBookmark extends PracticeEvent {
  final String flagId;

  const ToggleFlagBookmark(this.flagId);

  @override
  List<Object?> get props => [flagId];
}

/// Show only bookmarked flags
class ShowBookmarkedFlags extends PracticeEvent {
  const ShowBookmarkedFlags();
}
