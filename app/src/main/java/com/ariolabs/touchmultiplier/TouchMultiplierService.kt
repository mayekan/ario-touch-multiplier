package com.ariolabs.touchmultiplier

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import android.os.Handler
import android.os.Looper

class TouchMultiplierService : AccessibilityService() {

    private var isEnabled = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // این سرویس آماده دریافت رویدادهاست
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        // فعال و غیرفعال شدن با کلیدهای صدا
        if (event.action == KeyEvent.ACTION_DOWN) {
            if (event.keyCode == KeyEvent.KEYCODE_VOLUME_UP || event.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                isEnabled = !isEnabled
                return true
            }
        }
        return super.onKeyEvent(event)
    }

    // تابع شبیه‌سازی ۱۰ ضربه متوالی در مختصات لمس‌شده
    fun triggerMultipleTaps(x: Float, y: Float) {
        if (!isEnabled) return

        for (i in 0 until 10) {
            handler.postDelayed({
                val path = Path().apply {
                    moveTo(x, y)
                }
                val stroke = GestureDescription.StrokeDescription(path, 0, 50)
                val builder = GestureDescription.Builder().addStroke(stroke)
                dispatchGesture(builder.build(), null, null)
            }, (i * 35).toLong()) // هر ۳۵ میلی‌ثانیه یک کلیک سریع
        }
    }

    override fun onInterrupt() {}
}
