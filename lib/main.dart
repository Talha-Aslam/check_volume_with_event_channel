import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const CheckVolume());
}

class CheckVolume extends StatefulWidget {
  const CheckVolume({super.key});

  @override
  State<CheckVolume> createState() => _CheckVolumeState();
}

class _CheckVolumeState extends State<CheckVolume> {
  static const platform = EventChannel("samples.flutter.dev/volume");
  String _volume = "Waiting for volume...";

  @override
  void initState() {
    super.initState();

    platform.receiveBroadcastStream().listen(
      (event) {
        setState(
          () {
            _volume = event.toString();
          },
        );
      },
      onError: (error) {
        setState(() {
          _volume = "Error: $error";
        });
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Volume Check',
      home: Scaffold(
        appBar: AppBar(
          title: const Text("Volume Check with Event Channel"),
        ),
        body: Center(
          child: Text(
            _volume,
            style: const TextStyle(fontSize: 24),
          ),
        ),
      ),
    );
  }
}
