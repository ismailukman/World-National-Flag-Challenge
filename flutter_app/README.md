# World Nation Flag Challenge - Flutter App

A fun and educational flag quiz app for children and adults to learn world national flags across all continents. Migrated from Kotlin to Flutter for cross-platform support (Android & iOS).

## 🎯 Features

### Current (Phase 1 - Foundation ✅)
- ✅ 189 flags from 6 continents (Africa, Asia, Europe, North America, South America, Oceania)
- ✅ 4-language support (English, Arabic, French, Turkish) with RTL support for Arabic
- ✅ BLoC state management architecture
- ✅ Hive local database for progress tracking
- ✅ Theme matching original Android app (#399DC5, #7AC4E1, #0B5761)
- ✅ Scoring system (correct * 3 - wrong)
- ✅ Achievement/badge system
- ✅ 180 flag images organized by continent
- ✅ Background music (3 tracks)

### Coming Soon (Phase 2-16)
- 🔜 Multiple-choice quiz screens
- 🔜 Mascot character guide (Rive animations)
- 🔜 Gamification (achievements, leaderboards, streaks)
- 🔜 Interactive animations (flag-waving, confetti, smooth transitions)
- 🔜 Practice mode
- 🔜 Daily challenges
- 🔜 Sound effects
- 🔜 Progress tracking with completion percentages

## 📂 Project Structure

```
flutter_app/
├── lib/
│   ├── core/                 # Constants, config, utilities
│   │   ├── constants/        # Colors, dimensions, routes
│   │   └── config/           # Scoring rules, app config
│   ├── data/                 # Data layer
│   │   ├── models/           # Data models (Flag, UserProgress, etc.)
│   │   ├── repositories/     # Data access layer
│   │   └── datasources/      # Local data sources (Hive, JSON)
│   ├── domain/               # Business logic layer
│   │   ├── entities/         # Business entities
│   │   └── usecases/         # Business use cases
│   ├── presentation/         # UI layer
│   │   ├── blocs/            # BLoC state management
│   │   ├── screens/          # App screens
│   │   ├── widgets/          # Reusable widgets
│   │   └── theme/            # App theme
│   └── l10n/                 # Internationalization (ARB files)
├── assets/
│   ├── images/flags/         # 180 flag images (by continent)
│   ├── audio/music/          # Background music tracks
│   ├── fonts/                # Custom fonts (need to download)
│   └── data/                 # flags_data.json
└── test/                     # Unit & widget tests
```

## 🚀 Getting Started

### Prerequisites
- Flutter SDK (>= 3.0.0)
- Dart SDK (>= 3.0.0)

### Installation

1. **Install Flutter**
   ```bash
   # Follow instructions at https://docs.flutter.dev/get-started/install
   flutter doctor
   ```

2. **Download Font Files**
   See [FONTS_README.md](FONTS_README.md) for instructions on downloading required fonts from Google Fonts.

3. **Install Dependencies**
   ```bash
   cd flutter_app
   flutter pub get
   ```

4. **Run Code Generation** (for Hive adapters)
   ```bash
   flutter pub run build_runner build
   ```

5. **Run the App**
   ```bash
   # Android
   flutter run

   # iOS
   flutter run -d ios

   # Web (for testing)
   flutter run -d chrome
   ```

## 📦 Dependencies

### Core
- `flutter_bloc` - State management
- `equatable` - Value equality
- `go_router` - Navigation

### Database & Storage
- `hive` & `hive_flutter` - NoSQL local database
- `shared_preferences` - Key-value storage

### Audio
- `audioplayers` - Background music
- `just_audio` - Sound effects

### Animations
- `rive` - Mascot character animations
- `lottie` - JSON animations
- `confetti` - Celebration effects
- `flutter_animate` - Declarative animations

### Localization
- `flutter_localizations` & `intl` - Multi-language support

See [pubspec.yaml](pubspec.yaml) for complete list.

## 🌍 Internationalization

The app supports 4 languages:

| Language | Code | Status |
|----------|------|--------|
| English | en | ✅ Complete |
| Arabic | ar | ✅ Complete (RTL) |
| French | fr | ✅ Complete |
| Turkish | tr | ✅ Complete |

ARB files location: `lib/l10n/`

## 🎨 Theme & Design

**Color Scheme:**
- Primary: `#399DC5` (Blue)
- Secondary: `#7AC4E1` (Light Blue)
- Accent: `#0B5761` (Dark Blue)

**Fonts:**
- Primary: Rubik
- Display: Berkshire Swash
- Headings: Black Ops One

**Material Design 3:** Full support with custom theme configuration.

## 📊 Data Models

### Core Models
- **Flag** - Country flag with localization support
- **Continent** - Continent with flag collection
- **UserProgress** - Quiz performance tracking per continent
- **QuizQuestion** - Multiple-choice question (4 options)
- **QuizSession** - Mid-quiz progress saving
- **Achievement** - Gamification badges

### Scoring
Formula: `score = (correct × 3) - wrong`

Matches original Kotlin app logic.

## 🗄️ Database Structure

**Hive Boxes:**
- `user_progress` - Progress per continent
- `achievements` - Unlocked badges
- `quiz_sessions` - Saved quiz sessions
- `settings` - Language, audio preferences, streaks

## 🏗️ Architecture

**Pattern:** BLoC (Business Logic Component)

**Layers:**
1. **Presentation** - UI (Screens, Widgets, BLoCs)
2. **Domain** - Business logic (Use cases, Entities)
3. **Data** - Data access (Models, Repositories, Data sources)

**Benefits:**
- Clear separation of concerns
- Testable business logic
- Reactive state management
- Scalable and maintainable

## 📝 Development Status

### Phase 1: Foundation (✅ 100% Complete)
- [x] Project structure
- [x] Dependencies configuration
- [x] Data migration (189 flags)
- [x] Asset migration (180 images, audio)
- [x] Data models with Hive
- [x] Repositories
- [x] Internationalization (4 languages)
- [x] Theme configuration
- [x] App entry points
- [ ] Font files (manual download required)

### Phase 2-3: Core Functionality (📅 Next)
- [ ] Navigation setup with go_router
- [ ] Language selection screen
- [ ] Home screen with continent menu
- [ ] Unified quiz screen
- [ ] QuizBloc with game logic
- [ ] Score calculation and persistence
- [ ] Basic animations

## 🧪 Testing

```bash
# Run all tests
flutter test

# Run specific test
flutter test test/unit/models/flag_test.dart

# Run with coverage
flutter test --coverage
```

## 🔧 Useful Commands

```bash
# Analyze code
flutter analyze

# Format code
flutter format lib/

# Clean build
flutter clean && flutter pub get

# Build APK (Android)
flutter build apk

# Build IPA (iOS)
flutter build ios

# Generate code (Hive adapters)
flutter pub run build_runner build --delete-conflicting-outputs
```

## 📄 License

Copyright © 2024 Ismaila Lukman Enegi

## 🙏 Acknowledgments

- Original Android app by Ismaila Lukman Enegi
- All flag images from public domain sources
- Google Fonts for typography
- Flutter and Dart teams

---

**Current Status:** Phase 1 Complete - Ready for Phase 2 Development

For detailed implementation plan, see [PHASE1_COMPLETION_SUMMARY.md](PHASE1_COMPLETION_SUMMARY.md)
