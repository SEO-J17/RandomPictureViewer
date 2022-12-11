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
    url?.let {
        Glide.with(this.context).load(url).error(R.drawable.no_image).into(this)
    } ?: Glide.with(this.context).load(R.drawable.no_image).into(this)
}