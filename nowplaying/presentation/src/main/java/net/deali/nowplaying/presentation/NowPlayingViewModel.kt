package net.deali.nowplaying.presentation

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
import net.deali.nowplaying.domain.usecase.GetNowPlayingUseCase
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val getNowPlayingUseCase: GetNowPlayingUseCase
) : LazyColumnViewModel() {

    override fun onLoadMore() {
        if (hasLoadingEntity() || isAllLoaded) return

        getNowPlayingUseCase(pageCount).onEach { result ->
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

    fun onGoToDetail(movieEntity: MovieEntity) {
        _event.value = GoToDetailEvent(
            movieEntity.id,
            movieEntity.title
        )
    }

    class GoToDetailEvent(val movieId: Int, val title: String) : Event()
}