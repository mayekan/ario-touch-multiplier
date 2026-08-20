package com.ariolabs.touchmultiplier

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 50, 50, 50)
        }

        val tvStatus = TextView(this).apply {
            text = "برای استفاده، دسترسی Accessibility و Overlay را فعال کنید.\nبا کلیدهای ولوم صدا، قابلیت فعال/غیرفعال می‌شود."
            textSize = 16f
            setPadding(0, 0, 0, 30)
        }
        layout.addView(tvStatus)

        val btnAccessibility = Button(this).apply {
        }
    }
}
