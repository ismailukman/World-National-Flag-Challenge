// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'quiz_session.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class QuizSessionAdapter extends TypeAdapter<QuizSession> {
  @override
  final int typeId = 3;

  @override
  QuizSession read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return QuizSession(
      sessionId: fields[0] as String,
      continentId: fields[1] as String,
      flagIds: (fields[2] as List).cast<String>(),
      currentQuestionIndex: fields[3] as int,
      correctCount: fields[4] as int,
      wrongCount: fields[5] as int,
      startedAt: fields[6] as DateTime?,
      pausedAt: fields[7] as DateTime?,
      isCompleted: fields[8] as bool,
      answeredQuestions: (fields[9] as Map?)?.cast<String, bool>(),
    );
  }

  @override
  void write(BinaryWriter writer, QuizSession obj) {
    writer
      ..writeByte(10)
      ..writeByte(0)
      ..write(obj.sessionId)
      ..writeByte(1)
      ..write(obj.continentId)
      ..writeByte(2)
      ..write(obj.flagIds)
      ..writeByte(3)
      ..write(obj.currentQuestionIndex)
      ..writeByte(4)
      ..write(obj.correctCount)
      ..writeByte(5)
      ..write(obj.wrongCount)
      ..writeByte(6)
      ..write(obj.startedAt)
      ..writeByte(7)
      ..write(obj.pausedAt)
      ..writeByte(8)
      ..write(obj.isCompleted)
      ..writeByte(9)
      ..write(obj.answeredQuestions);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is QuizSessionAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
