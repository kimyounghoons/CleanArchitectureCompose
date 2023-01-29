package net.deali.cleanarchitecturecompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.domain.model.PopularMovieEntity
import net.deali.domain.usecase.GetMovieSearchUseCase
import net.deali.domain.usecase.GetPopularMovieUseCase
import net.deali.nativecore.ApiError
import net.deali.nativecore.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieSearchUseCase: GetMovieSearchUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel() {
    val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event

    private val _popularItems = MutableLiveData<PopularMovieEntity>()
    val popularItems: LiveData<PopularMovieEntity> = _popularItems

    private val _popularApiError = MutableLiveData<ApiError>()
    val apiError: LiveData<ApiError> = _popularApiError
    fun onMorePopularMoviesClick() {
        _event.value = Event.GoToPopularMoviesEvent
    }

    fun onMoreNowPlayingMoviesClick(){
        _event.value = Event.GoToNowPlayingMoviesEvent
    }

    fun fetchPopularMovies() {
        getPopularMovieUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    _popularItems.value = result.model
                }
                is Resource.Fail -> {
                    _popularApiError.value = result.apiError
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class Event {
        object GoToPopularMoviesEvent : Event()
        object GoToNowPlayingMoviesEvent: Event()
    }

    companion object {
        const val POPULAR_MOVIE = "인기영화"
    }
}