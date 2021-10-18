package com.kale.compose.mvi.demo.ui.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kale.compose.mvi.demo.model.HomeItemModel
import com.kale.compose.mvi.demo.model.Repository
import com.kale.simpl.mvi.Event
import com.kale.simpl.mvi.Processor
import com.kale.simpl.mvi.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExampleViewModel : ViewModel(), Processor<ExampleEvent, ExampleViewState> {

    private val _state: MutableStateFlow<ExampleViewState> by lazy {
        MutableStateFlow(
            ExampleViewState(false)
        )
    }

    override val state: StateFlow<ExampleViewState> by lazy { _state }


    override fun sendEvent(event: ExampleEvent) {
        viewModelScope.launch {
            when (event) {
                ExampleEvent.Load -> {
                    _state.value = _state.value.copy(isLoading = true)
                    kotlin.runCatching {
                        val homePageModel =
                            Repository.RemoteRepository.wanAndroid.getHomePageModel(0)
                        homePageModel.data
                    }.onSuccess {
                        _state.value =
                            _state.value.copy(isLoading = false, data = emptyList(), error = null)
                    }.onFailure {
                        _state.value = _state.value.copy(isLoading = false, error = it)
                    }
                }
            }
        }
    }
}


sealed class ExampleEvent : Event {
    object Load : ExampleEvent()
}

data class ExampleViewState(
    val isLoading: Boolean = false,
    val data: List<HomeItemModel> = emptyList(),
    val error: Throwable? = null
) : ViewState