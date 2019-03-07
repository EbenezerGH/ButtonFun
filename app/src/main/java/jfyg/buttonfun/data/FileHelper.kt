package jfyg.buttonfun.data

import android.content.Context
import jfyg.buttonfun.model.Square
import jfyg.buttonfun.toGsonString
import jfyg.buttonfun.toSquaresList
import java.io.*

class FileHelper constructor(val context: Context) {
    companion object {
        const val SQUARES_LIST_FILENAME = "squares_list_file"
    }

    fun writeToFile(squaresList: List<Square>) {
        try {
            context.openFileOutput(SQUARES_LIST_FILENAME, Context.MODE_PRIVATE).use {
                it.write(squaresList.toGsonString().toByteArray())
                it.close()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFromFile(context: Context): List<Square> {
        var squaresListString = ""

        try {
            val fileInputStream = context.openFileInput(SQUARES_LIST_FILENAME)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuffer = StringBuffer()
            for (lines in bufferedReader.readLine()) {
                squaresListString = stringBuffer.append(lines).toString()
            }

        } catch (e: FileNotFoundException) {
            return emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return squaresListString.toSquaresList()
    }

}