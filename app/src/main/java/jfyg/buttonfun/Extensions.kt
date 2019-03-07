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

fun List<Square>.toGsonString(): String {
    val squaresListType = object : TypeToken<List<Square>>() {}.type
    val gson = Gson()
    val target: List<Square> = this
    return gson.toJson(target, squaresListType)
}

fun String.toSquaresList(): List<Square> {
    val gson = Gson()
    val squaresListType = object : TypeToken<List<Square>>() {}.type
    return gson.fromJson<List<Square>>(this, squaresListType)
}

