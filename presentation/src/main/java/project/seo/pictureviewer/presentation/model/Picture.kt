package project.seo.pictureviewer.presentation.model

import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import project.seo.pictureviewer.domain.model.DomainPicture

data class Picture(
    val id: Int,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val downloadUrl: String,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Picture>() {
            override fun areItemsTheSame(
                oldItem: Picture,
                newItem: Picture,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Picture,
                newItem: Picture,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(data: DomainPicture): Picture {
            return Picture(
                id = data.id,
                author = data.author,
                width = data.width,
                height = data.height,
                url = data.url,
                downloadUrl = data.downloadUrl,
            )
        }

        operator fun invoke(pagingData: PagingData<DomainPicture>): PagingData<Picture> {
            return pagingData.map { data ->
                Picture(
                    id = data.id,
                    author = data.author,
                    width = data.width,
                    height = data.height,
                    url = data.url,
                    downloadUrl = data.downloadUrl,
                )
            }
        }
    }
}