package project.seo.pictureviewer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.databinding.ListItemBinding

class ListViewHolder(
    private val binding: ListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pictureData: PictureData) {
        binding.data = pictureData
    }

    companion object {
        operator fun invoke(parent: ViewGroup): ListViewHolder {
            return ListViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}
