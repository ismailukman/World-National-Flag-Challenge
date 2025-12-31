import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:go_router/go_router.dart';

// Core
import 'core/constants/app_routes.dart';
import 'presentation/theme/app_theme.dart';

// Data
import 'data/repositories/flag_repository.dart';
import 'data/repositories/progress_repository.dart';
import 'data/repositories/settings_repository.dart';
import 'data/repositories/achievement_repository.dart';
import 'data/repositories/daily_challenge_repository.dart';

// BLoCs
import 'presentation/blocs/language/language_bloc.dart';
import 'presentation/blocs/language/language_event.dart';
import 'presentation/blocs/language/language_state.dart';
import 'presentation/blocs/audio/audio_bloc.dart';
import 'presentation/blocs/audio/audio_event.dart';

// Screens
import 'presentation/screens/language/language_selection_screen.dart';
import 'presentation/screens/home/home_screen.dart';
import 'presentation/screens/quiz/quiz_screen.dart';
import 'presentation/screens/achievements/achievements_screen.dart';
import 'presentation/screens/practice/practice_mode_screen.dart';
import 'presentation/screens/daily_challenge/daily_challenge_screen.dart';
import 'presentation/screens/leaderboard/leaderboard_screen.dart';
import 'presentation/screens/about/about_screen.dart';
import 'presentation/screens/help/help_screen.dart';
import 'presentation/widgets/common/page_transition.dart';

// Generated localization
import 'package:world_national_flag_challenge/l10n/app_localizations.dart';

/// Main application widget
class WorldNationalFlagChallengeApp extends StatefulWidget {
  const WorldNationalFlagChallengeApp({super.key});

  @override
  State<WorldNationalFlagChallengeApp> createState() =>
      _WorldNationalFlagChallengeAppState();
}

class _WorldNationalFlagChallengeAppState
    extends State<WorldNationalFlagChallengeApp> {
  // Repositories (singletons)
  late final FlagRepository _flagRepository;
  late final ProgressRepository _progressRepository;
  late final SettingsRepository _settingsRepository;
  late final AchievementRepository _achievementRepository;
  late final DailyChallengeRepository _dailyChallengeRepository;

  // BLoCs
  late final LanguageBloc _languageBloc;
  late final AudioBloc _audioBloc;

  // Router
  late final GoRouter _router;
  bool _webAudioUnlocked = false;

  @override
  void initState() {
    super.initState();

    // Initialize repositories
    _flagRepository = FlagRepository();
    _progressRepository = ProgressRepository();
    _settingsRepository = SettingsRepository();
    _achievementRepository = AchievementRepository();
    _dailyChallengeRepository = DailyChallengeRepository();

    // Initialize BLoCs
    _languageBloc = LanguageBloc(settingsRepository: _settingsRepository)
      ..add(const LoadLanguage());
    _audioBloc = AudioBloc(settingsRepository: _settingsRepository)
      ..add(const InitializeAudio());

    // Determine initial route based on whether language has been set
    final hasLanguage = _settingsRepository.hasLanguageBeenSet();
    final initialRoute = hasLanguage ? AppRoutes.home : AppRoutes.language;

    // Initialize router with all Phase 2 routes
    _router = GoRouter(
      initialLocation: initialRoute,
      routes: [
        // Language Selection Screen
        GoRoute(
          path: AppRoutes.language,
          builder: (context, state) => const PageTransitionWrapper(
            type: PageTransitionType.fade,
            child: LanguageSelectionScreen(),
          ),
        ),

        // Home Screen
        GoRoute(
          path: AppRoutes.home,
          builder: (context, state) => const PageTransitionWrapper(
            type: PageTransitionType.scaleSlide,
            child: HomeScreen(),
          ),
        ),

        // Quiz Screen (unified for all continents)
        GoRoute(
          path: '${AppRoutes.quiz}/:continentId',
          builder: (context, state) {
            final continentId = state.pathParameters['continentId'] ?? 'africa';
            return PageTransitionWrapper(
              type: PageTransitionType.slide,
              child: QuizScreen(continentId: continentId),
            );
          },
        ),

        // About Screen
        GoRoute(
          path: AppRoutes.about,
          builder: (context, state) => const PageTransitionWrapper(
            type: PageTransitionType.fade,
            child: AboutScreen(),
          ),
        ),

        // Help Screen
        GoRoute(
          path: AppRoutes.help,
          builder: (context, state) => const PageTransitionWrapper(
            type: PageTransitionType.fade,
            child: HelpScreen(),
          ),
        ),

        // Achievements Screen
        GoRoute(
          path: AppRoutes.achievements,
          builder: (context, state) => const PageTransitionWrapper(
            type: PageTransitionType.scale,
            child: AchievementsScreen(),
          ),
        ),

        // Practice Mode Screen
        GoRoute(
          path: AppRoutes.practice,
          builder: (context, state) => const PageTransitionWrapper(
            type: PageTransitionType.scale,
            child: PracticeModeScreen(),
          ),
        ),

        // Practice Mode with continent filter
        GoRoute(
          path: '${AppRoutes.practice}/:continentId',
          builder: (context, state) {
            final continentId = state.pathParameters['continentId'];
            return PageTransitionWrapper(
              type: PageTransitionType.scale,
              child: PracticeModeScreen(continentId: continentId),
            );
          },
        ),

        // Daily Challenge Screen
        GoRoute(
          path: AppRoutes.dailyChallenge,
          builder: (context, state) => const PageTransitionWrapper(
            type: PageTransitionType.slide,
            child: DailyChallengeScreen(),
          ),
        ),

        // Leaderboard Screen
        GoRoute(
          path: AppRoutes.leaderboard,
          builder: (context, state) => const PageTransitionWrapper(
            type: PageTransitionType.scale,
            child: LeaderboardScreen(),
          ),
        ),
      ],
    );
  }

  @override
  void dispose() {
    _languageBloc.close();
    _audioBloc.close();
    _achievementRepository.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MultiRepositoryProvider(
      providers: [
        RepositoryProvider<FlagRepository>.value(value: _flagRepository),
        RepositoryProvider<ProgressRepository>.value(value: _progressRepository),
        RepositoryProvider<SettingsRepository>.value(value: _settingsRepository),
        RepositoryProvider<AchievementRepository>.value(
            value: _achievementRepository),
        RepositoryProvider<DailyChallengeRepository>.value(
            value: _dailyChallengeRepository),
      ],
      child: MultiBlocProvider(
        providers: [
          BlocProvider<LanguageBloc>.value(value: _languageBloc),
          BlocProvider<AudioBloc>.value(value: _audioBloc),
        ],
        child: BlocBuilder<LanguageBloc, LanguageState>(
          builder: (context, state) {
            // Determine current locale
            Locale currentLocale = const Locale('en');
            if (state is LanguageLoaded) {
              currentLocale = state.locale;
            } else if (state is LanguageChanged) {
              currentLocale = state.locale;
            }

            return MaterialApp.router(
              // App name
              title: 'World Nation Flag Challenge',

              // Theme
              theme: AppTheme.lightTheme,
              debugShowCheckedModeBanner: false,

              // Localization
              localizationsDelegates: const [
                AppLocalizations.delegate,
                GlobalMaterialLocalizations.delegate,
                GlobalWidgetsLocalizations.delegate,
                GlobalCupertinoLocalizations.delegate,
              ],
              supportedLocales: const [
                Locale('en'), // English
                Locale('ar'), // Arabic
                Locale('fr'), // French
                Locale('tr'), // Turkish
              ],
              locale: currentLocale,

              // Routing
              routerConfig: _router,

              // RTL support for Arabic
              builder: (context, child) {
                if (child == null) return const SizedBox.shrink();

                Widget app = Directionality(
                  textDirection: currentLocale.languageCode == 'ar'
                      ? TextDirection.rtl
                      : TextDirection.ltr,
                  child: child,
                );
                if (kIsWeb) {
                  app = Listener(
                    behavior: HitTestBehavior.translucent,
                    onPointerDown: (_) {
                      if (_webAudioUnlocked) return;
                      _webAudioUnlocked = true;
                      context.read<AudioBloc>().add(const PlayBackgroundMusic());
                    },
                    child: Center(
                      child: ConstrainedBox(
                        constraints: const BoxConstraints(maxWidth: 1100),
                        child: app,
                      ),
                    ),
                  );
                }
                return app;
              },
            );
          },
        ),
      ),
    );
  }
}
