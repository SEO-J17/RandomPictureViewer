package project.seo.pictureviewer.data.model

import project.seo.pictureviewer.data.database.PictureEntity
import project.seo.pictureviewer.data.network.PictureResponse

data class DataPicture(
    val id: Int,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val downloadUrl: String,
) {
    companion object {
        operator fun invoke(entity: List<PictureEntity>): List<DataPicture> {
            return entity.map {
                DataPicture(
                    id = it.id,
                    author = it.author,
                    width = it.width,
                    height = it.height,
                    url = it.url,
                    downloadUrl = it.downloadUrl
                )
            }
        }

        operator fun invoke(entity: PictureEntity): DataPicture {
            return DataPicture(
                id = entity.id,
                author = entity.author,
                width = entity.width,
                height = entity.height,
                url = entity.url,
                downloadUrl = entity.downloadUrl,
            )
        }

        operator fun invoke(response: PictureResponse): DataPicture {
            return DataPicture(
                id = response.id,
                author = response.author,
                width = response.width,
                height = response.height,
                url = response.url,
                downloadUrl = response.downloadUrl,
            )
        }
    }
}