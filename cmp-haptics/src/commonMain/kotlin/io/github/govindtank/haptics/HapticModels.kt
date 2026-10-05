package io.github.govindtank.haptics

/**
 * Standard semantic haptic feedback presets mapped across Android Material You and iOS CoreHaptics/UIFeedback.
 */
enum class HapticFeedbackType {
    // Standard Interactions
    CLICK,
    DOUBLE_CLICK,
    TICK,
    SELECTION,

    // Impact Strengths
    LIGHT_IMPACT,
    MEDIUM_IMPACT,
    HEAVY_IMPACT,
    RIGID_IMPACT,
    SOFT_IMPACT,

    // Notification / Status
    SUCCESS,
    WARNING,
    ERROR
}

/**
 * Custom vibration waveform pattern.
 *
 * @property timings Alternating off/on durations in milliseconds (e.g. `longArrayOf(0, 100, 50, 100)`).
 * @property amplitudes Vibration strength (1..255) for each duration if supported by hardware, or empty.
 */
data class HapticPattern(
    val timings: LongArray,
    val amplitudes: IntArray = intArrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HapticPattern) return false
        if (!timings.contentEquals(other.timings)) return false
        if (!amplitudes.contentEquals(other.amplitudes)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = timings.contentHashCode()
        result = 31 * result + amplitudes.contentHashCode()
        return result
    }
}
