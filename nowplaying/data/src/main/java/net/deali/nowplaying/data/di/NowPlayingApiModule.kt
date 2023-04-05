package net.deali.nowplaying.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.deali.nowplaying.data.repository.NowPlayingRepositoryImpl
import net.deali.nowplaying.data.service.NowPlayingApiService
import net.deali.nowplaying.domain.repository.NowPlayingRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NowPlayingApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NowPlayingApiService {
        return retrofit.create(NowPlayingApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        service: NowPlayingApiService,
    ): NowPlayingRepository {
        return NowPlayingRepositoryImpl(service)
    }

}

