package com.ariolabs.touchmultiplier

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import android.os.Handler
import android.os.Looper

class TouchMultiplierService : AccessibilityService() {

    companion object {
        var instance: TouchMultiplierService? = null
            private set
    }

    var isEnabled = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}

    override fun onKeyEvent(event: KeyEvent): Boolean {
        // فشردن دکمه ولوم بالا یا پایین برای فعال/غیرفعال کردن
        if (event.action == KeyEvent.ACTION_DOWN) {
            if (event.keyCode == KeyEvent.KEYCODE_VOLUME_UP || event.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                isEnabled = !isEnabled
                return true
            }
        }
        return super.onKeyEvent(event)
    }

    fun performMultiTap(x: Float, y: Float) {
        if (!isEnabled) return

        for (i in 0 until 10) {
            handler.postDelayed({
                val path = Path().apply {
                    moveTo(x, y)
                }
                val stroke = GestureDescription.StrokeDescription(path, 0, 30)
                val builder = GestureDescription.Builder().addStroke(stroke)
                dispatchGesture(builder.build(), null, null)
            }, (i * 25).toLong()) // هر ۲۵ میلی‌ثانیه یک کلیک سریع
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

    override fun onInterrupt() {}
}
