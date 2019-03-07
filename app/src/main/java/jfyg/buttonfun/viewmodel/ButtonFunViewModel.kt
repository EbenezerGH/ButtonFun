package jfyg.buttonfun.viewmodel

import android.graphics.Color
import android.view.Surface.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jfyg.buttonfun.*
import jfyg.buttonfun.model.Square
import jfyg.utils.DEVICE_ORIENTATION_UNEXPECTED_ERROR
import jfyg.utils.DIMENS_NOT_CALCULATED_ERROR
import jfyg.utils.handleException

import kotlin.math.roundToInt

class ButtonFunViewModel constructor(height: Float, width: Float) : ViewModel() {
    companion object {
        private const val SIZE_OF_SQUARE = 40

        // This is just to manage states within my observer
        const val STATE_NONE = 0
        const val STATE_CREATE_NEW = 1
        const val STATE_CHANGE_SQUARE = 2
    }

    private var heightInDp: Float? = null
        get() =
            field ?: handleException(DIMENS_NOT_CALCULATED_ERROR)

    private var widthInDp: Float? = null
        get() =
            field ?: handleException(DIMENS_NOT_CALCULATED_ERROR)

    var listState: Int = STATE_NONE

    private val mutableSquaresList = MutableLiveData<List<Square>>()
    val squaresList: LiveData<List<Square>> = mutableSquaresList

    init {
        heightInDp = height
        widthInDp = width

        listState = STATE_NONE
    }

    // this is where I decide how many squares can fit in the width of any device, no matter the orientation
    fun spanCountListener(rotation: Int?): Int {
        return when (rotation) {
            ROTATION_0, ROTATION_180 -> numOfColumns()
            ROTATION_90, ROTATION_270 -> numOfRows()
            else -> handleException(DEVICE_ORIENTATION_UNEXPECTED_ERROR)
        }
    }

    fun retrieveSquaresList(list: List<Square>) {
        val data: List<Square> = if (list.isEmpty()) {
            listState = STATE_CREATE_NEW
            numOfSquares().intToListOfSquares()
        } else {
            list
        }

        mutableSquaresList.postValue(data)
    }

    fun spotClicked(spot: Int) {
        listState = STATE_CHANGE_SQUARE
        mutableSquaresList.value?.get(spot)?.color = Color.BLACK
    }

    // calculate how many 40dp square will fit horizontally
    private fun numOfColumns() = (widthInDp?.let {
        it.div(SIZE_OF_SQUARE).roundToInt()
    } ?: handleException(DIMENS_NOT_CALCULATED_ERROR))

    // calculate how many 40dp square will fit vertically
    private fun numOfRows() = (heightInDp?.let {
        it.div(SIZE_OF_SQUARE).roundToInt()
    } ?: handleException(DIMENS_NOT_CALCULATED_ERROR))

    // multiplying col * row will get me the total number of squares that can fit on the screen in total
    private fun numOfSquares() = (numOfColumns() * numOfRows())

}