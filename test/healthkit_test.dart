import 'package:flutter_test/flutter_test.dart';
import 'package:healthkit/healthkit.dart';
import 'package:healthkit/healthkit_method_channel.dart';
import 'package:healthkit/healthkit_platform_interface.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockHealthkitPlatform
    with MockPlatformInterfaceMixin
    implements HealthkitPlatform {
  @override
  void cancelAuth(String provider) {
    // TODO: implement cancelAuth
  }

  @override
  Future loadData(String provider) {
    // TODO: implement loadData
    throw UnimplementedError();
  }

  @override
  void requireAuth(String provider) {
    // TODO: implement requireAuth
  }

  @override
  Future<bool?> testAuth(String provider) {
    // TODO: implement testAuth
    throw UnimplementedError();
  }

  @override
  // TODO: implement vendor
  Future<String?> get vendor => throw UnimplementedError();
}

void main() {
  final HealthkitPlatform initialPlatform = HealthkitPlatform.instance;

  test('$MethodChannelHealthkit is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelHealthkit>());
  });

  test('getPlatformVersion', () async {
    Healthkit healthkitPlugin = Healthkit();
    MockHealthkitPlatform fakePlatform = MockHealthkitPlatform();
    HealthkitPlatform.instance = fakePlatform;

    // expect(await healthkitPlugin.getPlatformVersion(), '42');
  });
}
