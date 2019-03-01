package jfyg.buttonfun

import android.graphics.Color
import java.util.*

data class Square(
    val num: Int
) {
    fun randomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}