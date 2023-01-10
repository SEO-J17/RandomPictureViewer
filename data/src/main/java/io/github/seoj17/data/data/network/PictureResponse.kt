package io.github.seoj17.data.data.network

import com.squareup.moshi.Json

data class PictureResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "author")
    val author: String,
    @Json(name = "width")
    val width: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "download_url")
    val downloadUrl: String,
)
