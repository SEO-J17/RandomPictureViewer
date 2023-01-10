package project.seo.pictureviewer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.data.data.PictureRepositoryImpl
import io.github.seoj17.domain.repository.PictureRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(mainRepositoryImpl: PictureRepositoryImpl): PictureRepository
}