package jfyg.buttonfun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import kotlinx.android.synthetic.main.grid_container.*


class MainActivity : AppCompatActivity() {

    private val KEY_RECYCLER_STATE = "recycler_state"
    private var mBundleRecyclerViewState: Bundle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowFlags() // maybe set this a different way
        setContentView(R.layout.activity_main)

        calculateAndSetScreenDensity()
        initButtonGridFragment()
    }

    private fun setWindowFlags() =
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    private fun initButtonGridFragment() {
        val fm = supportFragmentManager
        var frag = fm.findFragmentById(R.id.container)
        if (frag == null) {
            frag = ButtonFunRVFragment.newInstance()
            fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.container, frag, ButtonFunRVFragment.TAG)
                .commit()
        }
    }

    private fun calculateAndSetScreenDensity() {
        val display = this.windowManager?.defaultDisplay
        val outMetrics = DisplayMetrics().also {
            display?.getMetrics(it)
        }
        val density = resources.displayMetrics.density

        SquareGenerator.setScreenDimens(
            outMetrics.heightPixels / density,
            outMetrics.widthPixels / density
        )
    }

}