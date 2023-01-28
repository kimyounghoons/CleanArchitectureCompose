package net.deali.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.domain.model.PopularMovies
import net.deali.domain.usecase.GetLastestMovieUseCase
import net.deali.nativecore.ApiError
import net.deali.nativecore.Resource
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getLastestMovieUseCase: GetLastestMovieUseCase
) : ViewModel() {
    private val _items = MutableLiveData<PopularMovies>()
    val items: LiveData<PopularMovies> = _items

    private val _apiError = MutableLiveData<ApiError>()
    val apiError: LiveData<ApiError> = _apiError

    fun fetch() {
        getLastestMovieUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    _items.value = result.model
                }
                is Resource.Fail -> {
                    _apiError.value = result.apiError
                }
            }
        }.launchIn(viewModelScope)
    }
}