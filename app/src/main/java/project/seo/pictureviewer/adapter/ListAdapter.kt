package project.seo.pictureviewer.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.seo.pictureviewer.data.PictureData

class ListAdapter(
    private val dataSet: MutableList<PictureData> = mutableListOf(),
    private val itemClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder = ListViewHolder(parent)

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            bind(dataSet[position])
            itemView.setOnClickListener {
                itemClickListener(dataSet[position].id)
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun updatePictures(pictureInfo: List<PictureData>) {
        dataSet.clear()
        dataSet.addAll(pictureInfo)
        notifyDataSetChanged()
    }
}