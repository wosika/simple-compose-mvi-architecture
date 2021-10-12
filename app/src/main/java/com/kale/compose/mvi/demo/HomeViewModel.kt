package com.kale.compose.mvi.demo

import androidx.lifecycle.viewModelScope
import com.kale.simpl.mvi.BaseViewModel
import com.kale.simpl.mvi.IIntent
import com.kale.simpl.mvi.IViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeIntent, HomeViewState>() {
    override val _vsFlow: MutableStateFlow<HomeIntent> =
        MutableStateFlow(HomeIntent.InitIntent)

    override fun processIntent(intent: HomeIntent) {
        viewModelScope.launch {

        }
    }


}


sealed class HomeIntent : IIntent {
    object InitIntent : HomeIntent()

}

sealed class HomeViewState : IViewState