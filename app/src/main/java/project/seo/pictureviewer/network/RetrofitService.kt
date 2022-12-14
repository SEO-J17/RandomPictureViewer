package project.seo.pictureviewer.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import project.seo.pictureviewer.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private const val requestUrl = "https://picsum.photos"
    const val startPage = 1
    const val endPage = 100

    private val httpInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val client =
        OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(requestUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

    private val api = retrofit.create(PicturesAPI::class.java)

    suspend fun getPicture(page: Int = startPage, limit: Int = endPage) =
        api.getPicture(page, limit)

    suspend fun getPictureData(id: Int) = api.getPictureData(id)

}