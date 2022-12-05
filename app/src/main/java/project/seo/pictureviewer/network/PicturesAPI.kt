package project.seo.pictureviewer.network

import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.data.PictureInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicturesAPI {
    @GET("/v2/list")
    suspend fun getPicture(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): PictureInfo

    @GET("/id/{id}/info")
    suspend fun getPictureData(
        @Path("id") id: Int
    ): PictureData
}