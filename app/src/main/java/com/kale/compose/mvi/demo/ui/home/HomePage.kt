package com.kale.compose.mvi.demo.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.kale.compose.mvi.demo.ui.home.HomeIntent
import com.kale.compose.mvi.demo.ui.home.HomeViewModel




@Composable
fun HomePage(vm: HomeViewModel = viewModel()) {
    val viewState by vm.vsFlow.collectAsState()

    SwipeRefresh(state = SwipeRefreshState(viewState.isLoading), onRefresh = {
        vm.processIntent(HomeIntent.LoadDataIntent)
    }) {
        LazyColumn(Modifier.fillMaxSize(), content = {

        })
    }
}