import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../../blocs/audio/audio_bloc.dart';
import '../../blocs/audio/audio_event.dart';
import '../../blocs/audio/audio_state.dart';

/// Audio control widget for home screen (matches Android app functionality)
class AudioControl extends StatelessWidget {
  const AudioControl({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<AudioBloc, AudioState>(
      builder: (context, state) {
        final audioState = state is AudioReady ? state : null;
        final isReady = audioState != null;
        final isMusicEnabled = audioState?.isMusicEnabled ?? true;
        final isPlaying = audioState?.isPlaying ?? false;
        final currentTrack = audioState?.currentTrack ?? 'afro';

        return Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            // Music on/off toggle
            IconButton(
              icon: Icon(
                isMusicEnabled ? Icons.volume_up : Icons.volume_off,
                color: Colors.white,
                size: 28,
              ),
              onPressed: isReady
                  ? () {
                      if (isMusicEnabled && !isPlaying) {
                        context.read<AudioBloc>().add(
                              const PlayBackgroundMusic(),
                            );
                      } else {
                        context.read<AudioBloc>().add(const ToggleMusic());
                      }
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(
                          content: Text(
                            isMusicEnabled && !isPlaying
                                ? 'Music enabled'
                                : isMusicEnabled
                                    ? 'Music disabled'
                                    : 'Music enabled',
                          ),
                          duration: const Duration(seconds: 1),
                        ),
                      );
                    }
                  : null,
              tooltip: isMusicEnabled ? 'Mute' : 'Unmute',
            ),

            // Music track selection (long press in Android)
            IconButton(
              icon: const Icon(
                Icons.music_note,
                color: Colors.white,
                size: 28,
              ),
              onPressed: isReady
                  ? () => _selectMusicTrack(context, currentTrack)
                  : null,
              tooltip: 'Select music track',
            ),
          ],
        );
      },
    );
  }

  Future<void> _selectMusicTrack(
    BuildContext context,
    String currentTrack,
  ) async {
    final selectedTrack = await showDialog<String>(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Select Music'),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            _buildTrackOption(context, 'Afro', 'afro', currentTrack),
            _buildTrackOption(context, 'Game', 'game', currentTrack),
            _buildTrackOption(context, 'War', 'war', currentTrack),
          ],
        ),
      ),
    );

    if (selectedTrack != null) {
      context.read<AudioBloc>().add(ChangeMusicTrack(selectedTrack));
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Playing ${selectedTrack.toUpperCase()} music'),
          duration: const Duration(seconds: 1),
        ),
      );
    }
  }

  Widget _buildTrackOption(
    BuildContext context,
    String name,
    String value,
    String currentTrack,
  ) {
    return ListTile(
      title: Text(name),
      leading: Radio<String>(
        value: value,
        groupValue: currentTrack,
        onChanged: (val) {
          Navigator.pop(context, val);
        },
      ),
      onTap: () => Navigator.pop(context, value),
    );
  }
}
