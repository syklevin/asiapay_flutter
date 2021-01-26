import 'dart:async';

import 'package:flutter/services.dart';

class AsiapayFlutter {
  static const MethodChannel _channel = const MethodChannel('asiapay_flutter');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<Map> initialize(String envType) async {
    return await _channel.invokeMethod('initialize', envType);
  }

  static Future<bool> isGooglePayReady() async {
    return await _channel.invokeMethod('isGooglePayReady');
  }
}
