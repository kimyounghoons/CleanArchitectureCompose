package net.deali.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.deali.data.mapper.PopularMovieMapper
import net.deali.data.repository.PopularRepositoryImpl
import net.deali.data.service.PopularApiService
import net.deali.domain.repository.PopularRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PopularMoviesApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): PopularApiService {
        return retrofit.create(PopularApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        service: PopularApiService,
        popularMovieMapper: PopularMovieMapper
    ): PopularRepository {
        return PopularRepositoryImpl(service, popularMovieMapper)
    }

    @Provides
    fun provideMovieSearchMapper(): PopularMovieMapper = PopularMovieMapper()
}