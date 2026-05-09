// ui/screens/scanner/ScannerScreen.kt
package com.citrascan.app.ui.screens.scanner

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.citrascan.app.R
import com.citrascan.app.ml.OnnxModelManager
import com.citrascan.app.ui.components.ScanGuideModal
import com.citrascan.app.ui.theme.BoneWhite
import com.citrascan.app.ui.theme.CitraScanTheme
import com.citrascan.app.ui.theme.ForestGreen
import com.google.accompanist.permissions.*
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerScreen(
    onNavigateBack: () -> Unit,
    onNavigateToResult: (String) -> Unit,
    viewModel: ScannerViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(state) {
        if (state is ScannerUiState.Success) {
            onNavigateToResult((state as ScannerUiState.Success).diseaseKey)
            viewModel.reset()
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(it))
            if (bitmap != null) viewModel.analyze(bitmap)
        }
    }

    when (val s = state) {
        is ScannerUiState.Analyzing -> AnalyzingContent(s)
        is ScannerUiState.Error -> ErrorContent(s.message, onRetry = { viewModel.reset() })
        else -> {
            val ready = state as? ScannerUiState.Ready ?: ScannerUiState.Ready()
            ScannerContent(
                state = ready,
                cameraPermission = cameraPermission,
                onBack = onNavigateBack,
                onToggleGuide = { viewModel.toggleGuide() },
                onSetInputMode = { viewModel.setInputMode(it) },
                onSetScanMode = { viewModel.setScanMode(it) },
                onCapture = { viewModel.analyze(it) },
                onPickGallery = { galleryLauncher.launch("image/*") }
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun ScannerContent(
    state: ScannerUiState.Ready,
    cameraPermission: PermissionState,
    onBack: () -> Unit,
    onToggleGuide: () -> Unit,
    onSetInputMode: (InputMode) -> Unit,
    onSetScanMode: (OnnxModelManager.ScanMode) -> Unit,
    onCapture: (Bitmap) -> Unit,
    onPickGallery: () -> Unit
) {
    val extra = CitraScanTheme.extraColors
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    var isFlashOn by remember { mutableStateOf(false) }
    var camera by remember { mutableStateOf<Camera?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        // ── Nav header — matches .nav-hdr ──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(extra.accentBg)
                    .border(1.5.dp, extra.accentBorder, CircleShape)
                    .clickable(onClick = onBack),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.ArrowBackIos, null, Modifier.size(16.dp).padding(start = 3.dp), tint = extra.accent)
            }
            Text(
                text = stringResource(R.string.scanner_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.3).sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(extra.accentBg)
                    .border(1.5.dp, extra.accentBorder, CircleShape)
                    .clickable(onClick = onToggleGuide),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.Info, null, Modifier.size(16.dp), tint = extra.accent)
            }
        }

        // ── Segment Control — matches .seg-ctrl (single: Camera / Upload) ──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 12.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(extra.accentBg)
                .border(1.dp, extra.accentBorder, RoundedCornerShape(13.dp))
                .padding(3.dp)
        ) {
            listOf(InputMode.CAMERA to R.string.scanner_camera, InputMode.UPLOAD to R.string.scanner_upload).forEach { (mode, res) ->
                val sel = state.inputMode == mode
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(11.dp))
                        .then(if (sel) Modifier.background(extra.accent.copy(alpha = 0.16f)) else Modifier)
                        .clickable { onSetInputMode(mode) }
                        .padding(vertical = 7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        stringResource(res),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (sel) FontWeight.ExtraBold else FontWeight.W600
                        ),
                        color = if (sel) extra.accent else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        if (state.inputMode == InputMode.CAMERA) {
            // ── Viewfinder — matches .vf-wrap ──
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color(0x99000000))
                    .border(1.5.dp, extra.accentBorder, RoundedCornerShape(22.dp))
            ) {
                if (cameraPermission.status.isGranted) {
                    CameraPreview(
                        isFlashOn = isFlashOn,
                        onImageCaptureReady = { imageCapture = it },
                        onCameraReady = { camera = it },
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Outlined.PhotoCamera, null, Modifier.size(48.dp), tint = Color.White.copy(alpha = 0.5f))
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "Camera permission required",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.5f)
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { cameraPermission.launchPermissionRequest() },
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ForestGreen)
                        ) { Text("Enable Camera") }
                    }
                }

                // Corner brackets — matches .vc.tl / .vc.tr / .vc.bl / .vc.br
                ViewfinderCorners(accentColor = extra.accent.copy(alpha = 0.8f))

                // Center hint — matches .vf-hint
                Box(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        "Aim at leaf or fruit",
                        style = MaterialTheme.typography.bodySmall.copy(
                            letterSpacing = 0.3.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }

                // Scan line animation — matches .vf-line
                ScanLineAnimation(color = ForestGreen)

                // Bottom tag — matches .vf-tag
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black.copy(alpha = 0.5f))
                        .border(1.dp, extra.accentBorder, RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        "YOLOv8 · real-time detection",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.W600,
                            letterSpacing = 0.3.sp
                        ),
                        color = BoneWhite
                    )
                }
            }

            // ── Control Bar — matches .ctrl-bar ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Gallery — matches .ctrl-side
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(extra.accentBg)
                        .border(1.5.dp, extra.accentBorder, CircleShape)
                        .clickable(onClick = onPickGallery),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.PhotoLibrary, null, Modifier.size(19.dp), tint = extra.accent.copy(alpha = 0.7f))
                }

                // Shutter — matches .shutter-o / .shutter-i
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .border(3.dp, extra.accent.copy(alpha = 0.25f), CircleShape)
                        .padding(6.dp)
                        .clip(CircleShape)
                        .background(ForestGreen)
                        .clickable {
                            imageCapture?.let { ic ->
                                ic.takePicture(Executors.newSingleThreadExecutor(), object : ImageCapture.OnImageCapturedCallback() {
                                    override fun onCaptureSuccess(proxy: ImageProxy) {
                                        val bmp = proxy.toBitmap()
                                        proxy.close()
                                        onCapture(bmp)
                                    }
                                })
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.PhotoCamera, null, tint = BoneWhite, modifier = Modifier.size(24.dp))
                }

                // Flash toggle — matches .ctrl-side
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(if (isFlashOn) extra.accent.copy(alpha = 0.16f) else extra.accentBg)
                        .border(1.5.dp, extra.accentBorder, CircleShape)
                        .clickable {
                            isFlashOn = !isFlashOn
                            camera?.cameraControl?.enableTorch(isFlashOn)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (isFlashOn) Icons.Outlined.FlashOn else Icons.Outlined.FlashOff,
                        null, Modifier.size(19.dp),
                        tint = if (isFlashOn) extra.accent else extra.accent.copy(alpha = 0.7f)
                    )
                }
            }
        } else {
            // ── Upload Zone — matches .upload-zone ──
            val dashColor = extra.accentBorder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(170.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .drawBehind {
                        drawRoundRect(
                            color = dashColor,
                            style = Stroke(
                                width = 1.5.dp.toPx(),
                                pathEffect = PathEffect.dashPathEffect(
                                    floatArrayOf(10.dp.toPx(), 6.dp.toPx())
                                )
                            ),
                            cornerRadius = CornerRadius(22.dp.toPx())
                        )
                    }
                    .background(extra.accentBg.copy(alpha = 0.3f))
                    .clickable(onClick = onPickGallery),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(extra.accentBg)
                            .border(1.5.dp, extra.accentBorder, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Outlined.FileUpload, null, Modifier.size(21.dp), tint = extra.accent)
                    }
                    Text(
                        "Select from gallery",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        "JPG or PNG · max 10 MB",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    )
                }
            }
            Spacer(Modifier.weight(1f))
        }

        // ── Bottom Section — matches .scan-bottom ──
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
                .padding(bottom = 14.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            // Tip pill — matches .tip-pill.glass
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(13.dp))
                    .background(extra.glass)
                    .border(1.dp, extra.glassBorder, RoundedCornerShape(13.dp))
                    .padding(horizontal = 13.dp, vertical = 11.dp)
            ) {
                Text(
                    "Capture a single leaf under natural light. Ensure the affected area fills the frame for best detection accuracy.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        lineHeight = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Analyze button — matches .btn-analyze
            Button(
                onClick = {
                    if (state.inputMode == InputMode.CAMERA) {
                        imageCapture?.let { ic ->
                            ic.takePicture(Executors.newSingleThreadExecutor(), object : ImageCapture.OnImageCapturedCallback() {
                                override fun onCaptureSuccess(proxy: ImageProxy) {
                                    val bmp = proxy.toBitmap()
                                    proxy.close()
                                    onCapture(bmp)
                                }
                            })
                        }
                    } else {
                        onPickGallery()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ForestGreen,
                    contentColor = BoneWhite
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Text(
                    if (state.inputMode == InputMode.CAMERA) "Analyze image" else "Select & analyze",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold)
                )
            }
        }
    }

    // ── Guide Modal — now a proper dialog ──
    if (state.showGuide) {
        ScanGuideModal(onDismiss = onToggleGuide)
    }
}

/**
 * Corner bracket overlays matching .vc.tl / .vc.tr / .vc.bl / .vc.br
 */
@Composable
private fun ViewfinderCorners(accentColor: Color) {
    val strokeWidth = 2.5.dp
    Box(modifier = Modifier.fillMaxSize()) {
        // Top-left
        Box(
            modifier = Modifier
                .padding(start = 14.dp, top = 14.dp)
                .size(38.dp)
                .align(Alignment.TopStart)
                .drawBehind {
                    val sw = strokeWidth.toPx()
                    drawLine(accentColor, Offset(0f, sw / 2), Offset(size.width, sw / 2), sw)
                    drawLine(accentColor, Offset(sw / 2, 0f), Offset(sw / 2, size.height), sw)
                }
        )
        // Top-right
        Box(
            modifier = Modifier
                .padding(end = 14.dp, top = 14.dp)
                .size(38.dp)
                .align(Alignment.TopEnd)
                .drawBehind {
                    val sw = strokeWidth.toPx()
                    drawLine(accentColor, Offset(0f, sw / 2), Offset(size.width, sw / 2), sw)
                    drawLine(accentColor, Offset(size.width - sw / 2, 0f), Offset(size.width - sw / 2, size.height), sw)
                }
        )
        // Bottom-left
        Box(
            modifier = Modifier
                .padding(start = 14.dp, bottom = 14.dp)
                .size(38.dp)
                .align(Alignment.BottomStart)
                .drawBehind {
                    val sw = strokeWidth.toPx()
                    drawLine(accentColor, Offset(0f, size.height - sw / 2), Offset(size.width, size.height - sw / 2), sw)
                    drawLine(accentColor, Offset(sw / 2, 0f), Offset(sw / 2, size.height), sw)
                }
        )
        // Bottom-right
        Box(
            modifier = Modifier
                .padding(end = 14.dp, bottom = 14.dp)
                .size(38.dp)
                .align(Alignment.BottomEnd)
                .drawBehind {
                    val sw = strokeWidth.toPx()
                    drawLine(accentColor, Offset(0f, size.height - sw / 2), Offset(size.width, size.height - sw / 2), sw)
                    drawLine(accentColor, Offset(size.width - sw / 2, 0f), Offset(size.width - sw / 2, size.height), sw)
                }
        )
    }
}

/**
 * Animated scan line moving vertically — matches .vf-line animation
 */
@Composable
private fun ScanLineAnimation(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "scan_line")
    val fraction by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = EaseInOut),
            repeatMode = RepeatMode.Restart
        ),
        label = "scan_line_pos"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
                .height(2.dp)
                .align(Alignment.TopStart)
                .offset(y = with(LocalDensity.current) { (fraction * 200).dp })
                .background(color)
        )
    }
}

@Composable
private fun CameraPreview(
    isFlashOn: Boolean,
    onImageCaptureReady: (ImageCapture) -> Unit,
    onCameraReady: (Camera) -> Unit,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
            cameraProviderFuture.addListener({
                val provider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val imgCapture = ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .setFlashMode(
                        if (isFlashOn) ImageCapture.FLASH_MODE_ON
                        else ImageCapture.FLASH_MODE_OFF
                    )
                    .build()
                onImageCaptureReady(imgCapture)
                try {
                    provider.unbindAll()
                    val cam = provider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview, imgCapture
                    )
                    onCameraReady(cam)
                } catch (_: Exception) {}
            }, ContextCompat.getMainExecutor(ctx))
            previewView
        },
        modifier = modifier
    )
}




@Composable
private fun AnalyzingContent(state: ScannerUiState.Analyzing) {
    val extra = CitraScanTheme.extraColors
    val infiniteTransition = rememberInfiniteTransition(label = "spin")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1100, easing = LinearEasing)),
        label = "ring_rotation"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 28.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier.size(68.dp)) {
            drawArc(
                color = extra.accent.copy(alpha = 0.15f), startAngle = 0f, sweepAngle = 360f,
                useCenter = false, style = Stroke(3.5.dp.toPx())
            )
            drawArc(
                color = extra.accent, startAngle = rotation, sweepAngle = 90f,
                useCenter = false, style = Stroke(3.5.dp.toPx())
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(
            stringResource(R.string.analyzing_title),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold, letterSpacing = (-0.4).sp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(5.dp))
        Text(
            stringResource(R.string.analyzing_subtitle),
            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(26.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(extra.accent.copy(alpha = 0.1f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(state.progress)
                    .clip(RoundedCornerShape(2.dp))
                    .background(ForestGreen)
            )
        }
        Spacer(Modifier.height(12.dp))
        Text(
            state.stepLabel.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ErrorContent(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Outlined.Warning, 
            null, 
            modifier = Modifier.size(64.dp), 
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.height(24.dp))
        Text(
            "Detection Error",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(12.dp))
        SelectionContainer {
            Text(
                message, 
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 22.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                ), 
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.1f))
                    .padding(16.dp)
            )
        }
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ForestGreen)
        ) { 
            Text(
                "Try Again", 
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold)
            ) 
        }
    }
}
