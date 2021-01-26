#import "AsiapayFlutterPlugin.h"
#if __has_include(<asiapay_flutter/asiapay_flutter-Swift.h>)
#import <asiapay_flutter/asiapay_flutter-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "asiapay_flutter-Swift.h"
#endif

@implementation AsiapayFlutterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAsiapayFlutterPlugin registerWithRegistrar:registrar];
}
@end
