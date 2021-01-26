package com.sunod.asiapay_flutter

import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull
import com.asiapay.sdk.PaySDK
import com.asiapay.sdk.integration.EnvBase
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants
import io.flutter.Log

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.lang.Exception

/** AsiapayFlutterPlugin */
class AsiapayFlutterPlugin: FlutterPlugin, MethodCallHandler {

  private val TAG  = "AsiapayFlutterPlugin"
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context: Context
  private lateinit var paySDK: PaySDK

  private var pluginBinding: ActivityPluginBinding? = null
  private var activity: Activity? = null

  private var envType: EnvBase.EnvType = EnvBase.EnvType.SANDBOX



  private val gpayClient: PaymentsClient by lazy {
    val builder = Wallet.WalletOptions.Builder()
    if (envType == EnvBase.EnvType.PRODUCTION) {
      builder.setEnvironment(WalletConstants.ENVIRONMENT_PRODUCTION)
    } else {
      builder.setEnvironment(WalletConstants.ENVIRONMENT_TEST)
    }
    Wallet.getPaymentsClient(context, builder.build())
  }

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    Log.i(TAG, "onAttachedToEngine")
    context = flutterPluginBinding.applicationContext
    paySDK = PaySDK(flutterPluginBinding.applicationContext)
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "asiapay_flutter")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
