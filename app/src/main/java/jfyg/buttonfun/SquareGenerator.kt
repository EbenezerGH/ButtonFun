package jfyg.buttonfun

import kotlin.math.roundToInt

object SquareGenerator {

    private const val SIZE_OF_SQUARE = 40

    private var heightInDp: Float? = null
        get() =
            field ?: handleException(DIMENS_NOT_CALCULATED)

    private var widthInDp: Float? = null
        get() =
            field ?: handleException(DIMENS_NOT_CALCULATED)

    private fun handleException(msg: String): Nothing {
        throw AssertionError(msg)
    }

    fun setScreenDimens(dpHeight: Float, dpWidth: Float) {
        heightInDp = dpHeight
        widthInDp = dpWidth
    }

    fun listOfGeneratedSquares(): ArrayList<Square> {
        return numOfSquares().intToListOfSquares()
    }

    // my goal here is to calculate how many 40dp square can fit horizontally
    fun numOfColumns() = (widthInDp?.let {
        it.div(SIZE_OF_SQUARE).roundToInt()
    } ?: handleException(DIMENS_NOT_CALCULATED))


    // i'll do the same here except vertically
    private fun numOfRows() = (heightInDp?.let {
        it.div(SIZE_OF_SQUARE).roundToInt()
    } ?: handleException(DIMENS_NOT_CALCULATED))

    // multiplying col * row will get me how many squares can fit on the screen in total,
    // I'll make a list of square objects here
    private fun numOfSquares() = (numOfColumns() * numOfRows())
}