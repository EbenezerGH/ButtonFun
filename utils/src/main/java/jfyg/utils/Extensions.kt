package jfyg.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.Display
import android.view.WindowManager
import java.util.*

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Context.deviceOrientation(): Int {
    val display: Display = (this.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    return display.rotation
}

// util functions
fun generateRandomColor(): Int {
    val rnd = Random()
    return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}

fun handleException(msg: String): Nothing {
    throw AssertionError(msg)
}

