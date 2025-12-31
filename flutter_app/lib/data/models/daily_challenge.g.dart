// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'daily_challenge.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class DailyChallengeAdapter extends TypeAdapter<DailyChallenge> {
  @override
  final int typeId = 3;

  @override
  DailyChallenge read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return DailyChallenge(
      date: fields[0] as String,
      flagIds: (fields[1] as List).cast<String>(),
      bonusMultiplier: fields[2] as double,
      isCompleted: fields[3] as bool,
      scoreAchieved: fields[4] as int?,
      completionTimeSeconds: fields[5] as int?,
    );
  }

  @override
  void write(BinaryWriter writer, DailyChallenge obj) {
    writer
      ..writeByte(6)
      ..writeByte(0)
      ..write(obj.date)
      ..writeByte(1)
      ..write(obj.flagIds)
      ..writeByte(2)
      ..write(obj.bonusMultiplier)
      ..writeByte(3)
      ..write(obj.isCompleted)
      ..writeByte(4)
      ..write(obj.scoreAchieved)
      ..writeByte(5)
      ..write(obj.completionTimeSeconds);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is DailyChallengeAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
