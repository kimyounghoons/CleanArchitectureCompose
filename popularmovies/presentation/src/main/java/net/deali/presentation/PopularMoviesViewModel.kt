package net.deali.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.domain.usecase.GetPopularMovieUseCase
import net.deali.nativecore.Resource
import net.deali.nativecore.model.BaseModel
import net.deali.nativecore.model.EmptyModel
import net.deali.nativecore.model.ErrorModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel() {
    private val _items = MutableLiveData<List<BaseModel>>(listOf())
    val items: LiveData<List<BaseModel>> = _items

    var pageCount: Int = 1
    var isLoading: Boolean = false
    var isAllLoaded: Boolean = false
    fun onRefresh() {
        if (isLoading) return
        _items.value = listOf()
        pageCount = 1
        isAllLoaded = false
        onLoadMore()
    }

    fun onLoadMore() {
        if (isLoading || isAllLoaded) return

        getPopularMovieUseCase(pageCount).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    isLoading = true
                }
                is Resource.Success -> {
                    if (pageCount == 1 && result.model.movies.isEmpty()) {
                        _items.value = listOf(EmptyModel())
                    } else {
                        pageCount++
                        _items.value = items.value!! + result.model.movies
                    }
                    isLoading = false
                    if (pageCount >= result.model.totalPageCount) {
                        isAllLoaded = true
                    }
                }
                is Resource.Fail -> {
                    _items.value = listOf(ErrorModel(result.exception))
                    isLoading = false
                }
            }
        }.launchIn(viewModelScope)
    }
}