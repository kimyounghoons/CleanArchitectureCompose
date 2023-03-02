package net.deali.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.core.BaseViewModel
import net.deali.coredomain.Resource
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.EmptyEntity
import net.deali.coredomain.entity.ErrorEntity
import net.deali.coredomain.entity.LoadingEntity
import net.deali.coredomain.entity.MovieEntity
import net.deali.domain.usecase.GetPopularMovieUseCase
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : BaseViewModel() {
    private val _items = MutableLiveData<List<BaseEntity>>(listOf())
    val items: LiveData<List<BaseEntity>> = _items

    var pageCount: Int = 1
    var isAllLoaded: Boolean = false
    fun onRefresh() {
        if (hasLoadingEntity()) return
        _items.value = listOf()
        pageCount = 1
        isAllLoaded = false
        onLoadMore()
    }

    private fun hasLoadingEntity() = _items.value?.any {
        it is LoadingEntity
    } ?: false

    private fun getPureItems() = _items.value?.filterNot {
        it is LoadingEntity
    } ?: listOf()

    fun onLoadMore() {
        if (hasLoadingEntity() || isAllLoaded) return

        getPopularMovieUseCase(pageCount).onEach { result ->
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
                    _items.value = listOf(ErrorEntity(result.exception))
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

    class GoToDetailEvent(val movieId: Int, val title: String) : Event()
}