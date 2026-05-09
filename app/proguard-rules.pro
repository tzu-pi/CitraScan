# ProGuard rules for CitraScan

# ONNX Runtime rules
-keep class ai.onnxruntime.** { *; }
-dontwarn ai.onnxruntime.**

# Hilt rules
-keep class dagger.hilt.android.internal.** { *; }

# Compose rules
-keep class androidx.compose.material3.** { *; }
