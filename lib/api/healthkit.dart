import 'package:healthkit/healthkit_platform_interface.dart';

final HealthKit healthKit = HealthKit();

class HealthKit {
  Future<bool?> requireAuth(String provider) =>
      HealthkitPlatform.instance.requireAuth(provider);

  Future<bool?> cancelAuth(String provider) =>
      HealthkitPlatform.instance.cancelAuth(provider);

  Future<bool?> testAuth(String provider) =>
      HealthkitPlatform.instance.testAuth(provider);

  Future<dynamic> loadData(
    String provider,
    double startTime,
    double endTime,
    double timeout,
  ) =>
      HealthkitPlatform.instance.loadData(
        provider,
        startTime,
        endTime,
        timeout,
      );

  Future<String?> getManufacturer() =>
      HealthkitPlatform.instance.getManufacturer();

  Future<String?> getIP({
    bool? wifi = false,
    bool? ipv6 = false,
  }) =>
      HealthkitPlatform.instance.getIP(
        wifi: wifi,
        ipv6: ipv6,
      );

  Future<void> navToSettings(String provider) =>
      HealthkitPlatform.instance.navToSettings(provider);
}
