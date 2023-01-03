package project.seo.pictureviewer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.doamain.model.DomainPicture
import project.seo.pictureviewer.databinding.ListItemBinding

class MainListViewHolder(
    private val binding: ListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pictureData: DomainPicture) {
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
