package net.deali.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.deali.data.repository.FeatureARepositoryImpl
import net.deali.data.service.ApiService
import net.deali.domain.repository.FeatureARepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(service: ApiService): FeatureARepository {
        return FeatureARepositoryImpl(service)
    }
}