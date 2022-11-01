package project.seo.pictureviewer.network

import project.seo.pictureviewer.data.PictureInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesAPI {
    @GET("/v2/list")
    suspend fun getPicture(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): PictureInfo
}