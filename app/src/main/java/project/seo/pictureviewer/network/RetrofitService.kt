package project.seo.pictureviewer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val requestUrl = "https://picsum.photos"
    val retrofit = Retrofit.Builder().baseUrl(requestUrl).addConverterFactory(
        GsonConverterFactory.create()).build()
    val api = retrofit.create(PicturesAPI::class.java)
}