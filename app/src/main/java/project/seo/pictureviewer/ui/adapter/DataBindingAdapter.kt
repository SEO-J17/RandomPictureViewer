package project.seo.pictureviewer.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.seo.pictureviewer.R
import project.seo.pictureviewer.data.PictureData

@BindingAdapter("bind:list")
fun RecyclerView.setList(list: List<PictureData>?) {
    (adapter as? ListAdapter)?.updatePictures(list ?: emptyList())
}

@BindingAdapter("bind:imgUrl")
fun ImageView.setImage(url: String?) {
    Glide.with(this.context)
        .load(url ?: R.drawable.no_image)
        .placeholder(R.drawable.default_image)
        .into(this)
}