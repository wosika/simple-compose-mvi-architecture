package com.kale.compose.mvi.demo.ui.paging

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kale.compose.mvi.demo.model.HomeItemModel

@Composable
fun HomePage(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Paging-compose") }
            )
        },
        content = {
            Content()
        }
    )
}


@Composable
private  fun Content(vm: PagingViewModel = viewModel()) {
    val lazyItems = vm.pagingViewState.collectAsLazyPagingItems()
    val isRefreshState = rememberSwipeRefreshState(isRefreshing = false)


    SwipeRefresh(state = isRefreshState, onRefresh = {
        lazyItems.refresh()
    }) {
        isRefreshState.isRefreshing = lazyItems.loadState.refresh is LoadState.Loading
        LazyColumn( content = {
            items(lazyItems) {
                NewsItem(it!!)
            }

            lazyItems.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = lazyItems.loadState.refresh as LoadState.Error
                        if (lazyItems.itemCount<=0){
                            item {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    TextButton(modifier = Modifier.fillMaxSize(),onClick = {
                                        lazyItems.retry()
                                    }) {
                                        Text(text = "网络错误请点击重试")
                                    }
                                }
                            }
                        }else{
                            item {
                                TextButton(modifier = Modifier.fillMaxSize(),onClick = {
                                    lazyItems.retry()
                                }) {
                                    Text(text = "网络错误请点击重试")
                                }
                            }
                        }

                    }
                    loadState.append is LoadState.Error -> {
                        val e = lazyItems.loadState.append as LoadState.Error
                        item {
                            TextButton(modifier = Modifier.fillMaxSize(),onClick = {
                                lazyItems.retry()
                            }) {
                                Text(text = "网络错误请点击重试")
                            }

                        }
                    }
                }
            }
        })
    }
}

@Composable
fun LoadingItem() {
    Text(text = "加载更多")
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