package jfyg.buttonfun.data

import android.content.Context
import jfyg.buttonfun.model.Square

/*
When it came to saving data, I had 3 options:
1) I had the option of passing data as a bundle, while that would work for this simple usecase, It is just not practical
for bigger projects since bundle space is limited.  Also saving an object like a list is not a good idea, even if I was to
serialize it.
2) I thought about using Room, which would be a safe option. It would have blended well with with livedata and
viewmodel, but overall it is just too much of an overkill to add extra imports and boilerplate just to store an object list.

Saving to a file seemed to be the optimal choice here.  Since i'm only saving 1 data object.  Since this object is a collection,
I chose to persist the data as a file.  I added the option to handle a sharedPreference inthis class as well.
 */
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