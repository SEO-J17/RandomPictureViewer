package project.seo.pictureviewer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import project.seo.pictureviewer.data.PictureRepository
import project.seo.pictureviewer.data.PictureRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(mainRepositoryImpl: PictureRepositoryImpl): PictureRepository
}