package project.seo.pictureviewer.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.seoj17.domain.model.DomainModel
import kotlinx.coroutines.launch
import project.seo.pictureviewer.R
import project.seo.pictureviewer.ui.main.MainListAdapter
import project.seo.pictureviewer.utils.coroutineScope

@BindingAdapter("bind:list")
fun RecyclerView.setList(list: PagingData<DomainModel>) {
    coroutineScope?.launch {
        (adapter as? MainListAdapter)?.submitData(list)
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