package com.kale.simpl.mvi

interface IViewModel<I:IEvent,VS:IViewState>{
    //处理传过来的意图
    fun processIntent(intent:I)
}