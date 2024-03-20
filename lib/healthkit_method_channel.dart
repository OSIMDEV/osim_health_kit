import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'healthkit_platform_interface.dart';

/// An implementation of [HealthkitPlatform] that uses method channels.
class MethodChannelHealthkit extends HealthkitPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('healthkit');

  @override
  Future<bool?> requireAuth(String provider) =>
      methodChannel.invokeMethod<bool?>(
        'requireAuth',
        {
          "provider": provider,
        },
      );

  @override
  Future<bool?> cancelAuth(String provider) =>
      methodChannel.invokeMethod<bool?>(
        'cancelAuth',
        {
          "provider": provider,
        },
      );

  @override
  Future<bool?> testAuth(String provider) => methodChannel.invokeMethod<bool?>(
        'testAuth',
        {
          "provider": provider,
        },
      );

  @override
  Future<dynamic> loadData(
    String provider,
    double startTime,
    double endTime,
    double timeout,
  ) =>
      methodChannel.invokeMethod<dynamic>(
        'loadData',
        {
          "provider": provider,
          "startTime": startTime,
          "endTime": endTime,
          "timeout": timeout,
        },
      );

  @override
  Future<String?> getVendor(String provider) =>
      methodChannel.invokeMethod<String?>(
        'getVendor',
        {
          "provider": provider,
        },
      );

  @override
  Future<String?> getIP(
    String provider, {
    bool? wifi,
    bool? ipv6,
  }) =>
      methodChannel.invokeMethod<String?>(
        'getIP',
        {
          "provider": provider,
          if (wifi ?? false) "wifi": wifi,
          if (ipv6 ?? false) "ipv6": ipv6,
        },
      );

  @override
  Future<void> navToSettings(String provider) =>
      methodChannel.invokeMethod<void>(
        'navToSettings',
        {
          "provider": provider,
        },
      );
}
