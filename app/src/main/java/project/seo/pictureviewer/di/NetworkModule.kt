package project.seo.pictureviewer.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import project.seo.pictureviewer.BuildConfig
import io.github.seoj17.data.network.PicturesAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val TIME_LIMIT = 10L

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return "https://picsum.photos"
    }

    @Singleton
    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIME_LIMIT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        url: String,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient).build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): PicturesAPI {
        return retrofit.create(PicturesAPI::class.java)
    }

}