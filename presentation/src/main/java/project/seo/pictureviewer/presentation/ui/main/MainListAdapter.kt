package project.seo.pictureviewer.presentation.ui.main

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import project.seo.pictureviewer.presentation.model.Picture

class MainListAdapter(
    private val itemClickListener: (Int) -> Unit,
) : PagingDataAdapter<Picture, MainListViewHolder>(Picture.diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MainListViewHolder = MainListViewHolder(parent)

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val pictures = getItem(position) ?: return
        with(holder) {
            bind(pictures)
            itemView.setOnClickListener {
                itemClickListener(pictures.id)
            }
        }
    }
}