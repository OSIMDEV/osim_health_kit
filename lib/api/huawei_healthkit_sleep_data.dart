class HuaWeiHealthKitSleepData {
  int type;
  double startDate;
  double endDate;
  String metaData;
  String deviceModel;
  String deviceName;
  String deviceType;
  String deviceUniqueCode;
  double updateTime;

  HuaWeiHealthKitSleepData._(
    this.type,
    this.startDate,
    this.endDate,
    this.metaData,
    this.deviceModel,
    this.deviceName,
    this.deviceType,
    this.deviceUniqueCode,
    this.updateTime,
  );

  factory HuaWeiHealthKitSleepData.from(Map<Object?, Object?> rawData) =>
      HuaWeiHealthKitSleepData._(
        rawData['type'].castInt(-1),
        rawData['startDate'].castDouble(-1.0),
        rawData['endDate'].castDouble(-1.0),
        rawData['metaData'].castString(''),
        rawData['deviceModel'].castString(''),
        rawData['deviceName'].castString(''),
        rawData['deviceType'].castString(''),
        rawData['deviceUniqueCode'].castString(''),
        rawData['updateTime'].castDouble(-1.0),
      );

  @override
  String toString() {
    return 'HuaWeiHealthKitSleepData{type: $type, startDate: $startDate, endDate: $endDate, metaData: $metaData, deviceModel: $deviceModel, deviceName: $deviceName, deviceType: $deviceType, deviceUniqueCode: $deviceUniqueCode, updateTime: $updateTime}';
  }
}

extension on Object? {
  int castInt(int defVal) => _genericCast<int>(this, defVal);

  double castDouble(double defVal) => switch (runtimeType) {
        int => _genericCast<int>(this, defVal.toInt()).toDouble(),
        double => _genericCast<double>(this, defVal),
        _ => defVal,
      };

  String castString(String defVal) => _genericCast<String>(this, defVal);
}

T _genericCast<T>(dynamic val, T defVal) {
  if (val is T) return val;
  return defVal;
}
