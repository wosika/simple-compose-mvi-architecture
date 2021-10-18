package com.kale.compose.mvi.demo.ui.home


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig

import androidx.paging.PagingData
import com.kale.compose.mvi.demo.model.HomeItemModel
import com.kale.compose.mvi.demo.model.HomeItemSource
import com.kale.compose.mvi.demo.model.Repository

import com.kale.simpl.mvi.Event
import com.kale.simpl.mvi.Processor
import com.kale.simpl.mvi.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel(), Processor<HomeEvent, HomeViewState> {

    private val _state: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState(true))

    override val state: StateFlow<HomeViewState> = _state

    override fun sendEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                HomeEvent.LoadDataEvent -> {
                    //开启加载
                    loadData()
                }
            }
        }
    }

    //默认是刷新状态
    private suspend fun loadData(isRefresh: Boolean = true) {
        Log.d(javaClass.simpleName, "去加载网络数据")
        //讲ui状态置为刷新状态
        _state.value = _state.value.copy(isLoading = true)

        _state.value = _state.value.copy(isLoading = false, data = Pager(PagingConfig(20)) {
            HomeItemSource()
        }.flow, isError = false)


    }

}

sealed class HomeEvent : Event {
    object LoadDataEvent : HomeEvent()
}

data class HomeViewState(
    val isLoading: Boolean = false,
    val data: Flow<PagingData<HomeItemModel>>? = null,
    val isError: Boolean = false,
) : ViewState