# Check Volume with Event Channel

A Flutter application that demonstrates how to detect volume key presses on Android devices using EventChannel for native-Flutter communication.

## Overview

This project showcases the integration between Flutter and Android native code to capture hardware volume button events. When a user presses the volume up or volume down buttons, the app intercepts these events and streams them to the Flutter layer through an EventChannel.

## Features

- 🔊 **Volume Key Detection**: Captures volume up and volume down button presses
- 📡 **EventChannel Communication**: Real-time streaming of events from Android to Flutter
- 🚫 **Volume Override**: Prevents default volume behavior when events are captured
- 📱 **Cross-Platform Structure**: Ready for multi-platform development

## Project Structure

```
check_volume_with_event_channel/
├── android/                          # Android-specific code
│   └── app/src/main/kotlin/          # Kotlin source files
│       └── MainActivity.kt           # Main Android activity with volume detection
├── ios/                              # iOS-specific code
├── lib/                              # Flutter/Dart source code
├── windows/                          # Windows-specific code
├── macos/                            # macOS-specific code
├── linux/                            # Linux-specific code
├── web/                              # Web-specific code
└── pubspec.yaml                      # Flutter dependencies
```

## How It Works

### Android Native Implementation

The [`MainActivity.kt`](android/app/src/main/kotlin/com/example/check_volume_with_event_channel/MainActivity.kt) file contains:

1. **EventChannel Setup**: Creates a communication bridge between Android and Flutter
2. **Volume Key Interception**: Overrides `onKeyDown()` to capture volume button events
3. **Event Streaming**: Sends volume key events to Flutter in real-time

### Key Components

- **EventChannel**: `"samples.flutter.dev/volume"` - The communication channel identifier
- **Volume Detection**: Captures `KEYCODE_VOLUME_UP` and `KEYCODE_VOLUME_DOWN` events
- **Event Streaming**: Uses `EventSink` to send events to Flutter layer

## Getting Started

### Prerequisites

- Flutter SDK (3.5+)
- Android Studio or VS Code
- Android SDK (API level 21+)
- Java 8 or higher

### Installation

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd check_volume_with_event_channel
   ```

2. **Install dependencies**:
   ```bash
   flutter pub get
   ```

3. **Run the application**:
   ```bash
   flutter run
   ```

### Platform Support

| Platform | Status |
|----------|--------|
| Android  | ✅ Supported |
| iOS      | 🚧 Not implemented |
| Web      | ❌ Not applicable |
| Windows  | 🚧 Not implemented |
| macOS    | 🚧 Not implemented |
| Linux    | 🚧 Not implemented |

## Usage

1. Launch the app on an Android device or emulator
2. Press the volume up or volume down buttons
3. The app will detect these events and display them in the Flutter UI
4. Volume events are captured instead of changing the system volume

## Code Explanation

### EventChannel Setup
```kotlin
EventChannel(flutterEngine.dartExecutor.binaryMessenger, Volume_Channel)
    .setStreamHandler(object : EventChannel.StreamHandler {
        override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
            eventsink = events
        }
        override fun onCancel(arguments: Any?) {
            eventsink = null
        }
    })
```

### Volume Key Detection
```kotlin
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
```

## Configuration

### Android Manifest

The app requires no special permissions in the [`AndroidManifest.xml`](android/app/src/main/AndroidManifest.xml) for volume key detection, as this is a standard hardware interaction.

### Minimum SDK Requirements

- **Android**: API level 21 (Android 5.0)
- **Target SDK**: API level 34

## Development Notes

### Important Considerations

- **Event Handling**: Volume key events are only captured when the app is in the foreground
- **System Override**: Returning `true` from `onKeyDown()` prevents the default volume UI from appearing
- **Null Safety**: Always check if `eventsink` is not null before sending events
- **Memory Management**: The EventSink is properly cleaned up in the `onCancel()` method

### Troubleshooting

1. **Events not detected**: Ensure the app is in the foreground and has focus
2. **Compilation errors**: Check that the EventChannel import is correct
3. **Flutter communication**: Verify the channel name matches between Kotlin and Dart code

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin feature-name`
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Future Enhancements

- [ ] iOS implementation for volume key detection
- [ ] Desktop platform support (Windows, macOS, Linux)
- [ ] Volume level monitoring
- [ ] Gesture-based volume controls
- [ ] Custom volume UI implementation

## Related Resources

- [Flutter EventChannel Documentation](https://api.flutter.dev/flutter/services/EventChannel-class.html)
- [Android KeyEvent Documentation](https://developer.android.com/reference/android/view/KeyEvent)
- [Flutter Platform Integration Guide](https://docs.flutter.dev/platform-integration/platform-channels)