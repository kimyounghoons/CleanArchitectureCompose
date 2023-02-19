package net.deali.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.core.BaseViewModel
import net.deali.coredomain.Resource
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.ErrorEntity
import net.deali.detail.domain.usecase.GetDetailUseCase
import net.deali.navigator.NavigatorKey
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val movieId =
        savedStateHandle.get<Int>(NavigatorKey.MovieDetail.KEY_MOVIE_ID)
            ?: throw IllegalStateException("must not be null")

    val movieTitle = savedStateHandle.get<String>(NavigatorKey.MovieDetail.KEY_MOVIE_TITLE)
        ?: throw IllegalStateException("must not be null")

    var isLoading: Boolean = false

    private val _item = MutableLiveData<BaseEntity>()
    val item: LiveData<BaseEntity> = _item

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
                    _item.value = result.model
                }
                is Resource.Fail -> {
                    isLoading = false
                    _item.value = ErrorEntity(result.exception)
                }
            }
        }.launchIn(viewModelScope)
    }
}