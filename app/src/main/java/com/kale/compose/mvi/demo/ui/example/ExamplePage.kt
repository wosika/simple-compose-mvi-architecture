package com.kale.compose.mvi.demo.ui.example

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kale.compose.mvi.demo.App
import com.kale.compose.mvi.demo.model.HomeItemModel


@Composable
fun ExamplePage() {

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Mvi-Example-compose") }
        )
    }, content = { Content() })
}

@Composable
private fun Content(vm: ExampleViewModel = viewModel()) {
    val viewState = vm.state.collectAsState()

    val isRefreshState = rememberSwipeRefreshState(isRefreshing = false)


    val isInit = remember {
        mutableStateOf(true)
    }

    SwipeRefresh(state = isRefreshState, onRefresh = {
        vm.sendEvent(ExampleEvent.Load)
    }) {

        isRefreshState.isRefreshing = viewState.value.isLoading
        LazyColumn(content = {
            if (isInit.value) {
                vm.sendEvent(ExampleEvent.Load)
                isInit.value = false
            }
            viewState.value.apply {
                items(data) { item ->
                    NewsItem(it = item)
                }

                if (!isLoading) {
                    when {
                        error != null -> {
                            if (data.isNotEmpty()) {
                                Toast.makeText(App.app, "load error", Toast.LENGTH_SHORT).show()
                                item {
                                    Surface(modifier = Modifier.fillMaxSize()) {
                                        ErrorContent {
                                            vm.sendEvent(ExampleEvent.Load)
                                        }
                                    }
                                }
                            } else {
                                item {
                                    ErrorContent {
                                        vm.sendEvent(ExampleEvent.Load)
                                    }
                                }
                            }
                        }
                        data.isEmpty() -> {
                            item {

                                Surface(modifier = Modifier.fillMaxSize()) {
                                    EmptyContent {
                                        vm.sendEvent(ExampleEvent.Load)
                                    }
                                }

                            }
                        }
                    }
                }
            }
        })
    }
}

@Composable
fun ErrorContent(function: () -> Unit) {
    TextButton(onClick = function,Modifier.fillMaxSize()) {
        Text(text = "Load error,click to retry")
    }
}

@Composable
fun EmptyContent(function: () -> Unit) {
    TextButton(onClick = function,Modifier.fillMaxSize()) {
        Text(text = "Content is empty,click to retry")
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