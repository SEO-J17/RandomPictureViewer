package project.seo.pictureviewer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import project.seo.pictureviewer.navigator.AppNavigator
import project.seo.pictureviewer.navigator.AppNavigatorImpl

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}