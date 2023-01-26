package net.deali.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.deali.domain.model.Resource
import net.deali.domain.usecase.GetLastestMovieUseCase
import javax.inject.Inject

@HiltViewModel
class FeatureAViewModel @Inject constructor(
    private val getLastestMovieUseCase: GetLastestMovieUseCase
) : ViewModel() {
    fun getLastest() {
        getLastestMovieUseCase().onEach { result ->
            Log.e("kyh!!!", "getLastest result : $result")
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                }
                is Resource.Fail -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}