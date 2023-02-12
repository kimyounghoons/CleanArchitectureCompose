package net.deali.navigator

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NavigatorModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = NavigatorImpl()
}