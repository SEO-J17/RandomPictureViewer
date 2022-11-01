package project.seo.pictureviewer.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private const val requestUrl = "https://picsum.photos"

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(20000L, TimeUnit.SECONDS)
            .build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(requestUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    private val api = retrofit.create(PicturesAPI::class.java)

    suspend fun getPicture(page: Int = 1, limit: Int = 100) = api.getPicture(page, limit)

}