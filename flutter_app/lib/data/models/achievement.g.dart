// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'achievement.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class AchievementAdapter extends TypeAdapter<Achievement> {
  @override
  final int typeId = 1;

  @override
  Achievement read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return Achievement(
      id: fields[0] as String,
      nameKey: fields[1] as String,
      descriptionKey: fields[2] as String,
      iconPath: fields[3] as String,
      type: fields[4] as AchievementType,
      targetValue: fields[5] as int,
      isUnlocked: fields[6] as bool,
      unlockedAt: fields[7] as DateTime?,
      currentProgress: fields[8] as int,
    );
  }

  @override
  void write(BinaryWriter writer, Achievement obj) {
    writer
      ..writeByte(9)
      ..writeByte(0)
      ..write(obj.id)
      ..writeByte(1)
      ..write(obj.nameKey)
      ..writeByte(2)
      ..write(obj.descriptionKey)
      ..writeByte(3)
      ..write(obj.iconPath)
      ..writeByte(4)
      ..write(obj.type)
      ..writeByte(5)
      ..write(obj.targetValue)
      ..writeByte(6)
      ..write(obj.isUnlocked)
      ..writeByte(7)
      ..write(obj.unlockedAt)
      ..writeByte(8)
      ..write(obj.currentProgress);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is AchievementAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}

class AchievementTypeAdapter extends TypeAdapter<AchievementType> {
  @override
  final int typeId = 10;

  @override
  AchievementType read(BinaryReader reader) {
    switch (reader.readByte()) {
      case 0:
        return AchievementType.firstQuiz;
      case 1:
        return AchievementType.continentMaster;
      case 2:
        return AchievementType.streakWarrior;
      case 3:
        return AchievementType.speedster;
      case 4:
        return AchievementType.explorer;
      case 5:
        return AchievementType.perfectionist;
      case 6:
        return AchievementType.dedicated;
      case 7:
        return AchievementType.practiceExpert;
      case 8:
        return AchievementType.dailyChampion;
      default:
        return AchievementType.firstQuiz;
    }
  }

  @override
  void write(BinaryWriter writer, AchievementType obj) {
    switch (obj) {
      case AchievementType.firstQuiz:
        writer.writeByte(0);
        break;
      case AchievementType.continentMaster:
        writer.writeByte(1);
        break;
      case AchievementType.streakWarrior:
        writer.writeByte(2);
        break;
      case AchievementType.speedster:
        writer.writeByte(3);
        break;
      case AchievementType.explorer:
        writer.writeByte(4);
        break;
      case AchievementType.perfectionist:
        writer.writeByte(5);
        break;
      case AchievementType.dedicated:
        writer.writeByte(6);
        break;
      case AchievementType.practiceExpert:
        writer.writeByte(7);
        break;
      case AchievementType.dailyChampion:
        writer.writeByte(8);
        break;
    }
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is AchievementTypeAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
