import 'package:equatable/equatable.dart';

/// Events for quiz flow
abstract class QuizEvent extends Equatable {
  const QuizEvent();

  @override
  List<Object?> get props => [];
}

/// Start a new quiz for a continent
class StartQuiz extends QuizEvent {
  final String continentId;

  const StartQuiz(this.continentId);

  @override
  List<Object?> get props => [continentId];
}

/// User answered a question
class AnswerQuestion extends QuizEvent {
  final String selectedAnswer;

  const AnswerQuestion(this.selectedAnswer);

  @override
  List<Object?> get props => [selectedAnswer];
}

/// Move to next question
class NextQuestion extends QuizEvent {
  const NextQuestion();
}

/// Restart the current quiz
class RestartQuiz extends QuizEvent {
  const RestartQuiz();
}

/// Load a saved quiz session
class LoadSavedSession extends QuizEvent {
  final String sessionId;

  const LoadSavedSession(this.sessionId);

  @override
  List<Object?> get props => [sessionId];
}

/// Pause the quiz (save progress)
class PauseQuiz extends QuizEvent {
  const PauseQuiz();
}
