package com.ariolabs.touchmultiplier

accessibility-service xmlns:android="http://schemas.android.com/apk/res/android"
import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent

class TouchMultiplierService : AccessibilityService() {

    private var isEnabled = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!isEnabled || event == null) return

        if (event.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED || 
            event.eventType == AccessibilityEvent.TYPE_TOUCH_INTERACTION_START) {
            // منطق شبیه‌سازی لمس در صورت نیاز به رصد رویدادهای ویو
        }
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        // کنترل با کلیدهای صدا (Volume Up یا Volume Down برای فعال/غیرفعال کردن)
        if (event.action == KeyEvent.ACTION_DOWN) {
            if (event.keyCode == KeyEvent.KEYCODE_VOLUME_UP || event.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                isEnabled = !isEnabled
                return true
            }
        }
        return super.onKeyEvent(event)
    }

    // شبیه‌سازی ۱۰ ضربه متوالی در مختصات x و y
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
            }, (i * 40).toLong()) // هر 40 میلی‌ثانیه یک ضربه (خیلی سریع و پشت سر هم)
        }
    }

    override fun onInterrupt() {}
}
