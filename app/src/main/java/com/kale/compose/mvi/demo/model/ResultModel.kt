package com.kale.compose.mvi.demo.model

data class ResultModel<T>(val data: T, val errorCode: Int, val errorMsg: String)
