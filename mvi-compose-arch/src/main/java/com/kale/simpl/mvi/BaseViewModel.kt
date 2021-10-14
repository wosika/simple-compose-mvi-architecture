package com.kale.simpl.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


//需要维护事件转换成为ViewState回调，ViewModel对接的是一个个的compose布局，compose为单activity的页面。
abstract class BaseViewModel<EV : IEvent, VS : IViewState> : IViewModel<EV, VS>, ViewModel() {
    protected abstract val _vsFlow: MutableStateFlow<VS>
    //向外部提供不可变的stateFlow
    val vsFlow by lazy { _vsFlow.asStateFlow() }

}