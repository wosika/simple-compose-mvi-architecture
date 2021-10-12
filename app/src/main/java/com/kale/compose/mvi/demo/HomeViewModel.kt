package com.kale.compose.mvi.demo

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kale.compose.mvi.demo.model.HomePageModel
import com.kale.compose.mvi.demo.ui.theme.Repository
import com.kale.simpl.mvi.BaseViewModel
import com.kale.simpl.mvi.IIntent
import com.kale.simpl.mvi.IViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeIntent, HomeViewState>() {
    //初始状态
    override val _vsFlow: MutableStateFlow<HomeViewState> by lazy{
        MutableStateFlow(HomeViewState(isLoading = true)).also {
            viewModelScope.launch {
                loadData()
            }
        }
    }


    override fun processIntent(intent: HomeIntent) {
        Log.d(javaClass.simpleName, "处理意图")
        viewModelScope.launch {
            when (intent) {
                HomeIntent.LoadDataIntent -> {
                    loadData()
                }
            }
        }
    }

    private suspend fun loadData() {
        Log.d(javaClass.simpleName, "去加载网络数据")
        _vsFlow.value = HomeViewState(true)
        val result = Repository.RemoteRepository.wanAndroid.getHomePageModel(0)
        Log.d(javaClass.simpleName,result.toString())
        _vsFlow.value = HomeViewState(false, data = result.data)

    }
}


sealed class HomeIntent : IIntent {
    object LoadDataIntent : HomeIntent()
}

data class HomeViewState(
    val isLoading: Boolean = false,
    val data: HomePageModel? = null,
    val isError: Boolean = false,
) : IViewState