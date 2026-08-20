package com.ariolabs.touchmultiplier

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Path
import android.os.Build
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import androidx.core.app.NotificationCompat

class TouchMultiplierService : AccessibilityService() {

    private var isMultiplierActive = false
    private val CHANNEL_ID = "TouchMultiplierChannel"
    private val NOTIFICATION_ID = 101

    override fun onServiceConnected() {
        super.onServiceConnected()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification("Service Ready (Idle)"))
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (isMultiplierActive && event?.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            // Multi-touch simulation trigger point
        }
    }

    override fun onKeyEvent(event: KeyEvent?): Boolean {
        if (event == null) return super.onKeyEvent(event)

        val keyCode = event.keyCode
        val action = event.action

        if (action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP -> {
                    isMultiplierActive = true
                    updateNotification("10x Multiplier Active 🟢")
                    return true
                }
                KeyEvent.KEYCODE_VOLUME_DOWN -> {
                    isMultiplierActive = false
                    updateNotification("Paused 🔴")
                    return true
                }
            }
        }
        return super.onKeyEvent(event)
    }

    private fun triggerMultipleTaps(x: Float, y: Float) {
        val path = Path().apply { moveTo(x, y) }
        val strokeDescription = GestureDescription.StrokeDescription(path, 0, 50)
        val gestureBuilder = GestureDescription.Builder().addStroke(strokeDescription)

        for (i in 0 until 10) {
            dispatchGesture(gestureBuilder.build(), object : GestureResultCallback() {}, null)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Touch Multiplier Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun createNotification(statusText: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Ario Touch Multiplier")
            .setContentText(statusText)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setOngoing(true)
            .build()
    }

    private fun updateNotification(statusText: String) {
        val manager = getSystemService(NotificationManager::class.java)
        manager?.notify(NOTIFICATION_ID, createNotification(statusText))
    }

    override fun onInterrupt() {
        isMultiplierActive = false
    }
}