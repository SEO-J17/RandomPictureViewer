package project.seo.pictureviewer.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import project.seo.pictureviewer.data.network.PicturesAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    @Named("connectTimeoutPolicy")
    fun provideConnectTimeoutPolicy(): Long {
        return 10_000L
    }

    @Singleton
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "https://picsum.photos"
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @Named("baseUrl") url: String,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): PicturesAPI {
        return retrofit.create(PicturesAPI::class.java)
    }

}