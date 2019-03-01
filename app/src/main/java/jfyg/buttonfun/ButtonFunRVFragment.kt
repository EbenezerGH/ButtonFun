package jfyg.buttonfun

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grid_container.*

class ButtonFunRVFragment : Fragment() {

    companion object {
        const val TAG = "ButtonFunRVFragment"
        fun newInstance(): ButtonFunRVFragment {
            val frag = ButtonFunRVFragment()
            val bundle = Bundle()
            frag.arguments = bundle

            return frag
        }
    }

    private lateinit var adapter: ButtonFunRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.grid_container, container, false)
        adapter = ButtonFunRVAdapter(SquareGenerator.listOfGeneratedSquares())
        adapter.onItemClick = { position, layout ->
            Toast.makeText(context, "${adapter.listOfSquares[position].num}", Toast.LENGTH_SHORT).show()
            layout.setBackgroundColor(Color.BLACK)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lm: RecyclerView.LayoutManager = GridLayoutManager(context, SquareGenerator.numOfColumns())
        rv_buttons.layoutManager = lm
        rv_buttons.adapter = adapter
    }

}