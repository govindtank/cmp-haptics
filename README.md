# cmp-haptics

[![JitPack](https://jitpack.io/v/govindtank/cmp-haptics.svg)](https://jitpack.io/#govindtank/cmp-haptics)

**Modern Haptic Feedback Engine for Compose Multiplatform (Material You & CoreHaptics Sync).**

A fast, lightweight multiplatform library delivering synchronized tactile haptics across Android (VibratorManager / Material You effects) and iOS (`UIFeedbackGenerator` / CoreHaptics).

---

## Features

- 🎯 **Semantic Presets**: `CLICK`, `DOUBLE_CLICK`, `TICK`, `SELECTION`, `SUCCESS`, `WARNING`, `ERROR`.
- ⚡ **Material You & iOS Sync**: Maps seamlessly to Android 12+ predefined vibration constants and iOS Taptic Engine feedback.
- 🎛️ **Custom Vibration Patterns**: Support for multi-phase waveform patterns with timings & amplitudes.
- 🎨 **Compose Modifiers**: 1-line `.hapticClickable { }` modifier for any Compose element.
- 🪶 **Zero Overhead**: Direct platform API calls with zero third-party runtime baggage.

---

## Installation

Add the JitPack repository and dependency to your `build.gradle.kts`:

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.govindtank:cmp-haptics:1.0.0")
}
```

---

## Quick Start

### 1. Android Initialization (in MainActivity)

```kotlin
import io.github.govindtank.haptics.hapticInit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hapticInit(this)

        setContent {
            App()
        }
    }
}
```

### 2. Using in Compose UI

```kotlin
import androidx.compose.material3.*
import androidx.compose.runtime.*
import io.github.govindtank.haptics.*

@Composable
fun HapticShowcase() {
    val hapticManager = rememberHapticManager()

    Column {
        // Option 1: Direct via manager
        Button(onClick = {
            hapticManager.perform(HapticFeedbackType.SUCCESS)
        }) {
            Text("Success Haptic")
        }

        Button(onClick = {
            hapticManager.perform(HapticFeedbackType.HEAVY_IMPACT)
        }) {
            Text("Heavy Impact")
        }

        // Option 2: Composable Modifier
        Card(
            modifier = Modifier.hapticClickable(type = HapticFeedbackType.SELECTION) {
                // Card tapped with selection haptic
            }
        ) {
            Text("Tap Card")
        }
    }
}
```

---

## Semantic Haptic Mappings

| Preset | Android Effect | iOS Feedback Generator |
| :--- | :--- | :--- |
| `CLICK` | `EFFECT_CLICK` (API 29+) | `UIImpactFeedbackGenerator(Medium)` |
| `DOUBLE_CLICK` | `EFFECT_DOUBLE_CLICK` | 2x `UIImpactFeedbackGenerator` |
| `TICK` / `SELECTION` | `EFFECT_TICK` | `UISelectionFeedbackGenerator` |
| `LIGHT_IMPACT` | `EFFECT_TICK` (15ms one-shot) | `UIImpactFeedbackGenerator(Light)` |
| `MEDIUM_IMPACT` | `EFFECT_CLICK` (30ms one-shot)| `UIImpactFeedbackGenerator(Medium)` |
| `HEAVY_IMPACT` | `EFFECT_HEAVY_CLICK` (50ms) | `UIImpactFeedbackGenerator(Heavy)` |
| `RIGID_IMPACT` | Sharp 25ms one-shot | `UIImpactFeedbackGenerator(Rigid)` |
| `SOFT_IMPACT` | Subtle 20ms one-shot | `UIImpactFeedbackGenerator(Soft)` |
| `SUCCESS` | Custom 2-pulse waveform | `UINotificationFeedbackGenerator(Success)` |
| `WARNING` | Custom 2-pulse waveform | `UINotificationFeedbackGenerator(Warning)` |
| `ERROR` | Custom 3-pulse waveform | `UINotificationFeedbackGenerator(Error)` |

---

## Permissions (Android)

Add the vibration permission to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.VIBRATE" />
```

---

## License

Apache License 2.0
