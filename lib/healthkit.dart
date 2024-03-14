
import 'healthkit_platform_interface.dart';

class Healthkit {
  Future<String?> getPlatformVersion() {
    return HealthkitPlatform.instance.getPlatformVersion();
  }
}
