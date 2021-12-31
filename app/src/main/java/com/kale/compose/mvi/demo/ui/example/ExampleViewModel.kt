package com.kale.compose.mvi.demo.ui.example


import com.kale.compose.mvi.demo.model.HomeItemModel
import com.kale.compose.mvi.demo.model.Repository
import com.kale.simple.mvi.Action
import com.kale.simple.mvi.BaseViewModel
import com.kale.simple.mvi.Event
import com.kale.simple.mvi.ViewState


class ExampleViewModel : BaseViewModel<ExampleAction, ExampleViewState, ExampleEvent>() {

    override fun initState(): ExampleViewState = ExampleViewState(true)

    override suspend fun handlerAction(action: ExampleAction) {
        when (action) {
            ExampleAction.Load -> {
                updateViewState(state.value.copy(isLoading = true))

                kotlin.runCatching {
                    val homePageModel =
                        Repository.RemoteRepository.wanAndroid.getHomePageModel(0)
                    homePageModel.data
                }.onSuccess {
                    updateViewState(
                        state.value.copy(
                            isLoading = false,
                            data = it.datas
                        )
                    )
                    shotEvent(ExampleEvent.ShowToast("加载成功"))
                }.onFailure {
                    shotEvent(ExampleEvent.ShowToast(it.message ?: "加载错误。。。"))
                }
            }
        }
    }
}


sealed class ExampleAction : Action {
    object Load : ExampleAction()
}


sealed class ExampleEvent : Event {
    data class ShowToast(val msg: String) : ExampleEvent()
}

data class ExampleViewState(
    val isLoading: Boolean = false,
    val data: List<HomeItemModel> = emptyList()
) : ViewState