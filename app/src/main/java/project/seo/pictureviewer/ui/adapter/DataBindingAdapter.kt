package project.seo.pictureviewer.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import project.seo.pictureviewer.R
import project.seo.pictureviewer.data.Picture
import project.seo.pictureviewer.extensions.coroutineScope
import project.seo.pictureviewer.ui.main.MainListAdapter

@BindingAdapter("bind:list")
fun RecyclerView.setList(list: PagingData<Picture>) {
    coroutineScope?.launch {
        (adapter as? MainListAdapter)?.submitData(list)
    }
}

@BindingAdapter("bind:imgUrl")
fun ImageView.setImage(url: String?) {
    Glide.with(this.context)
        .load(url ?: R.drawable.no_image)
        .placeholder(R.drawable.default_image)
        .into(this)
}