# Audio Assets

This directory contains all audio files used in the World Nation Flag Challenge app.

## Directory Structure

```
assets/audio/
├── music/          # Background music tracks (looping)
│   ├── afro.mp3
│   ├── game.mp3
│   └── war.mp3
└── sfx/            # Sound effects (one-shot)
    ├── correct_answer.wav
    ├── wrong_answer.wav
    ├── button_tap.wav
    ├── achievement_unlock.wav
    └── quiz_complete.wav
```

## Audio Specifications

### Background Music

**Format:** WAV
**Sample Rate:** 44.1 kHz
**Bit Rate:** 128-192 kbps
**Playback Mode:** Loop
**Default Volume:** 0.2 (20%)

#### Music Tracks:

1. **afro.mp3** (Default)
   - African-inspired upbeat music
   - Recommended duration: 2-3 minutes

2. **game.mp3**
   - General game background music
   - Recommended duration: 2-3 minutes

3. **war.mp3**
   - Epic/dramatic background music
   - Recommended duration: 2-3 minutes

### Sound Effects

**Format:** MP3
**Sample Rate:** 44.1 kHz
**Bit Rate:** 96-128 kbps
**Playback Mode:** One-shot
**Default Volume:** 0.5 (50%)

#### Sound Effects:

1. **correct_answer.wav**
   - Positive feedback sound (e.g., bell, ding, success chime)
   - Duration: < 1 second
   - Trigger: When user selects correct answer

2. **wrong_answer.wav**
   - Negative feedback sound (e.g., buzz, error tone)
   - Duration: < 1 second
   - Trigger: When user selects wrong answer

3. **button_tap.wav**
   - Subtle UI click sound
   - Duration: < 0.5 seconds
   - Trigger: On button press

4. **achievement_unlock.wav**
   - Celebratory sound (e.g., fanfare, success jingle)
   - Duration: 1-2 seconds
   - Trigger: When achievement is unlocked

5. **quiz_complete.wav**
   - Completion sound (e.g., victory theme, applause)
   - Duration: 2-3 seconds
   - Trigger: When quiz finishes

## Placeholder Files

If actual audio files are not available, you can:

1. **Use online resources:**
   - Freesound.org
   - Zapsplat.com
   - Mixkit.co
   - YouTube Audio Library

2. **Create simple placeholders** for testing:
   ```bash
   # Create a short WAV tone using Python
   python - <<'PY'
   import math, wave, struct
   with wave.open('correct_answer.wav', 'w') as wf:
       wf.setnchannels(1)
       wf.setsampwidth(2)
       wf.setframerate(44100)
       for i in range(int(44100 * 0.2)):
           sample = 0.4 * math.sin(2 * math.pi * 880 * (i / 44100))
           wf.writeframes(struct.pack('<h', int(sample * 32767)))
   PY
   ```

3. **Use text-to-speech** for temporary audio

## Attribution

If using free audio assets, ensure proper attribution as required by the license.

## Integration

Audio is managed by `AudioBloc` in `lib/presentation/blocs/audio/audio_bloc.dart`.

See the AudioBloc documentation for usage examples.
