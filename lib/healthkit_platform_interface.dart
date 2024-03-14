import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'healthkit_method_channel.dart';

abstract class HealthkitPlatform extends PlatformInterface {
  /// Constructs a HealthkitPlatform.
  HealthkitPlatform() : super(token: _token);

  static final Object _token = Object();

  static HealthkitPlatform _instance = MethodChannelHealthkit();

  /// The default instance of [HealthkitPlatform] to use.
  ///
  /// Defaults to [MethodChannelHealthkit].
  static HealthkitPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [HealthkitPlatform] when
  /// they register themselves.
  static set instance(HealthkitPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
