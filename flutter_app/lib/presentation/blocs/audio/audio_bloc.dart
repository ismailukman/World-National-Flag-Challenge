import 'package:flutter/foundation.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:audioplayers/audioplayers.dart' hide AudioEvent;
import '../../../data/repositories/settings_repository.dart';
import 'audio_event.dart';
import 'audio_state.dart';

/// BLoC for managing background music and sound effects
class AudioBloc extends Bloc<AudioEvent, AudioState> {
  final SettingsRepository settingsRepository;

  // Audio players
  late AudioPlayer _musicPlayer;
  final Map<String, AudioPlayer> _sfxPlayers = {};

  // Track paths
  static const Map<String, String> _musicTracks = {
    'afro': 'assets/audio/music/afro.mp3',
    'game': 'assets/audio/music/game.mp3',
    'war': 'assets/audio/music/war.mp3',
  };

  static const Map<String, String> _soundEffects = {
    'correct': 'assets/audio/sfx/correct_answer.wav',
    'wrong': 'assets/audio/sfx/wrong_answer.wav',
    'button': 'assets/audio/sfx/button_tap.wav',
    'achievement': 'assets/audio/sfx/achievement_unlock.wav',
    'complete': 'assets/audio/sfx/quiz_complete.wav',
  };

  AudioBloc({
    required this.settingsRepository,
  }) : super(const AudioInitial()) {
    on<InitializeAudio>(_onInitializeAudio);
    on<PlayBackgroundMusic>(_onPlayBackgroundMusic);
    on<PauseBackgroundMusic>(_onPauseBackgroundMusic);
    on<ResumeBackgroundMusic>(_onResumeBackgroundMusic);
    on<StopBackgroundMusic>(_onStopBackgroundMusic);
    on<ToggleMusic>(_onToggleMusic);
    on<ChangeMusicTrack>(_onChangeMusicTrack);
    on<PlaySoundEffect>(_onPlaySoundEffect);
    on<ToggleSoundEffects>(_onToggleSoundEffects);
    on<SetMusicVolume>(_onSetMusicVolume);
    on<SetSfxVolume>(_onSetSfxVolume);
    on<DisposeAudio>(_onDisposeAudio);

    // Initialize audio players
    _musicPlayer = AudioPlayer();
    _musicPlayer.setReleaseMode(ReleaseMode.loop);
  }

  String _assetKey(String assetPath) {
    return assetPath.startsWith('assets/')
        ? assetPath.substring('assets/'.length)
        : assetPath;
  }

  Future<void> _playTrack(String trackName, AudioReady currentState) async {
    final trackPath = _musicTracks[trackName];
    if (trackPath == null) {
      throw Exception('Invalid music track: $trackName');
    }
    await _musicPlayer.stop();
    await _musicPlayer.setSource(
      kIsWeb ? UrlSource(trackPath) : AssetSource(_assetKey(trackPath)),
    );
    await _musicPlayer.setVolume(currentState.musicVolume);
    await _musicPlayer.resume();
  }

  /// Initialize audio system
  Future<void> _onInitializeAudio(
    InitializeAudio event,
    Emitter<AudioState> emit,
  ) async {
    try {
      final isMusicEnabled = settingsRepository.isMusicEnabled();
      final isSfxEnabled = settingsRepository.isSfxEnabled();
      final currentTrack = settingsRepository.getCurrentMusicTrack();

      emit(AudioReady(
        isMusicEnabled: isMusicEnabled,
        isSfxEnabled: isSfxEnabled,
        currentTrack: currentTrack,
        isPlaying: false,
      ));

      // Auto-play if music is enabled (skip on web until user gesture)
      if (isMusicEnabled && !kIsWeb) {
        add(PlayBackgroundMusic(trackName: currentTrack));
      }
    } catch (e) {
      emit(AudioError('Failed to initialize audio: $e'));
    }
  }

  /// Play background music
  Future<void> _onPlayBackgroundMusic(
    PlayBackgroundMusic event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    try {
      final trackName = event.trackName ?? currentState.currentTrack;

      if (currentState.isMusicEnabled) {
        await _playTrack(trackName, currentState);

        emit(currentState.copyWith(
          currentTrack: trackName,
          isPlaying: true,
        ));
      }
    } catch (e) {
      emit(AudioError('Failed to play music: $e'));
    }
  }

  /// Pause background music
  Future<void> _onPauseBackgroundMusic(
    PauseBackgroundMusic event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    try {
      await _musicPlayer.pause();
      emit(currentState.copyWith(isPlaying: false));
    } catch (e) {
      emit(AudioError('Failed to pause music: $e'));
    }
  }

  /// Resume background music
  Future<void> _onResumeBackgroundMusic(
    ResumeBackgroundMusic event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    try {
      if (currentState.isMusicEnabled) {
        await _musicPlayer.resume();
        emit(currentState.copyWith(isPlaying: true));
      }
    } catch (e) {
      emit(AudioError('Failed to resume music: $e'));
    }
  }

  /// Stop background music
  Future<void> _onStopBackgroundMusic(
    StopBackgroundMusic event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    try {
      await _musicPlayer.stop();
      emit(currentState.copyWith(isPlaying: false));
    } catch (e) {
      emit(AudioError('Failed to stop music: $e'));
    }
  }

  /// Toggle music on/off
  Future<void> _onToggleMusic(
    ToggleMusic event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    try {
      await settingsRepository.toggleMusic();
      final isMusicEnabled = settingsRepository.isMusicEnabled();

      if (isMusicEnabled) {
        if (kIsWeb) {
          await _playTrack(currentState.currentTrack, currentState);
        } else {
          add(PlayBackgroundMusic(trackName: currentState.currentTrack));
        }
      } else {
        await _musicPlayer.stop();
      }

      emit(currentState.copyWith(
        isMusicEnabled: isMusicEnabled,
        isPlaying: isMusicEnabled,
      ));
    } catch (e) {
      emit(AudioError('Failed to toggle music: $e'));
    }
  }

  /// Change music track
  Future<void> _onChangeMusicTrack(
    ChangeMusicTrack event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    try {
      await settingsRepository.setMusicTrack(event.trackName);
      final currentState = state as AudioReady;
      if (kIsWeb) {
        await _playTrack(event.trackName, currentState);
        emit(currentState.copyWith(
          currentTrack: event.trackName,
          isPlaying: currentState.isMusicEnabled,
        ));
      } else {
        add(PlayBackgroundMusic(trackName: event.trackName));
      }
    } catch (e) {
      emit(AudioError('Failed to change music track: $e'));
    }
  }

  /// Play sound effect
  Future<void> _onPlaySoundEffect(
    PlaySoundEffect event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    if (!currentState.isSfxEnabled) return;

    try {
      final effectPath = _soundEffects[event.effectName];

      if (effectPath == null) {
        emit(AudioError('Invalid sound effect: ${event.effectName}'));
        return;
      }

      // Create or reuse player for this effect
      if (!_sfxPlayers.containsKey(event.effectName)) {
        _sfxPlayers[event.effectName] = AudioPlayer();
        _sfxPlayers[event.effectName]!.setReleaseMode(ReleaseMode.stop);
      }

      final player = _sfxPlayers[event.effectName]!;
      await player.stop();
      await player.setSource(
        kIsWeb ? UrlSource(effectPath) : AssetSource(_assetKey(effectPath)),
      );
      await player.setVolume(currentState.sfxVolume);
      await player.resume();
    } catch (e) {
      // Silently fail for sound effects - not critical
    }
  }

  /// Toggle sound effects on/off
  Future<void> _onToggleSoundEffects(
    ToggleSoundEffects event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    try {
      await settingsRepository.toggleSfx();
      final isSfxEnabled = settingsRepository.isSfxEnabled();

      emit(currentState.copyWith(isSfxEnabled: isSfxEnabled));
    } catch (e) {
      emit(AudioError('Failed to toggle sound effects: $e'));
    }
  }

  /// Set music volume
  Future<void> _onSetMusicVolume(
    SetMusicVolume event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    try {
      await _musicPlayer.setVolume(event.volume);
      emit(currentState.copyWith(musicVolume: event.volume));
    } catch (e) {
      emit(AudioError('Failed to set music volume: $e'));
    }
  }

  /// Set SFX volume
  Future<void> _onSetSfxVolume(
    SetSfxVolume event,
    Emitter<AudioState> emit,
  ) async {
    if (state is! AudioReady) return;

    final currentState = state as AudioReady;

    emit(currentState.copyWith(sfxVolume: event.volume));
  }

  /// Dispose audio resources
  Future<void> _onDisposeAudio(
    DisposeAudio event,
    Emitter<AudioState> emit,
  ) async {
    try {
      await _musicPlayer.dispose();
      for (final player in _sfxPlayers.values) {
        await player.dispose();
      }
      _sfxPlayers.clear();
    } catch (e) {
      // Ignore disposal errors
    }
  }

  @override
  Future<void> close() async {
    await _musicPlayer.dispose();
    for (final player in _sfxPlayers.values) {
      await player.dispose();
    }
    _sfxPlayers.clear();
    return super.close();
  }
}
