package com.example.check_volume_with_event_channel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.view.KeyEvent
import android.content.ComponentName
import android.view.View
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel

class MainActivity: FlutterActivity(){
    private val Volume_Channel = "samples.flutter.dev/volume"
    private var eventsink: EventChannel.EventSink? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine){
        super.configureFlutterEngine(flutterEngine)

        EventChannel(flutterEngine.dartExecutor.binaryMessenger, Volume_Channel).setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                eventsink = events
            }

            override fun onCancel(arguments: Any?) {
                eventsink = null
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (eventsink != null) {
            when (keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP -> {
                    eventsink?.success("Volume Up Pressed")
                    return true
                }
                KeyEvent.KEYCODE_VOLUME_DOWN -> {
                    eventsink?.success("Volume Down Pressed")
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}