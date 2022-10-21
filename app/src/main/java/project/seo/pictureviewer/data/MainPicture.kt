package project.seo.pictureviewer.data

import com.google.gson.annotations.SerializedName

data class MainPicture(
    @SerializedName("author")
    val author: String,
    @SerializedName("download_url")
    val url: String,
) {
    companion object {
        operator fun invoke(data: MainPicture): MainPicture {
            return with(data) {
                MainPicture(author, url)
            }
        }
    }
}
