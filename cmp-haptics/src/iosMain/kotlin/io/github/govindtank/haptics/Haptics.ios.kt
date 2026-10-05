package io.github.govindtank.haptics

import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle
import platform.UIKit.UINotificationFeedbackGenerator
import platform.UIKit.UINotificationFeedbackType
import platform.UIKit.UISelectionFeedbackGenerator

actual fun hapticInit(context: Any?) {
    // No context needed for iOS UIKit feedback generators
}

actual fun performHapticFeedback(type: HapticFeedbackType) {
    when (type) {
        HapticFeedbackType.CLICK,
        HapticFeedbackType.MEDIUM_IMPACT -> {
            val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium)
            generator.prepare()
            generator.impactOccurred()
        }
        HapticFeedbackType.LIGHT_IMPACT,
        HapticFeedbackType.TICK -> {
            val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)
            generator.prepare()
            generator.impactOccurred()
        }
        HapticFeedbackType.HEAVY_IMPACT -> {
            val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy)
            generator.prepare()
            generator.impactOccurred()
        }
        HapticFeedbackType.RIGID_IMPACT -> {
            val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleRigid)
            generator.prepare()
            generator.impactOccurred()
        }
        HapticFeedbackType.SOFT_IMPACT -> {
            val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleSoft)
            generator.prepare()
            generator.impactOccurred()
        }
        HapticFeedbackType.SELECTION -> {
            val generator = UISelectionFeedbackGenerator()
            generator.prepare()
            generator.selectionChanged()
        }
        HapticFeedbackType.DOUBLE_CLICK -> {
            val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium)
            generator.prepare()
            generator.impactOccurred()
        }
        HapticFeedbackType.SUCCESS -> {
            val generator = UINotificationFeedbackGenerator()
            generator.prepare()
            generator.notificationOccurred(UINotificationFeedbackType.UINotificationFeedbackTypeSuccess)
        }
        HapticFeedbackType.WARNING -> {
            val generator = UINotificationFeedbackGenerator()
            generator.prepare()
            generator.notificationOccurred(UINotificationFeedbackType.UINotificationFeedbackTypeWarning)
        }
        HapticFeedbackType.ERROR -> {
            val generator = UINotificationFeedbackGenerator()
            generator.prepare()
            generator.notificationOccurred(UINotificationFeedbackType.UINotificationFeedbackTypeError)
        }
    }
}

actual fun performHapticPattern(pattern: HapticPattern) {
    // Basic pulse on iOS fallback
    val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy)
    generator.prepare()
    generator.impactOccurred()
}

actual fun cancelHaptic() {
    // No-op for standard iOS transient feedback generators
}
