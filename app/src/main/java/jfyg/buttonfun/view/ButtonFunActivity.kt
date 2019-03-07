package jfyg.buttonfun.view

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import jfyg.buttonfun.R
import jfyg.buttonfun.data.DataManager
import jfyg.buttonfun.model.Square
import jfyg.buttonfun.viewmodel.ButtonFunViewModel
import jfyg.utils.deviceOrientation
import kotlinx.android.synthetic.main.grid_container.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ButtonFunActivity : BaseActivity() {
    private val adapter: ButtonFunRVAdapter by inject()
    private val dataManager: DataManager by inject()

    private val vm: ButtonFunViewModel by viewModel {
        val display = this.windowManager?.defaultDisplay
        val outMetrics = DisplayMetrics().also { display?.getMetrics(it) }
        val density = resources.displayMetrics.density

        parametersOf(
            outMetrics.heightPixels / density,
            outMetrics.widthPixels / density
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_container)
        initRecyclerView()
        adapterClickListener()
    }

    private fun initRecyclerView() {
        rv_squares.layoutManager =
            GridLayoutManager(this, vm.spanCountListener(this.deviceOrientation()))
        rv_squares.adapter = adapter
    }

    private fun adapterClickListener() {
        adapter.onItemClick = { position, layout ->
            Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
            layout.setBackgroundColor(Color.BLACK)
            vm.spotClicked(position)
        }
    }

    override fun onStart() {
        super.onStart()
        vm.retrieveSquaresList(this.let { dataManager.readFromFile(it) })
        vm.squaresList.observe(this, squareslistObserver)
    }

    override fun onPause() {
        super.onPause()
        GlobalScope.launch {
            dataManager.writeToFile(vm.squaresList.value ?: emptyList())
        }
        vm.listState = ButtonFunViewModel.STATE_NONE
        vm.squaresList.removeObserver(squareslistObserver)
    }

    private val squareslistObserver = Observer<List<Square>> {
        when (vm.listState) {
            ButtonFunViewModel.STATE_NONE -> {
                adapter.listOfSquares = it
            }
            ButtonFunViewModel.STATE_CREATE_NEW -> {
                adapter.listOfSquares = it
                dataManager.writeToFile(it)
            }
            ButtonFunViewModel.STATE_CHANGE_SQUARE -> adapter.listOfSquares = it
        }
    }

}