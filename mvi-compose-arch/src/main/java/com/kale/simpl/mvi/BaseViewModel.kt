package com.kale.simpl.mvi

import androidx.lifecycle.ViewModel


//需要维护事件转换成为viewstate回调，viewmodel对接的是一个个的compose布局，compose为单activity的页面。
abstract class BaseViewModel<A:IAction,VS:IViewState>:IViewModel<A,VS> , ViewModel()