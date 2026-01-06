import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'data/datasources/local/hive_database.dart';
import 'app.dart';

void main() async {
  // Ensure Flutter bindings are initialized
  WidgetsFlutterBinding.ensureInitialized();

  // Set preferred orientations (portrait only for better UX)
  await SystemChrome.setPreferredOrientations([
    DeviceOrientation.portraitUp,
    DeviceOrientation.portraitDown,
  ]);

  // Initialize Hive database
  try {
    await HiveDatabase.init();
  } catch (e) {
    debugPrint('Error initializing Hive: $e');
    // Continue anyway, error will be handled in the app
  }

  // Run the app
  runApp(const WorldNationalFlagChallengeApp());
}
