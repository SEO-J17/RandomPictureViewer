package prod.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideClient(
        @Named("connectTimeoutPolicy")
        connectTimeoutPolicy: Long,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(
                connectTimeoutPolicy,
                TimeUnit.MILLISECONDS,
            )
            .build()
    }

}


