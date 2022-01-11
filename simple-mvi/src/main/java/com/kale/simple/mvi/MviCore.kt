package com.kale.simple.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


/*
 * UI状态
 */
interface ViewState

/*
 * view层发起的动作
 */
interface Action


interface Event

/*
 * 处理器
 */
interface Processor<in AT : Action, VS : ViewState, EV : Event> {
    val state: StateFlow<VS>
    val uiEvent: Flow<EV>
    fun sendAction(action: AT)
    fun updateViewState(viewState: VS) {}
    fun sendUiEvent(event: EV)
    suspend fun listenUiEvent(action: (event: EV) -> Unit)

}