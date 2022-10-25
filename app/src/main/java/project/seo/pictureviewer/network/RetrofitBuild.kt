package project.seo.pictureviewer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuild {
    private const val requestUrl = "https://picsum.photos"
    //레트로핏 객체 생성.
    val retrofit = Retrofit.Builder().baseUrl(requestUrl).addConverterFactory(
        GsonConverterFactory.create()).build()
    //레트로핏이 API에 명시된 규칙에 따라서 객체를 최종적으로 생성한다.
    val api = retrofit.create(PicturesAPI::class.java)
}