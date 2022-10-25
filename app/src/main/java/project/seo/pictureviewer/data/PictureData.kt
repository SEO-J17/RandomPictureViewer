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
) : Serializable    //intent로 Detail 액티비티로 전달하기 위해 Serializable을 선언했다.
{
    companion object {
        operator fun invoke(data: PictureData): PictureData {       //연산자 오버로딩을 사용하여  invoke를 다르게 동작할 수 있도록 하였다.
            return with(data) {
                PictureData(id, author, width, height, pageUrl, imageUrl)
            }
        }
    }
}
