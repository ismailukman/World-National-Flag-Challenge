import 'package:equatable/equatable.dart';
import 'flag.dart';

/// Quiz question model with flag and multiple choice options
class QuizQuestion extends Equatable {
  final Flag correctFlag;
  final List<Flag> allOptions; // 4 options total (1 correct + 3 wrong)
  final List<Flag> displayOptions; // Shuffled once per question for stable UI
  final int questionNumber;
  String? userAnswer;
  bool? isCorrect;
  DateTime? answeredAt;

  QuizQuestion({
    required this.correctFlag,
    required this.allOptions,
    List<Flag>? displayOptions,
    required this.questionNumber,
    this.userAnswer,
    this.isCorrect,
    this.answeredAt,
  })  : assert(allOptions.length == 4, 'Must have exactly 4 options'),
        displayOptions = displayOptions ?? (List<Flag>.from(allOptions)..shuffle());

  /// Check if user's answer is correct
  bool checkAnswer(String selectedCountryName) {
    userAnswer = selectedCountryName;
    isCorrect = selectedCountryName == correctFlag.countryName;
    answeredAt = DateTime.now();
    return isCorrect!;
  }

  /// Check if question has been answered
  bool get isAnswered => userAnswer != null;

  /// Get the correct answer country name
  String get correctAnswer => correctFlag.countryName;

  /// Create a copy with modified fields
  QuizQuestion copyWith({
    Flag? correctFlag,
    List<Flag>? allOptions,
    List<Flag>? displayOptions,
    int? questionNumber,
    String? userAnswer,
    bool? isCorrect,
    DateTime? answeredAt,
  }) {
    return QuizQuestion(
      correctFlag: correctFlag ?? this.correctFlag,
      allOptions: allOptions ?? this.allOptions,
      displayOptions: displayOptions ?? this.displayOptions,
      questionNumber: questionNumber ?? this.questionNumber,
      userAnswer: userAnswer ?? this.userAnswer,
      isCorrect: isCorrect ?? this.isCorrect,
      answeredAt: answeredAt ?? this.answeredAt,
    );
  }

  @override
  List<Object?> get props => [
        correctFlag.id,
        questionNumber,
        userAnswer,
        isCorrect,
      ];

  @override
  String toString() {
    return 'QuizQuestion(#$questionNumber, correct: ${correctFlag.countryName}, '
        'answered: ${userAnswer ?? "not yet"}, isCorrect: $isCorrect)';
  }
}
