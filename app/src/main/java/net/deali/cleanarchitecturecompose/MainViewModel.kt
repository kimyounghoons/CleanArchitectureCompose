package net.deali.cleanarchitecturecompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.core.BaseViewModel
import net.deali.coredomain.Resource
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.ErrorEntity
import net.deali.coredomain.entity.LoadingEntity
import net.deali.coredomain.entity.MovieEntity
import net.deali.domain.usecase.GetPopularMovieUseCase
import net.deali.nowplaying.domain.usecase.GetNowPlayingUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getNowPlayingUseCase: GetNowPlayingUseCase
) : BaseViewModel() {
    private val _popularItems = MutableLiveData<List<BaseEntity>>()
    val popularItems: LiveData<List<BaseEntity>> = _popularItems

    private val _nowPlayingItems = MutableLiveData<List<BaseEntity>>()
    val nowPlayingItems: LiveData<List<BaseEntity>> = _nowPlayingItems

    fun onRefresh() {
        _popularItems.value = listOf()
        _nowPlayingItems.value = listOf()
        fetchAll()
    }

    fun onMorePopularMoviesClick() {
        sendEvent(GoToPopularMoviesEvent)
    }

    fun onMoreNowPlayingMoviesClick() {
        sendEvent(GoToNowPlayingMoviesEvent)
    }

    fun onNowPlayingMoviesRefreshClick() {
        _nowPlayingItems.value = listOf()
        fetchNowPlayingMovies()
    }

    fun onPopularMoviesRefreshClick() {
        _popularItems.value = listOf()
        fetchPopularMovies()
    }

    private fun fetchAll() {
        fetchPopularMovies()
        fetchNowPlayingMovies()
    }

    private fun fetchPopularMovies() {
        getPopularMovieUseCase(1).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _popularItems.value = listOf(LoadingEntity)
                }

                is Resource.Success -> {
                    _popularItems.value = result.model.movieEntities
                }

                is Resource.Fail -> {
                    _popularItems.value = listOf(ErrorEntity(result.exception))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchNowPlayingMovies() {
        getNowPlayingUseCase(1).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _nowPlayingItems.value = listOf(LoadingEntity)
                }

                is Resource.Success -> {
                    _nowPlayingItems.value = result.model.movieEntities
                }

                is Resource.Fail -> {
                    _nowPlayingItems.value = listOf(ErrorEntity(result.exception))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onGoToDetail(movieEntity: MovieEntity) {
        _event.value = GoToDetailEvent(
            movieEntity.id,
            movieEntity.title
        )
    }

    object GoToPopularMoviesEvent : Event()
    object GoToNowPlayingMoviesEvent : Event()
    class GoToDetailEvent(val movieId: Int, val title: String) : Event()

}