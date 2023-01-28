package net.deali.cleanarchitecturecompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val list = listOf(POPULAR_MOVIE)
    val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event
    fun onClick(title: String) {
        when (title) {
            POPULAR_MOVIE -> {
                _event.value = Event.GoToPopularMoviesEvent
            }
        }
    }

    sealed class Event {
        object GoToPopularMoviesEvent : Event()
    }

    companion object {
        const val POPULAR_MOVIE = "인기영화"
    }
}