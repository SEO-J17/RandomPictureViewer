package project.seo.pictureviewer.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import project.seo.pictureviewer.data.model.DataPicture
import project.seo.pictureviewer.data.network.PictureResponse

@Entity(tableName = "pictures")
data class PictureEntity(
    @ColumnInfo(name = "id", index = true)
    @PrimaryKey
    var id: Int,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "width")
    var width: String,

    @ColumnInfo(name = "height")
    var height: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "download_url")
    var downloadUrl: String,
) {
    companion object {
        operator fun invoke(data: DataPicture): PictureEntity {
            return PictureEntity(
                id = data.id,
                author = data.author,
                width = data.width,
                height = data.height,
                url = data.url,
                downloadUrl = data.downloadUrl,
            )
        }

        operator fun invoke(response: PictureResponse): PictureEntity {
            return PictureEntity(
                id = response.id,
                author = response.author,
                width = response.width,
                height = response.height,
                url = response.url,
                downloadUrl = response.downloadUrl,
            )
        }

        operator fun invoke(response: List<PictureResponse>): List<PictureEntity> {
            return response.map {
                invoke(it)
            }
        }
    }
}