import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'healthkit_platform_interface.dart';

/// An implementation of [HealthkitPlatform] that uses method channels.
class MethodChannelHealthkit extends HealthkitPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('healthkit');

  @override
  void requireAuth(String provider) async {
    methodChannel.invokeMethod<void>('requireAuth', provider);
  }

  @override
  void cancelAuth(String provider) async {
    methodChannel.invokeMethod<void>('cancelAuth', provider);
  }

  @override
  Future<bool?> testAuth(String provider) async {
    return methodChannel.invokeMethod<bool>('testAuth', provider);
  }

  @override
  Future<dynamic> loadData(String provider) async {
    return methodChannel.invokeMethod<dynamic>('loadData', {
      "provider": provider,
    });
  }

  @override
  Future<String?> getVendor(String provider) async =>
      methodChannel.invokeMethod<String>('getVendor', {
        "provider": provider,
      });
}
