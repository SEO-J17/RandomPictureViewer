package project.seo.pictureviewer.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import project.seo.pictureviewer.data.repository.PictureRepository
import project.seo.pictureviewer.domain.PictureRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(mainRepositoryImpl: PictureRepositoryImpl): PictureRepository
}