import 'package:healthkit/healthkit_platform_interface.dart';

class Healthkit {
  void requireAuth(String provider) {
    HealthkitPlatform.instance.requireAuth(provider);
  }

  void cancelAuth(String provider) {
    HealthkitPlatform.instance.cancelAuth(provider);
  }

  Future<bool?> testAuth(String provider) =>
      HealthkitPlatform.instance.testAuth(provider);

  Future<dynamic> loadData(String provider) =>
      HealthkitPlatform.instance.loadData(provider);

  Future<String?> get vendor => HealthkitPlatform.instance.vendor;
}
