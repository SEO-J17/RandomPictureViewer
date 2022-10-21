package project.seo.pictureviewer.data

import com.google.gson.annotations.SerializedName

data class PictureData(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("url")
    val pageUrl: String,
    @SerializedName("download_url")
    val imageUrl: String,
) {
    companion object {
        operator fun invoke(data: PictureData): PictureData {
            return with(data) {
                PictureData(id, author, pageUrl, imageUrl)
            }
        }
    }
}
