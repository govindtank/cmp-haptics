package io.github.govindtank.haptics

/**
 * Initializes the haptics engine (e.g. passing Android Context).
 */
expect fun hapticInit(context: Any? = null)

/**
 * Triggers a semantic predefined haptic pulse on the device.
 */
expect fun performHapticFeedback(type: HapticFeedbackType)

/**
 * Triggers a custom timed vibration waveform pattern on the device.
 */
expect fun performHapticPattern(pattern: HapticPattern)

/**
 * Cancels any active vibration or haptic feedback.
 */
expect fun cancelHaptic()
