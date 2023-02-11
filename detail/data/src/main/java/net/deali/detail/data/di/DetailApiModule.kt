package net.deali.detail.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.deali.detail.data.mapper.DetailMapper
import net.deali.detail.data.repository.DetailRepositoryImpl
import net.deali.detail.data.service.DetailApiService
import net.deali.detail.domain.repository.DetailRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): DetailApiService {
        return retrofit.create(DetailApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        service: DetailApiService,
        detailMapper: DetailMapper
    ): DetailRepository {
        return DetailRepositoryImpl(service, detailMapper)
    }

    @Provides
    fun provideNowPlayingMapper(): DetailMapper = DetailMapper()
}