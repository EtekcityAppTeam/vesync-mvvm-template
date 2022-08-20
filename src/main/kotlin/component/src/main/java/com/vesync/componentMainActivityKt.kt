package component.src.main.java.com.vesync

fun componentMainActivityKt(
    componentName: String
) = """
package com.vesync

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vesync.${componentName}.R

class ${componentName}MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_${componentName.toLowerCase()}_main)
    }
}
""".trimIndent()