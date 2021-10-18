package com.kale.simpl.mvi


import kotlinx.coroutines.flow.StateFlow


interface Processor<in EV : Any, out VS> {
    val state: StateFlow<VS>
    fun sendEvent(event: EV)
}

