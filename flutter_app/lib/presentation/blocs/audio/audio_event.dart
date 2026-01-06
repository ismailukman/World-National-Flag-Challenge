import 'package:equatable/equatable.dart';

/// Events for audio management
abstract class AudioEvent extends Equatable {
  const AudioEvent();

  @override
  List<Object?> get props => [];
}

/// Initialize audio system
class InitializeAudio extends AudioEvent {
  const InitializeAudio();
}

/// Play background music
class PlayBackgroundMusic extends AudioEvent {
  final String? trackName; // 'afro', 'game', 'war' or null for current

  const PlayBackgroundMusic({this.trackName});

  @override
  List<Object?> get props => [trackName];
}

/// Pause background music
class PauseBackgroundMusic extends AudioEvent {
  const PauseBackgroundMusic();
}

/// Resume background music
class ResumeBackgroundMusic extends AudioEvent {
  const ResumeBackgroundMusic();
}

/// Stop background music
class StopBackgroundMusic extends AudioEvent {
  const StopBackgroundMusic();
}

/// Toggle music on/off
class ToggleMusic extends AudioEvent {
  const ToggleMusic();
}

/// Change music track
class ChangeMusicTrack extends AudioEvent {
  final String trackName; // 'afro', 'game', 'war'

  const ChangeMusicTrack(this.trackName);

  @override
  List<Object?> get props => [trackName];
}

/// Play sound effect
class PlaySoundEffect extends AudioEvent {
  final String effectName; // 'correct', 'wrong', 'button', 'achievement', 'complete'

  const PlaySoundEffect(this.effectName);

  @override
  List<Object?> get props => [effectName];
}

/// Toggle sound effects on/off
class ToggleSoundEffects extends AudioEvent {
  const ToggleSoundEffects();
}

/// Set music volume
class SetMusicVolume extends AudioEvent {
  final double volume; // 0.0 to 1.0

  const SetMusicVolume(this.volume);

  @override
  List<Object?> get props => [volume];
}

/// Set SFX volume
class SetSfxVolume extends AudioEvent {
  final double volume; // 0.0 to 1.0

  const SetSfxVolume(this.volume);

  @override
  List<Object?> get props => [volume];
}

/// Dispose audio resources
class DisposeAudio extends AudioEvent {
  const DisposeAudio();
}
