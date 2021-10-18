package com.kale.compose.mvi.demo.ui.home


import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig

import com.kale.compose.mvi.demo.model.HomeItemsSource


class HomeViewModel : ViewModel() {

    val pagingViewState  =  Pager(PagingConfig(20)) { HomeItemsSource() }.flow

}

