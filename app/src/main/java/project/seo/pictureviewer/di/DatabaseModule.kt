package project.seo.pictureviewer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.data.database.PictureDao
import io.github.seoj17.data.database.PictureDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): PictureDatabase {
        return Room.databaseBuilder(
            appContext,
            PictureDatabase::class.java,
            "picture.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: PictureDatabase): PictureDao {
        return database.pictureDao()
    }
}