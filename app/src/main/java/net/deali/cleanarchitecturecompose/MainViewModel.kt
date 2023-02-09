package net.deali.cleanarchitecturecompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.coredomain.Resource
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.ErrorEntity
import net.deali.domain.usecase.GetPopularMovieUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel() {
    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event

    private val _popularItems = MutableLiveData<List<BaseEntity>>()
    val popularItems: LiveData<List<BaseEntity>> = _popularItems

    fun onRefresh() {
        _popularItems.value = listOf()
        fetchAll()
    }

    fun onMorePopularMoviesClick() {
        _event.value = Event.GoToPopularMoviesEvent
    }

    fun onMoreNowPlayingMoviesClick() {
        _event.value = Event.GoToNowPlayingMoviesEvent
    }

    private fun fetchAll() {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        getPopularMovieUseCase(1).onEach { result ->
            when (result) {
                is Resource.Loading -> {

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

    sealed class Event {
        object GoToPopularMoviesEvent : Event()
        object GoToNowPlayingMoviesEvent : Event()
    }

    companion object {
        const val POPULAR_MOVIE = "인기영화"
    }
}