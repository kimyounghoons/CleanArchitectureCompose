package net.deali.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.deali.data.mapper.MovieSearchKeywordMapper
import net.deali.data.mapper.MovieSearchMapper
import net.deali.data.repository.SearchRepositoryImpl
import net.deali.data.service.SearchApiService
import net.deali.domain.repository.SearchRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        service: SearchApiService,
        movieSearchMapper: MovieSearchMapper,
        movieSearchKeywordMapper: MovieSearchKeywordMapper
    ): SearchRepository {
        return SearchRepositoryImpl(service, movieSearchMapper, movieSearchKeywordMapper)
    }

    @Provides
    fun provideMovieSearchMapper(): MovieSearchMapper = MovieSearchMapper()

    @Provides
    fun provideMovieSearchKeywordMapper(): MovieSearchKeywordMapper = MovieSearchKeywordMapper()
}

