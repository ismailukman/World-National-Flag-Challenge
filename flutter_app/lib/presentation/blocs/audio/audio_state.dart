import 'package:equatable/equatable.dart';

/// States for audio management
abstract class AudioState extends Equatable {
  const AudioState();

  @override
  List<Object?> get props => [];
}

/// Initial state
class AudioInitial extends AudioState {
  const AudioInitial();
}

/// Audio ready to use
class AudioReady extends AudioState {
  final bool isMusicEnabled;
  final bool isSfxEnabled;
  final String currentTrack;
  final bool isPlaying;
  final double musicVolume;
  final double sfxVolume;

  const AudioReady({
    required this.isMusicEnabled,
    required this.isSfxEnabled,
    required this.currentTrack,
    required this.isPlaying,
    this.musicVolume = 0.2,
    this.sfxVolume = 0.5,
  });

  @override
  List<Object?> get props => [
        isMusicEnabled,
        isSfxEnabled,
        currentTrack,
        isPlaying,
        musicVolume,
        sfxVolume,
      ];

  AudioReady copyWith({
    bool? isMusicEnabled,
    bool? isSfxEnabled,
    String? currentTrack,
    bool? isPlaying,
    double? musicVolume,
    double? sfxVolume,
  }) {
    return AudioReady(
      isMusicEnabled: isMusicEnabled ?? this.isMusicEnabled,
      isSfxEnabled: isSfxEnabled ?? this.isSfxEnabled,
      currentTrack: currentTrack ?? this.currentTrack,
      isPlaying: isPlaying ?? this.isPlaying,
      musicVolume: musicVolume ?? this.musicVolume,
      sfxVolume: sfxVolume ?? this.sfxVolume,
    );
  }
}

/// Audio error
class AudioError extends AudioState {
  final String message;

  const AudioError(this.message);

  @override
  List<Object?> get props => [message];
}
