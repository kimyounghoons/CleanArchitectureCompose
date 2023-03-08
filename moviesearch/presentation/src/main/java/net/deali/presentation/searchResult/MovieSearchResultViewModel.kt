package net.deali.presentation.searchResult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.core.LazyColumnViewModel
import net.deali.coredomain.Resource
import net.deali.coredomain.entity.BottomErrorEntity
import net.deali.coredomain.entity.EmptyEntity
import net.deali.coredomain.entity.ErrorEntity
import net.deali.coredomain.entity.LoadingEntity
import net.deali.coredomain.entity.MovieEntity
import net.deali.domain.usecase.GetMovieSearchUseCase
import net.deali.navigator.NavigatorKey
import javax.inject.Inject

@HiltViewModel
class MovieSearchResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getMovieSearchUseCase: GetMovieSearchUseCase
) : LazyColumnViewModel() {
    private val searchText: String =
        savedStateHandle[NavigatorKey.SearchResultMovies.KEY_KEYWORD] ?: ""

    override fun onRefresh() {
        onSearch()
    }

    override fun onLoadMore() {
        if (hasLoadingEntity() || isAllLoaded || searchText.isEmpty()) return
        getMovieSearchUseCase(
            query = searchText,
            page = pageCount
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _items.value = getPureItems() + LoadingEntity
                }

                is Resource.Success -> {
                    if (pageCount == 1 && result.model.movieEntities.isEmpty()) {
                        _items.value = listOf(EmptyEntity())
                    } else {
                        pageCount++
                        _items.value = getPureItems() + result.model.movieEntities
                    }

                    if (pageCount >= result.model.totalPageCount) {
                        isAllLoaded = true
                    }
                }

                is Resource.Fail -> {
                    if (pageCount == 1) {
                        _items.value = listOf(ErrorEntity(result.exception))
                    } else {
                        _items.value = getPureItems() + BottomErrorEntity
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onSearch() {
        if (hasLoadingEntity()) return
        _items.value = listOf()
        pageCount = 1
        isAllLoaded = false
        onLoadMore()
    }

    fun onGoToDetail(movieEntity: MovieEntity) {
        _event.value = GoToDetailEvent(
            movieEntity.id,
            movieEntity.title
        )
    }

    class GoToDetailEvent(val movieId: Int, val title: String) : Event()
}