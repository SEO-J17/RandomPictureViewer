package project.seo.pictureviewer

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.seo.pictureviewer.data.PictureData

class ListAdapter(
    private val dataSet: MutableList<PictureData> = mutableListOf(),
    private val itemClickListener: (Int) -> Unit,
) :
    RecyclerView.Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return when (viewType) {
            0 -> ListViewHolder(parent)
            else -> ListViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            bind(dataSet[position])
            itemView.setOnClickListener {
                itemClickListener(position)
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateData(data: List<PictureData>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

}