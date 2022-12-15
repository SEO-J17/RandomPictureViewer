package project.seo.pictureviewer.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.seo.pictureviewer.data.PictureData

class MainListAdapter(
    private val dataSet: MutableList<PictureData> = mutableListOf(),
    private val itemClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<MainListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MainListViewHolder = MainListViewHolder(parent)

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
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