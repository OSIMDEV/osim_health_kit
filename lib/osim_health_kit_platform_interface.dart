import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'osim_health_kit_method_channel.dart';

abstract class OsimHealthKitPlatform extends PlatformInterface {
  /// Constructs a OsimHealthKitPlatform.
  OsimHealthKitPlatform() : super(token: _token);

  static final Object _token = Object();

  static OsimHealthKitPlatform _instance = MethodChannelOsimHealthKit();

  /// The default instance of [OsimHealthKitPlatform] to use.
  ///
  /// Defaults to [MethodChannelOsimHealthKit].
  static OsimHealthKitPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [OsimHealthKitPlatform] when
  /// they register themselves.
  static set instance(OsimHealthKitPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
