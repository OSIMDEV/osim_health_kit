import 'package:flutter_test/flutter_test.dart';
import 'package:healthkit/healthkit_method_channel.dart';
import 'package:healthkit/healthkit_platform_interface.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockHealthkitPlatform
    with MockPlatformInterfaceMixin
    implements HealthkitPlatform {
  @override
  Future<bool?> cancelAuth(String provider) {
    // TODO: implement loadData
    throw UnimplementedError();
  }

  @override
  Future<dynamic> loadData(
    String provider,
    double startTime,
    double endTime,
    double timeout,
  ) {
    // TODO: implement loadData
    throw UnimplementedError();
  }

  @override
  Future<bool?> requireAuth(String provider) {
    // TODO: implement loadData
    throw UnimplementedError();
  }

  @override
  Future<bool?> testAuth(String provider) {
    // TODO: implement testAuth
    throw UnimplementedError();
  }

  @override
  // TODO: implement vendor
  Future<String?> getManufacturer() => throw UnimplementedError();

  @override
  Future<String?> getIP({
    bool? wifi,
    bool? ipv6,
  }) {
    // TODO: implement getIP
    throw UnimplementedError();
  }

  @override
  Future<void> navToSettings(String provider) => throw UnimplementedError();
}

void main() {
  final HealthkitPlatform initialPlatform = HealthkitPlatform.instance;

  test('$MethodChannelHealthkit is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelHealthkit>());
  });

  test('getPlatformVersion', () async {
    MockHealthkitPlatform fakePlatform = MockHealthkitPlatform();
    HealthkitPlatform.instance = fakePlatform;

    // expect(await healthkitPlugin.getPlatformVersion(), '42');
  });
}
