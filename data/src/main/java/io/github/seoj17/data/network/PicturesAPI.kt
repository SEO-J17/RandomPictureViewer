package io.github.seoj17.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicturesAPI {
    @GET("/v2/list")
    fun getPicture(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Call<List<PictureResponse>>

    @GET("/id/{id}/info")
    fun getPictureData(
        @Path("id") id: Int,
    ): Call<PictureResponse>
}