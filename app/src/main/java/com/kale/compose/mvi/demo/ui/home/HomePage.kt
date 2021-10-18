package com.kale.compose.mvi.demo.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.kale.compose.mvi.demo.model.HomeItemModel


@Composable
fun HomePage(vm: HomeViewModel = viewModel()) {

    val viewState by vm.state.collectAsState()

    SwipeRefresh(state = SwipeRefreshState(viewState.isLoading), onRefresh = {
        vm.sendEvent(HomeEvent.LoadDataEvent)
    }) {
        if (viewState.isError) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                TextButton(onClick = {
                    vm.sendEvent(HomeEvent.LoadDataEvent)
                }) {
                    Text(text = "网络错误请点击重试")
                }
            }
        } else {
            if (viewState.data != null) {
                val lazyItems = viewState.data!!.collectAsLazyPagingItems()
                LazyColumn(Modifier.fillMaxSize(), content = {
                    items(lazyItems) {
                        NewsItem(it!!)
                    }
                })
            }
        }
    }
}

@Composable
private fun NewsItem(it: HomeItemModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = it.title, modifier = Modifier.padding(10.dp))
            if (it.desc.isNotBlank()) {
                Text(
                    text = it.desc,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }


}