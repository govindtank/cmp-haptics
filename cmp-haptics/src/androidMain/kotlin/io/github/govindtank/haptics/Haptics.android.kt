package io.github.govindtank.haptics

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.HapticFeedbackConstants
import android.view.View

private var appContext: Context? = null

actual fun hapticInit(context: Any?) {
    if (context is Context) {
        appContext = context.applicationContext
    }
}

private fun getVibrator(): Vibrator? {
    val ctx = appContext ?: return null
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = ctx.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vibratorManager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        ctx.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }
}

actual fun performHapticFeedback(type: HapticFeedbackType) {
    val vibrator = getVibrator() ?: return
    if (!vibrator.hasVibrator()) return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val effectId = when (type) {
            HapticFeedbackType.CLICK -> VibrationEffect.EFFECT_CLICK
            HapticFeedbackType.DOUBLE_CLICK -> VibrationEffect.EFFECT_DOUBLE_CLICK
            HapticFeedbackType.TICK -> VibrationEffect.EFFECT_TICK
            HapticFeedbackType.HEAVY_IMPACT -> VibrationEffect.EFFECT_HEAVY_CLICK
            HapticFeedbackType.MEDIUM_IMPACT -> VibrationEffect.EFFECT_CLICK
            HapticFeedbackType.LIGHT_IMPACT -> VibrationEffect.EFFECT_TICK
            HapticFeedbackType.RIGID_IMPACT -> VibrationEffect.EFFECT_CLICK
            HapticFeedbackType.SOFT_IMPACT -> VibrationEffect.EFFECT_TICK
            HapticFeedbackType.SUCCESS -> null // custom pulse
            HapticFeedbackType.WARNING -> null // custom pulse
            HapticFeedbackType.ERROR -> null // custom pulse
            HapticFeedbackType.SELECTION -> VibrationEffect.EFFECT_TICK
        }

        if (effectId != null) {
            vibrator.vibrate(VibrationEffect.createPredefined(effectId))
            return
        }
    }

    // Fallback or Status types (SUCCESS, WARNING, ERROR)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val effect = when (type) {
            HapticFeedbackType.SUCCESS -> VibrationEffect.createWaveform(
                longArrayOf(0, 35, 60, 45),
                intArrayOf(0, 180, 0, 255),
                -1
            )
            HapticFeedbackType.WARNING -> VibrationEffect.createWaveform(
                longArrayOf(0, 40, 60, 40),
                intArrayOf(0, 220, 0, 220),
                -1
            )
            HapticFeedbackType.ERROR -> VibrationEffect.createWaveform(
                longArrayOf(0, 50, 40, 50, 40, 50),
                intArrayOf(0, 255, 0, 255, 0, 255),
                -1
            )
            HapticFeedbackType.HEAVY_IMPACT -> VibrationEffect.createOneShot(50, 255)
            HapticFeedbackType.MEDIUM_IMPACT -> VibrationEffect.createOneShot(30, 180)
            HapticFeedbackType.LIGHT_IMPACT -> VibrationEffect.createOneShot(15, 100)
            HapticFeedbackType.SOFT_IMPACT -> VibrationEffect.createOneShot(20, 80)
            HapticFeedbackType.RIGID_IMPACT -> VibrationEffect.createOneShot(25, 240)
            HapticFeedbackType.CLICK -> VibrationEffect.createOneShot(25, 150)
            HapticFeedbackType.DOUBLE_CLICK -> VibrationEffect.createWaveform(
                longArrayOf(0, 25, 50, 25),
                intArrayOf(0, 150, 0, 150),
                -1
            )
            HapticFeedbackType.TICK, HapticFeedbackType.SELECTION -> VibrationEffect.createOneShot(10, 80)
        }
        vibrator.vibrate(effect)
    } else {
        @Suppress("DEPRECATION")
        val duration = when (type) {
            HapticFeedbackType.ERROR -> 150L
            HapticFeedbackType.WARNING -> 80L
            HapticFeedbackType.HEAVY_IMPACT -> 50L
            HapticFeedbackType.MEDIUM_IMPACT -> 30L
            else -> 20L
        }
        @Suppress("DEPRECATION")
        vibrator.vibrate(duration)
    }
}

actual fun performHapticPattern(pattern: HapticPattern) {
    val vibrator = getVibrator() ?: return
    if (!vibrator.hasVibrator()) return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val effect = if (pattern.amplitudes.isNotEmpty() && pattern.amplitudes.size == pattern.timings.size) {
            VibrationEffect.createWaveform(pattern.timings, pattern.amplitudes, -1)
        } else {
            VibrationEffect.createWaveform(pattern.timings, -1)
        }
        vibrator.vibrate(effect)
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(pattern.timings, -1)
    }
}

actual fun cancelHaptic() {
    val vibrator = getVibrator() ?: return
    vibrator.cancel()
}
