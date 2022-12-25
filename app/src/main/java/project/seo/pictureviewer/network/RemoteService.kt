package project.seo.pictureviewer.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import project.seo.pictureviewer.BuildConfig
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RemoteService {
    private const val BASE_URL = "https://picsum.photos"

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
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

    private val api = retrofit.create(PicturesAPI::class.java)

    suspend fun getPicture(page: Int, limit: Int): Result<List<PictureResponse>> {
        val response = api.getPicture(page, limit).awaitResponse()
        if (response.isSuccessful) {
            response.body()?.let {
                return Result.success(it)
            }
        }
        return Result.failure(Exception(response.message()))
    }
}
