import 'package:flutter_test/flutter_test.dart';
import 'package:osim_health_kit/osim_health_kit.dart';
import 'package:osim_health_kit/osim_health_kit_platform_interface.dart';
import 'package:osim_health_kit/osim_health_kit_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockOsimHealthKitPlatform
    with MockPlatformInterfaceMixin
    implements OsimHealthKitPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final OsimHealthKitPlatform initialPlatform = OsimHealthKitPlatform.instance;

  test('$MethodChannelOsimHealthKit is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelOsimHealthKit>());
  });

  test('getPlatformVersion', () async {
    OsimHealthKit osimHealthKitPlugin = OsimHealthKit();
    MockOsimHealthKitPlatform fakePlatform = MockOsimHealthKitPlatform();
    OsimHealthKitPlatform.instance = fakePlatform;

    expect(await osimHealthKitPlugin.getPlatformVersion(), '42');
  });
}
