package io.github.govindtank.haptics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * Controller for triggering cross-platform haptic feedback directly.
 */
class HapticManager {
    /**
     * Performs predefined haptic feedback.
     */
    fun perform(type: HapticFeedbackType) {
        performHapticFeedback(type)
    }

    /**
     * Performs a custom vibration pattern.
     */
    fun performPattern(pattern: HapticPattern) {
        performHapticPattern(pattern)
    }

    /**
     * Stops any ongoing vibration.
     */
    fun cancel() {
        cancelHaptic()
    }
}

/**
 * Remembers a singleton [HapticManager] instance in the current composition.
 */
@Composable
fun rememberHapticManager(): HapticManager {
    return remember { HapticManager() }
}

/**
 * Convenience modifier that triggers a haptic pulse whenever clicked.
 */
fun Modifier.hapticClickable(
    type: HapticFeedbackType = HapticFeedbackType.CLICK,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: androidx.compose.ui.semantics.Role? = null,
    onClick: () -> Unit
): Modifier = this.then(
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role
    ) {
        performHapticFeedback(type)
        onClick()
    }
)
