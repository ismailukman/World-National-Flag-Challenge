// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_progress.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class UserProgressAdapter extends TypeAdapter<UserProgress> {
  @override
  final int typeId = 0;

  @override
  UserProgress read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return UserProgress(
      continentId: fields[0] as String,
      highScore: fields[1] as int,
      totalQuizzesTaken: fields[2] as int,
      totalCorrectAnswers: fields[3] as int,
      totalWrongAnswers: fields[4] as int,
      learnedFlagIds: (fields[5] as List?)?.cast<String>(),
      difficultFlagIds: (fields[6] as List?)?.cast<String>(),
      lastPlayedAt: fields[7] as DateTime?,
      isUnlocked: fields[8] as bool,
      completionPercentage: fields[9] as double,
    );
  }

  @override
  void write(BinaryWriter writer, UserProgress obj) {
    writer
      ..writeByte(10)
      ..writeByte(0)
      ..write(obj.continentId)
      ..writeByte(1)
      ..write(obj.highScore)
      ..writeByte(2)
      ..write(obj.totalQuizzesTaken)
      ..writeByte(3)
      ..write(obj.totalCorrectAnswers)
      ..writeByte(4)
      ..write(obj.totalWrongAnswers)
      ..writeByte(5)
      ..write(obj.learnedFlagIds)
      ..writeByte(6)
      ..write(obj.difficultFlagIds)
      ..writeByte(7)
      ..write(obj.lastPlayedAt)
      ..writeByte(8)
      ..write(obj.isUnlocked)
      ..writeByte(9)
      ..write(obj.completionPercentage);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is UserProgressAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
