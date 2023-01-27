package net.deali.cleanarchitecturecompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val list = listOf(POPULAR_MOVIE, MOVIE_SEARCH)
    val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event
    fun onClick(title: String) {
        when (title) {
            POPULAR_MOVIE -> {
                _event.value = Event.GoToPopularMoviesEvent
            }
            MOVIE_SEARCH -> {
                _event.value = Event.GoToMovieSearchEvent
            }
        }
    }

    sealed class Event {
        object GoToPopularMoviesEvent : Event()
        object GoToMovieSearchEvent : Event()
    }

    companion object {
        const val POPULAR_MOVIE = "인기영화"
        const val MOVIE_SEARCH = "영화 검색"
    }
}