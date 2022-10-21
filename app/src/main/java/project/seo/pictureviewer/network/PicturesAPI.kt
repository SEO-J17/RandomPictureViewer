package project.seo.pictureviewer.network

import project.seo.pictureviewer.data.PictureInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesAPI {
    @GET("/v2/list")
    fun getPicture(@Query("page") page: Int, @Query("limit") limit: Int): Call<PictureInfo>
}