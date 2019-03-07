package jfyg.buttonfun.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import jfyg.buttonfun.model.Square
import jfyg.buttonfun.toGsonString
import jfyg.buttonfun.toSquaresList

class SharedPrefsHelper constructor(context: Context) {
    companion object {
        const val MY_PREFS = "MY_PREFS"
        const val SQUARES_LIST_KEY = "SQUARES_LIST_KEY"
    }

    private var sharedPrefs: SharedPreferences

    init {
        sharedPrefs = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE)
    }

    fun clear() {
        sharedPrefs.edit().clear().apply()
    }

    fun saveSquaresList(squaresList: List<Square>) {
        sharedPrefs.edit().putString(SQUARES_LIST_KEY, squaresList.toGsonString()).apply()
    }

    fun getSquaresList(): List<Square>? {
        val stringToObject = sharedPrefs.getString(SQUARES_LIST_KEY, null)
        return stringToObject?.toSquaresList()
    }

}