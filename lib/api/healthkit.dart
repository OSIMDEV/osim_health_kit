import 'package:healthkit/healthkit_platform_interface.dart';

final HealthKit healthKit = HealthKit();

class HealthKit {
  void requireAuth(String provider) =>
      HealthkitPlatform.instance.requireAuth(provider);

  void cancelAuth(String provider) =>
      HealthkitPlatform.instance.cancelAuth(provider);

  Future<bool?> testAuth(String provider) =>
      HealthkitPlatform.instance.testAuth(provider);

  Future<dynamic> loadData(String provider) =>
      HealthkitPlatform.instance.loadData(provider);

  Future<String?> getVendor(String provider) =>
      HealthkitPlatform.instance.getVendor(provider);
}
