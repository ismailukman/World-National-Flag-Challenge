# World Nation Flag Challenge - Project Summary

## Overview
World Nation Flag Challenge is a Flutter app that teaches children world flags across all continents through quizzes, daily challenges, and gamified achievements.

## Core Features
- Multi-continent quiz mode with progress tracking and achievements.
- Daily Challenge mode with timer, bonus scoring, and confetti celebrations.
- Home hub with practice mode, daily challenge, and continent selection.
- Audio system for background music and sound effects.
- Localization in English, Arabic, French, and Turkish.
- Gamification: streaks, leaderboards, achievements, and badges.

## Architecture
- State management: `flutter_bloc` with feature-focused BLoCs.
- Data layer: repositories for flags, progress, settings, and achievements.
- Local persistence: Hive + SharedPreferences.
- Routing: `go_router` with animated transitions.

## Key Directories
- `flutter_app/lib/presentation/screens` - UI screens.
- `flutter_app/lib/presentation/blocs` - BLoCs and state.
- `flutter_app/lib/data` - repositories, models, and data sources.
- `flutter_app/assets` - images, audio, animations, and data.

## Audio
- Background music: `flutter_app/assets/audio/music/`.
- Sound effects: `flutter_app/assets/audio/sfx/`.
- Audio is controlled via `AudioBloc` and integrated across the app.

## Mascot
- `MascotCharacter` shows an animated mascot with emoji fallback.
- If `assets/animations/mascot.riv` is present, the Rive animation is used.

## Animations
- Confetti celebrations on quiz and daily challenge completion.
- Page transitions via `PageTransitionWrapper` and `flutter_animate`.

## App Icons and Splash
Configured for `flutter_launcher_icons` and `flutter_native_splash` in `flutter_app/pubspec.yaml`.

## Suggested Local Commands
- Get packages: `flutter pub get`
- Generate icons: `flutter pub run flutter_launcher_icons`
- Generate splash: `flutter pub run flutter_native_splash:create`
- Run app: `flutter run`
