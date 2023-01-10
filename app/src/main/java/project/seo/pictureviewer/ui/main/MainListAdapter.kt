package project.seo.pictureviewer.ui.main

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import io.github.seoj17.domain.model.DomainModel

class MainListAdapter(
    private val itemClickListener: (Int) -> Unit,
) : PagingDataAdapter<DomainModel, MainListViewHolder>(DomainModel.diffUtil) {

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