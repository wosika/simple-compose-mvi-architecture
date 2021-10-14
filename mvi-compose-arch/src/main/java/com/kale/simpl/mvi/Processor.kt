package com.kale.simpl.mvi


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow


interface Processor<in EV : Any, out VS> {
    val state: StateFlow<VS>
    fun sendEvent(event: EV)
}



fun <EV : Any, EF : Any> ViewModel.processor(
    prepare: suspend EffectsCollector<EF>.() -> Unit = {},
    onEvent: suspend EffectsCollector<EF>.(EV) -> Unit = {},
): Processor<EV, Nothing, EF> = viewModelScope.processor(
    prepare = prepare,
    onEvent = onEvent
)