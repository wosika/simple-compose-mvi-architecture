package com.kale.compose.mvi.demo.model

data class HomePageModel(
    val curPage: Int,
    val datas: List<HomeItemModel>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)