// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'leaderboard_entry.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class LeaderboardEntryAdapter extends TypeAdapter<LeaderboardEntry> {
  @override
  final int typeId = 4;

  @override
  LeaderboardEntry read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return LeaderboardEntry(
      playerName: fields[0] as String,
      totalScore: fields[1] as int,
      totalQuizzes: fields[2] as int,
      flagsLearned: fields[3] as int,
      highestQuizScore: fields[4] as int,
      lastPlayedDate: fields[5] as DateTime,
      currentStreak: fields[6] as int,
    );
  }

  @override
  void write(BinaryWriter writer, LeaderboardEntry obj) {
    writer
      ..writeByte(7)
      ..writeByte(0)
      ..write(obj.playerName)
      ..writeByte(1)
      ..write(obj.totalScore)
      ..writeByte(2)
      ..write(obj.totalQuizzes)
      ..writeByte(3)
      ..write(obj.flagsLearned)
      ..writeByte(4)
      ..write(obj.highestQuizScore)
      ..writeByte(5)
      ..write(obj.lastPlayedDate)
      ..writeByte(6)
      ..write(obj.currentStreak);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is LeaderboardEntryAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
