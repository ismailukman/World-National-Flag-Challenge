import 'package:equatable/equatable.dart';
import '../../../data/models/flag.dart';

/// States for practice mode
abstract class PracticeState extends Equatable {
  const PracticeState();

  @override
  List<Object?> get props => [];
}

/// Initial state
class PracticeInitial extends PracticeState {
  const PracticeInitial();
}

/// Loading flags
class PracticeLoading extends PracticeState {
  const PracticeLoading();
}

/// Flags loaded successfully
class PracticeFlagsLoaded extends PracticeState {
  final List<Flag> flags;
  final String? continentId;
  final Set<String> bookmarkedFlagIds;

  const PracticeFlagsLoaded({
    required this.flags,
    this.continentId,
    this.bookmarkedFlagIds = const {},
  });

  @override
  List<Object?> get props => [flags, continentId, bookmarkedFlagIds];

  PracticeFlagsLoaded copyWith({
    List<Flag>? flags,
    String? continentId,
    Set<String>? bookmarkedFlagIds,
  }) {
    return PracticeFlagsLoaded(
      flags: flags ?? this.flags,
      continentId: continentId ?? this.continentId,
      bookmarkedFlagIds: bookmarkedFlagIds ?? this.bookmarkedFlagIds,
    );
  }
}

/// Error loading flags
class PracticeError extends PracticeState {
  final String message;

  const PracticeError(this.message);

  @override
  List<Object?> get props => [message];
}
