package jfyg.buttonfun

import android.content.res.Resources
import java.util.*

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Int.intToListOfSquares(): ArrayList<Square> {
    val a = arrayListOf<Square>()
    (1..this).forEach { i -> a.add(Square(i)) }
    return a
}
