package jfyg.buttonfun

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jfyg.buttonfun.model.Square
import jfyg.utils.generateRandomColor

fun Int.intToListOfSquares(): List<Square> {
    val a = mutableListOf<Square>()
    for (i in 0 until this) {
        a.add(Square(generateRandomColor()))
    }
    return a
}
