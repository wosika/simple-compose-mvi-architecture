package com.kale.compose.mvi.demo.ui.home


import androidx.lifecycle.ViewModel

import androidx.paging.PagingData
import com.kale.compose.mvi.demo.model.HomeItemModel

import com.kale.simpl.mvi.Event
import com.kale.simpl.mvi.Processor
import com.kale.simpl.mvi.ViewState
import kotlinx.coroutines.flow.Flow

typealias HomeProcessor = Processor<HomeEvent,HomeViewState>


class HomeViewModel : ViewModel() {

}


sealed class HomeEvent : Event {
    object LoadDataEvent : HomeEvent()
}

data class HomeViewState(
    val isLoading: Boolean = false,
    val data: Flow<PagingData<HomeItemModel>>? = null,
    val isError: Boolean = false,
) : ViewState