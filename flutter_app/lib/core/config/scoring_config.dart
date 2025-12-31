/// Scoring configuration matching the original Kotlin app logic
class ScoringConfig {
  // Score calculation: correct * 3 - wrong (from Kotlin: score = c * 3 - w)
  static const int pointsPerCorrect = 3;
  static const int pointsPerWrong = 1;

  /// Calculate final score based on correct and wrong answers
  static int calculateScore(int correct, int wrong) {
    return (correct * pointsPerCorrect) - wrong;
  }

  // Minimum scores required per continent (from Kotlin activities)
  // NOTE: With FREE EXPLORATION these are not enforced, but kept for potential future use
  static const Map<String, int> minimumScores = {
    'africa': 15,      // From Africa.kt: if (c >= 15)
    'asia': 10,        // From Asia.kt: if (c >= 10)
    'europe': 10,      // From Europe.kt: if (c >= 10)
    'north_america': 5,
    'south_america': 3,
    'oceania': 3,
  };

  // Prevent instantiation
  ScoringConfig._();
}
