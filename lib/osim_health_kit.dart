
import 'osim_health_kit_platform_interface.dart';

class OsimHealthKit {
  Future<String?> getPlatformVersion() {
    return OsimHealthKitPlatform.instance.getPlatformVersion();
  }
}
