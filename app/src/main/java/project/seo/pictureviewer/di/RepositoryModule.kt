package project.seo.pictureviewer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import project.seo.pictureviewer.data.DetailRepository
import project.seo.pictureviewer.data.DetailRepositoryImpl
import project.seo.pictureviewer.data.MainRepository
import project.seo.pictureviewer.data.MainRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun bindDetailRepository(detailRepository: DetailRepositoryImpl): DetailRepository
}