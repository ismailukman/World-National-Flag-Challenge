import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:intl/intl.dart' as intl;

import 'app_localizations_ar.dart';
import 'app_localizations_en.dart';
import 'app_localizations_fr.dart';
import 'app_localizations_tr.dart';

// ignore_for_file: type=lint

/// Callers can lookup localized strings with an instance of AppLocalizations
/// returned by `AppLocalizations.of(context)`.
///
/// Applications need to include `AppLocalizations.delegate()` in their app's
/// `localizationDelegates` list, and the locales they support in the app's
/// `supportedLocales` list. For example:
///
/// ```dart
/// import 'l10n/app_localizations.dart';
///
/// return MaterialApp(
///   localizationsDelegates: AppLocalizations.localizationsDelegates,
///   supportedLocales: AppLocalizations.supportedLocales,
///   home: MyApplicationHome(),
/// );
/// ```
///
/// ## Update pubspec.yaml
///
/// Please make sure to update your pubspec.yaml to include the following
/// packages:
///
/// ```yaml
/// dependencies:
///   # Internationalization support.
///   flutter_localizations:
///     sdk: flutter
///   intl: any # Use the pinned version from flutter_localizations
///
///   # Rest of dependencies
/// ```
///
/// ## iOS Applications
///
/// iOS applications define key application metadata, including supported
/// locales, in an Info.plist file that is built into the application bundle.
/// To configure the locales supported by your app, you’ll need to edit this
/// file.
///
/// First, open your project’s ios/Runner.xcworkspace Xcode workspace file.
/// Then, in the Project Navigator, open the Info.plist file under the Runner
/// project’s Runner folder.
///
/// Next, select the Information Property List item, select Add Item from the
/// Editor menu, then select Localizations from the pop-up menu.
///
/// Select and expand the newly-created Localizations item then, for each
/// locale your application supports, add a new item and select the locale
/// you wish to add from the pop-up menu in the Value field. This list should
/// be consistent with the languages listed in the AppLocalizations.supportedLocales
/// property.
abstract class AppLocalizations {
  AppLocalizations(String locale)
    : localeName = intl.Intl.canonicalizedLocale(locale.toString());

  final String localeName;

  static AppLocalizations? of(BuildContext context) {
    return Localizations.of<AppLocalizations>(context, AppLocalizations);
  }

  static const LocalizationsDelegate<AppLocalizations> delegate =
      _AppLocalizationsDelegate();

  /// A list of this localizations delegate along with the default localizations
  /// delegates.
  ///
  /// Returns a list of localizations delegates containing this delegate along with
  /// GlobalMaterialLocalizations.delegate, GlobalCupertinoLocalizations.delegate,
  /// and GlobalWidgetsLocalizations.delegate.
  ///
  /// Additional delegates can be added by appending to this list in
  /// MaterialApp. This list does not have to be used at all if a custom list
  /// of delegates is preferred or required.
  static const List<LocalizationsDelegate<dynamic>> localizationsDelegates =
      <LocalizationsDelegate<dynamic>>[
        delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
      ];

  /// A list of this localizations delegate's supported locales.
  static const List<Locale> supportedLocales = <Locale>[
    Locale('ar'),
    Locale('en'),
    Locale('fr'),
    Locale('tr'),
  ];

  /// The application name
  ///
  /// In en, this message translates to:
  /// **'World Nation Flag Challenge'**
  String get app_name;

  /// Full title of the application
  ///
  /// In en, this message translates to:
  /// **'World Nation Flag Challenge'**
  String get world_national_flag_challenge;

  /// Button text to start the quiz
  ///
  /// In en, this message translates to:
  /// **'Start'**
  String get start;

  /// Continent name - Africa
  ///
  /// In en, this message translates to:
  /// **'Africa'**
  String get africa;

  /// Continent name - Asia
  ///
  /// In en, this message translates to:
  /// **'Asia'**
  String get asia;

  /// Continent name - Europe
  ///
  /// In en, this message translates to:
  /// **'Europe'**
  String get europe;

  /// Continent name - North America
  ///
  /// In en, this message translates to:
  /// **'North America'**
  String get north_america;

  /// Continent name - Oceania
  ///
  /// In en, this message translates to:
  /// **'Oceania'**
  String get oceania;

  /// Continent name - South America
  ///
  /// In en, this message translates to:
  /// **'South America'**
  String get south_america;

  /// Help menu or button text
  ///
  /// In en, this message translates to:
  /// **'Help'**
  String get help;

  /// Help instructions for playing the game
  ///
  /// In en, this message translates to:
  /// **'This challenge requires you to select the correct country name of the national flag on current display from the options provided. Beginning with africa, play across all continents to stand a chance of getting higher points. Long press the audio icon to select music'**
  String
  get this_challenge_requires_you_to_select_the_correct_country_name_of_the_national_flag_on_current_display_from_the_options_provided_beginning_with_africa_play_across_all_continents_to_stand_a_chance_of_getting_higher_points_long_press_the_audio_icon_to_select_music;

  /// Encouragement text
  ///
  /// In en, this message translates to:
  /// **'...enjoy'**
  String get enjoy;

  /// About menu or section title
  ///
  /// In en, this message translates to:
  /// **'About'**
  String get about;

  /// About section description text
  ///
  /// In en, this message translates to:
  /// **'This application is designed to ease familiarity with world national flags. World national flag challenge provides the educative and entertaining opportunity'**
  String
  get this_application_is_designed_to_ease_familiarity_with_world_national_flags_world_national_flag_challenge_provides_the_educative_and_entertaining_opportunity;

  /// Developer credit line
  ///
  /// In en, this message translates to:
  /// **'by Ismaila Lukman Enegi'**
  String get by_ismaila_lukman_enegi;

  /// Country name - Nigeria
  ///
  /// In en, this message translates to:
  /// **'Nigeria'**
  String get nigeria;

  /// OK button text
  ///
  /// In en, this message translates to:
  /// **'Ok'**
  String get ok;

  /// Mascot greeting message when user starts the app
  ///
  /// In en, this message translates to:
  /// **'Hi! I\'m Flaggy, your flag learning buddy!'**
  String get mascot_greeting;

  /// Mascot message when user answers correctly
  ///
  /// In en, this message translates to:
  /// **'Great job!'**
  String get mascot_correct;

  /// Mascot message when user answers incorrectly
  ///
  /// In en, this message translates to:
  /// **'Keep trying!'**
  String get mascot_wrong;

  /// Mascot message when quiz is completed
  ///
  /// In en, this message translates to:
  /// **'Amazing work! You learned {count} flags!'**
  String mascot_quiz_complete(int count);

  /// Achievement title for completing first quiz
  ///
  /// In en, this message translates to:
  /// **'First Quiz'**
  String get achievement_first_quiz;

  /// Achievement description for completing first quiz
  ///
  /// In en, this message translates to:
  /// **'Complete your first quiz'**
  String get achievement_first_quiz_desc;

  /// Achievement title for trying all continents
  ///
  /// In en, this message translates to:
  /// **'Global Explorer'**
  String get achievement_global_explorer;

  /// Achievement description for trying all continents
  ///
  /// In en, this message translates to:
  /// **'Try all 6 continents'**
  String get achievement_global_explorer_desc;

  /// Achievement title for Africa perfect score
  ///
  /// In en, this message translates to:
  /// **'Africa Expert'**
  String get achievement_africa_expert;

  /// Achievement description for Africa perfect score
  ///
  /// In en, this message translates to:
  /// **'Perfect score on Africa quiz'**
  String get achievement_africa_expert_desc;

  /// Achievement title for getting 100% on a quiz
  ///
  /// In en, this message translates to:
  /// **'Perfectionist'**
  String get achievement_perfectionist;

  /// Achievement description for getting 100% on a quiz
  ///
  /// In en, this message translates to:
  /// **'Get 100% correct on a quiz'**
  String get achievement_perfectionist_desc;

  /// Achievement title for 7-day login streak
  ///
  /// In en, this message translates to:
  /// **'Week Warrior'**
  String get achievement_week_warrior;

  /// Achievement description for 7-day login streak
  ///
  /// In en, this message translates to:
  /// **'7-day login streak'**
  String get achievement_week_warrior_desc;

  /// Achievement title for 14-day login streak
  ///
  /// In en, this message translates to:
  /// **'Fortnight Fighter'**
  String get achievement_fortnight_fighter;

  /// Achievement description for 14-day login streak
  ///
  /// In en, this message translates to:
  /// **'14-day login streak'**
  String get achievement_fortnight_fighter_desc;

  /// Achievement title for 30-day login streak
  ///
  /// In en, this message translates to:
  /// **'Monthly Master'**
  String get achievement_monthly_master;

  /// Achievement description for 30-day login streak
  ///
  /// In en, this message translates to:
  /// **'30-day login streak'**
  String get achievement_monthly_master_desc;

  /// Achievement title for completing quiz quickly
  ///
  /// In en, this message translates to:
  /// **'Speedster'**
  String get achievement_speedster;

  /// Achievement description for completing quiz quickly
  ///
  /// In en, this message translates to:
  /// **'Complete quiz in under 90 seconds'**
  String get achievement_speedster_desc;

  /// Achievement title for completing 50 quizzes
  ///
  /// In en, this message translates to:
  /// **'Dedicated Learner'**
  String get achievement_dedicated_learner;

  /// Achievement description for completing 50 quizzes
  ///
  /// In en, this message translates to:
  /// **'Complete 50 quizzes'**
  String get achievement_dedicated_learner_desc;

  /// Achievement title for completing 100 quizzes
  ///
  /// In en, this message translates to:
  /// **'Flag Fanatic'**
  String get achievement_flag_fanatic;

  /// Achievement description for completing 100 quizzes
  ///
  /// In en, this message translates to:
  /// **'Complete 100 quizzes'**
  String get achievement_flag_fanatic_desc;

  /// Label for practice mode option
  ///
  /// In en, this message translates to:
  /// **'Practice Mode'**
  String get practice_mode;

  /// Label for daily challenge feature
  ///
  /// In en, this message translates to:
  /// **'Daily Challenge'**
  String get daily_challenge;

  /// Label for achievements section
  ///
  /// In en, this message translates to:
  /// **'Achievements'**
  String get achievements;

  /// Label for leaderboard section
  ///
  /// In en, this message translates to:
  /// **'Leaderboard'**
  String get leaderboard;

  /// Label for score display
  ///
  /// In en, this message translates to:
  /// **'Score'**
  String get score;

  /// Label for correct answers count
  ///
  /// In en, this message translates to:
  /// **'Correct'**
  String get correct;

  /// Label for wrong answers count
  ///
  /// In en, this message translates to:
  /// **'Wrong'**
  String get wrong;

  /// Label for high score display
  ///
  /// In en, this message translates to:
  /// **'High Score'**
  String get high_score;

  /// Message shown when user achieves new high score
  ///
  /// In en, this message translates to:
  /// **'New High Score!!!'**
  String get new_high_score;

  /// Continue button text
  ///
  /// In en, this message translates to:
  /// **'Continue'**
  String get continue_text;

  /// Message encouraging user to try again
  ///
  /// In en, this message translates to:
  /// **'Please try again!'**
  String get try_again;

  /// Label for language selection option
  ///
  /// In en, this message translates to:
  /// **'Select Default Language'**
  String get select_language;

  /// Label for quiz screen
  ///
  /// In en, this message translates to:
  /// **'Quiz'**
  String get quiz;

  /// Question prompt in quiz
  ///
  /// In en, this message translates to:
  /// **'Which country does this flag belong to?'**
  String get whichCountryFlag;

  /// Go back button text
  ///
  /// In en, this message translates to:
  /// **'Go Back'**
  String get goBack;

  /// Title when quiz is completed
  ///
  /// In en, this message translates to:
  /// **'Quiz Complete!'**
  String get quizComplete;

  /// Label for final score display
  ///
  /// In en, this message translates to:
  /// **'Final Score'**
  String get finalScore;

  /// Play again button text
  ///
  /// In en, this message translates to:
  /// **'Play Again'**
  String get playAgain;

  /// Title for newly unlocked achievements
  ///
  /// In en, this message translates to:
  /// **'Achievements Unlocked!'**
  String get achievementsUnlocked;
}

class _AppLocalizationsDelegate
    extends LocalizationsDelegate<AppLocalizations> {
  const _AppLocalizationsDelegate();

  @override
  Future<AppLocalizations> load(Locale locale) {
    return SynchronousFuture<AppLocalizations>(lookupAppLocalizations(locale));
  }

  @override
  bool isSupported(Locale locale) =>
      <String>['ar', 'en', 'fr', 'tr'].contains(locale.languageCode);

  @override
  bool shouldReload(_AppLocalizationsDelegate old) => false;
}

AppLocalizations lookupAppLocalizations(Locale locale) {
  // Lookup logic when only language code is specified.
  switch (locale.languageCode) {
    case 'ar':
      return AppLocalizationsAr();
    case 'en':
      return AppLocalizationsEn();
    case 'fr':
      return AppLocalizationsFr();
    case 'tr':
      return AppLocalizationsTr();
  }

  throw FlutterError(
    'AppLocalizations.delegate failed to load unsupported locale "$locale". This is likely '
    'an issue with the localizations generation tool. Please file an issue '
    'on GitHub with a reproducible sample app and the gen-l10n configuration '
    'that was used.',
  );
}
