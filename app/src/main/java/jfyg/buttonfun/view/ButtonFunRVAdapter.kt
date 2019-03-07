package jfyg.buttonfun.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import jfyg.buttonfun.R
import jfyg.buttonfun.model.Square

class ButtonFunRVAdapter :
    RecyclerView.Adapter<ButtonFunRVAdapter.ViewHolder>() {

    var listOfSquares: List<Square> = emptyList()
    var onItemClick: ((position: Int, layout: FrameLayout) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listOfSquares.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(listOfSquares[position])
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val layout: FrameLayout = v.findViewById(R.id.iv_square)

        init {
            v.setOnClickListener {
                onItemClick?.invoke(adapterPosition, layout)
            }
        }

        fun bindItems(square: Square) {
            layout.setBackgroundColor(square.color)
        }
    }

}