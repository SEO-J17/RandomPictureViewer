package project.seo.pictureviewer.data

import androidx.recyclerview.widget.DiffUtil
import project.seo.pictureviewer.database.PictureEntity
import project.seo.pictureviewer.network.PictureResponse

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

        operator fun invoke(response: PictureResponse): Picture {
            return Picture(
                id = response.id,
                author = response.author,
                width = response.width,
                height = response.height,
                url = response.url,
                downloadUrl = response.downloadUrl
            )
        }

        operator fun invoke(response: List<PictureResponse>): List<Picture> {
            return response.map {
                invoke(it)
            }
        }

        operator fun invoke(entity: PictureEntity): Picture {
            return Picture(
                id = entity.id,
                author = entity.author,
                width = entity.width,
                height = entity.height,
                url = entity.url,
                downloadUrl = entity.downloadUrl
            )
        }

        @JvmName("EntityToList")
        operator fun invoke(entity: List<PictureEntity>): List<Picture> {
            return entity.map {
                invoke(it)
            }
        }
    }
}