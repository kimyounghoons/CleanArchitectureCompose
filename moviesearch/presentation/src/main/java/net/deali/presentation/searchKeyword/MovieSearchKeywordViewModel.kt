package net.deali.presentation.searchKeyword

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
import net.deali.domain.usecase.GetMovieSearchKeywordUseCase
import javax.inject.Inject

@HiltViewModel
class MovieSearchKeywordViewModel @Inject constructor(
    val getMovieSearchUseCase: GetMovieSearchKeywordUseCase
) : LazyColumnViewModel() {
    var searchText: String = ""

    override fun onRefresh() {
        onSearch(searchText)
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
                    if (pageCount == 1 && result.model.searchKeywordEntities.isEmpty()) {
                        _items.value = listOf(EmptyEntity())
                    } else {
                        if (pageCount == 1) {
                            _items.value = result.model.searchKeywordEntities
                        } else {
                            _items.value = getPureItems() + result.model.searchKeywordEntities
                        }
                        pageCount++
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

    fun onSearch(searchText: String) {
        if (hasLoadingEntity() || searchText.isEmpty()) return
        pageCount = 1
        isAllLoaded = false
        this.searchText = searchText
        onLoadMore()
    }

    fun onSearchClear() {
        this.searchText = ""
    }

    fun onGoToSearchResult(keyword: String) {
        _event.value = GoToSearchResultEvent(keyword)
    }

    class GoToSearchResultEvent(val keyword: String) : Event()

}