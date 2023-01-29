package project.seo.pictureviewer.presentation.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import project.seo.pictureviewer.domain.model.DomainPicture
import project.seo.pictureviewer.presentation.R
import project.seo.pictureviewer.presentation.model.Picture
import project.seo.pictureviewer.presentation.ui.main.MainListAdapter
import project.seo.pictureviewer.presentation.utils.coroutineScope

@BindingAdapter("bind:list")
fun RecyclerView.setList(list: PagingData<DomainPicture>) {
    coroutineScope?.launch {
        (adapter as? MainListAdapter)?.submitData(Picture(list))
    }
}

@BindingAdapter("bind:imgUrl")
fun ImageView.setImage(url: String?) {
    this.clipToOutline = true
    Glide.with(this.context)
        .load(url ?: R.drawable.no_image)
        .placeholder(R.drawable.default_image)
        .into(this)
}