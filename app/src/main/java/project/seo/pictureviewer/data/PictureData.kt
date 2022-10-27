package project.seo.pictureviewer.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PictureData(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("url")
    val pageUrl: String,
    @SerializedName("download_url")
    val imageUrl: String,
) {
    companion object {
        operator fun invoke(data: PictureData): PictureData {
            return with(data) {
                PictureData(id, author, width, height, pageUrl, imageUrl)
            }
        }
    }
}
