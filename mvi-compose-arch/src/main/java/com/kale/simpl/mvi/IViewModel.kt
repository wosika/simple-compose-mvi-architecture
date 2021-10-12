package com.kale.simpl.mvi

interface IViewModel<A:IAction,VS:IViewState>{


    //处理传过来的意图
    fun processAction(action:A)



    //返回一个ViewState
}