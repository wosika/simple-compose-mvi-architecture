package com.kale.simple.mvi


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<AT : Action, VS : ViewState, EV : Event> : Processor<AT, VS, EV>,
    ViewModel() {


    private val _state: MutableStateFlow<VS> = MutableStateFlow(initState())

    override val state: StateFlow<VS> = _state.asStateFlow()

    private val _uiEvents = Channel<EV>(Channel.BUFFERED)

    override val uiEvent: Flow<EV> = _uiEvents.receiveAsFlow()


    override fun sendAction(action: AT) {
        viewModelScope.launch(Dispatchers.IO) {
            handlerAction(action)
        }
    }

    override fun updateViewState(viewState: VS) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _state.value = viewState
        }
    }


    protected abstract fun initState(): VS

    protected abstract suspend fun handlerAction(action: AT)


    override fun sendUiEvent(event: EV) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEvents.send(event)
        }
    }

    override suspend fun listenUiEvent(action: (event: EV) -> Unit) {
        uiEvent.collect(action)
    }
}

