package com.kale.compose.mvi.demo.model

data class HomePageModel(
    val curPage: Int,
    val datas: List<HomeItemModel>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
){
    override fun toString(): String {
        return "HomePageModel(curPage=$curPage, datas=$datas, offset=$offset, over=$over, pageCount=$pageCount, size=$size, total=$total)"
    }
}