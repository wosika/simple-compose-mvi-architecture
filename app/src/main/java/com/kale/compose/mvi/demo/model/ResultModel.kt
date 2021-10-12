package com.kale.compose.mvi.demo.model

data class ResultModel<T>(val data: T, val errorCode: Int, val errorMsg: String){
    override fun toString(): String {
        return "ResultModel(data=$data, errorCode=$errorCode, errorMsg='$errorMsg')"
    }
}
