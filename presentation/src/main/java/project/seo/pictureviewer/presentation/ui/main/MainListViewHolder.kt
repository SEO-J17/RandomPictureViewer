package project.seo.pictureviewer.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.seo.pictureviewer.presentation.databinding.ListItemBinding
import project.seo.pictureviewer.presentation.model.Picture

class MainListViewHolder(
    private val binding: ListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pictureData: Picture) {
        binding.data = pictureData
    }

    companion object {
        operator fun invoke(parent: ViewGroup): MainListViewHolder {
            return MainListViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}
