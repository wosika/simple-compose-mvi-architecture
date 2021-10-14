package com.kale.compose.mvi.demo.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kale.compose.mvi.demo.model.HomeItemModel
import com.kale.compose.mvi.demo.model.HomeItemSource
import com.kale.simpl.mvi.BaseViewModel
import com.kale.simpl.mvi.IEvent
import com.kale.simpl.mvi.IViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeEvent, HomeViewState>() {
    //初始状态
    override val _vsFlow: MutableStateFlow<HomeViewState> by lazy {
        MutableStateFlow(HomeViewState(isLoading = true)).apply {
            viewModelScope.launch {
                loadData()
            }
        }
    }

    override fun processIntent(intent: HomeEvent) {
        Log.d(javaClass.simpleName, "处理意图")
        viewModelScope.launch {
            when (intent) {
                HomeEvent.LoadDataEvent -> {
                    loadData()
                }
            }
        }
    }

    //默认是刷新状态
    private suspend fun loadData(isRefresh: Boolean = true) {
        Log.d(javaClass.simpleName, "去加载网络数据")
        //讲ui状态置为刷新状态
        _vsFlow.value = _vsFlow.value.copy(isLoading = true)

        _vsFlow.value = vsFlow.value.copy(isLoading = false, data = Pager(PagingConfig(20)) {
            HomeItemSource()
        }.flow, isError = false)


/*
        kotlin.runCatching {

        }.onSuccess { result ->

        }.onFailure {
            it.printStackTrace()
            _vsFlow.value = vsFlow.value.copy(isLoading = false, isError = true)
        }*/
    }
}


sealed class HomeEvent : IEvent {
    object LoadDataEvent : HomeEvent()
}

data class HomeViewState(
    val isLoading: Boolean = false,
    val data: Flow<PagingData<HomeItemModel>>? = null,
    val isError: Boolean = false,
) : IViewState