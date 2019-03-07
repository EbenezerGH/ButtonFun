package jfyg.buttonfun.data

import android.content.Context
import jfyg.buttonfun.model.Square


class DataManager constructor(private var sharedPrefs: SharedPrefsHelper, private var fileHelper: FileHelper) {

    fun writeToFile(squaresList: List<Square>) {
        fileHelper.writeToFile(squaresList)
    }

    fun readFromFile(context: Context): List<Square> {
        return fileHelper.readFromFile(context)
    }

    fun clear() {
        sharedPrefs.clear()
    }

    fun saveSquaresListToPrefs(squareList: List<Square>) {
        sharedPrefs.saveSquaresList(squareList)
    }

    fun getSquaresListFromPrefs(): List<Square>? {
        return sharedPrefs.getSquaresList()
    }

}