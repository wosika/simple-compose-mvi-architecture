package com.kale.simpl.mvi

interface IViewModel<I:IIntent,VS:IViewState>{


    //处理传过来的意图
    fun processIntent(intent:I)



    //返回一个ViewState
}