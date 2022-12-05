package project.seo.pictureviewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.databinding.ListItemBinding

class ListViewHolder(
    private val binding: ListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pictureData: PictureData) {
        binding.pictures.load(pictureData.downloadUrl)
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
