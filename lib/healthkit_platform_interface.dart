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

  Future<bool?> requireAuth(String provider) async {
    throw UnimplementedError('requireAuth() has not been implemented.');
  }

  Future<bool?> cancelAuth(String provider) async {
    throw UnimplementedError('cancelAuth() has not been implemented.');
  }

  Future<bool?> testAuth(String provider) async {
    throw UnimplementedError('testAuth() has not been implemented.');
  }

  Future<dynamic> loadData(
    String provider,
    double startTime,
    double endTime,
    double timeout,
  ) async {
    throw UnimplementedError('loadData() has not been implemented.');
  }

  Future<String?> getVendor(String provider) async {
    throw UnimplementedError('get vendor has not been implemented.');
  }

  Future<String?> getIP(
    String provider, {
    bool? wifi,
    bool? ipv6,
  }) async {
    throw UnimplementedError('get IP has not been implemented.');
  }

  Future<void> navToSettings(String provider) async {
    throw UnimplementedError('navToSettings() has not been implemented.');
  }
}
