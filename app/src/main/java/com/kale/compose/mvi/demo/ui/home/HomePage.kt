package com.kale.compose.mvi.demo.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.kale.compose.mvi.demo.model.HomeItemModel
import com.kale.compose.mvi.demo.ui.home.HomeIntent
import com.kale.compose.mvi.demo.ui.home.HomeViewModel


@Composable
fun HomePage(vm: HomeViewModel = viewModel()) {
    val viewState by vm.vsFlow.collectAsState()


    SwipeRefresh(state = SwipeRefreshState(viewState.isLoading), onRefresh = {
        vm.processIntent(HomeIntent.LoadDataIntent)
    }) {
        if (viewState.isError) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                TextButton(onClick = {
                    vm.processIntent(HomeIntent.LoadDataIntent)
                }) {
                    Text(text = "网络错误请点击重试")
                }
            }
        } else {
            if (viewState.data != null) {
                LazyColumn(Modifier.fillMaxSize(), content = {
                    if (viewState.data != null) {
                        items(items = viewState.data!!.datas) {
                            NewsItem(it)
                        }
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