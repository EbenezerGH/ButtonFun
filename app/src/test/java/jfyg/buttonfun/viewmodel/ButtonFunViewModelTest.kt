package jfyg.buttonfun.viewmodel

import android.graphics.Color
import android.view.Surface.*
import jfyg.buttonfun.model.Square
import jfyg.buttonfun.viewmodel.ButtonFunViewModel.Companion.STATE_CHANGE_SQUARE
import jfyg.buttonfun.viewmodel.ButtonFunViewModel.Companion.STATE_CREATE_NEW
import jfyg.buttonfun.viewmodel.ButtonFunViewModel.Companion.STATE_NONE
import jfyg.utils.isEqualTo
import org.junit.Before
import org.junit.Test

class ButtonFunViewModelTest {
    lateinit var vm: ButtonFunViewModel

    @Before
    fun setUp() {
        vm = ButtonFunViewModel(2080F, 1080F)
    }

    @Test
    fun `verify live data value stores and updates`() {
        vm.squaresList
    }

    @Test
    fun `verify square spot color is changed on click`() {
        vm.listState = STATE_NONE
        vm.spotClicked(0)

        vm.listState isEqualTo STATE_CHANGE_SQUARE
    }

    @Test
    fun `restore backup list of squares if exists`() {
        vm.listState = STATE_NONE
        vm.retrieveSquaresList(listOf(Square(Color.BLUE)))

        vm.listState isEqualTo STATE_NONE
    }

    @Test
    fun `create new list of squares if none detected`() {
        vm.listState = STATE_NONE
        vm.retrieveSquaresList(emptyList())

        vm.listState isEqualTo STATE_CREATE_NEW
    }

    @Test
    fun `correct spans are created per rotation`() {
        vm.spanCountListener(ROTATION_0) isEqualTo 27
        vm.spanCountListener(ROTATION_180) isEqualTo 27
        vm.spanCountListener(ROTATION_90) isEqualTo 52
        vm.spanCountListener(ROTATION_270) isEqualTo 52
    }

}