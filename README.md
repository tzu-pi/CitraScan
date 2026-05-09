# CitraScan Android Project

This is the Jetpack Compose implementation of the CitraScan Citrus Disease Detection app.

## Setup Instructions

### 1. Add ONNX Models
To enable disease detection, you must place your trained YOLOv8s ONNX models in the following directory:
`app/src/main/assets/models/`

The app expects these exact filenames:
- `citrus_yolov8s_fruits_best.onnx`
- `citrus_yolov8s_leaves_best.onnx`

### 2. Permissions
The app requires Camera permissions to function. This is handled at runtime, but ensure your device/emulator has camera support.

### 3. Build & Run
1. Open the `CitraScan` directory in Android Studio.
2. Sync Project with Gradle Files.
3. Run on a physical device (recommended for CameraX and ONNX performance).

## Architecture
- **Language**: Kotlin
- **UI**: Jetpack Compose (Material 3)
- **Architecture**: MVVM
- **DI**: Hilt
- **Inference**: ONNX Runtime for Android
- **Camera**: CameraX
