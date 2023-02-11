package net.deali.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.coredomain.Resource
import net.deali.detail.domain.usecase.GetDetailUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val movieId =
        savedStateHandle.get<Int>(KEY_MOVIE_ID) ?: throw IllegalStateException("must not be null")

    val movieTitle = savedStateHandle.get<String>(KEY_MOVIE_TITLE)
        ?: throw IllegalStateException("must not be null")

    var isLoading: Boolean = false

    fun onRefresh() {
        if (isLoading) return
        fetch()
    }

    private fun fetch() {
        if (isLoading) return

        getDetailUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    isLoading = true
                }
                is Resource.Success -> {
                    isLoading = false
                }
                is Resource.Fail -> {
                    isLoading = false
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        const val KEY_MOVIE_ID = "KEY_MOVIE_ID"
        const val KEY_MOVIE_TITLE = "KEY_MOVIE_TITLE"
    }
}