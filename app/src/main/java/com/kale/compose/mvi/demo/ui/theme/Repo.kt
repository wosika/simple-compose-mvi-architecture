package com.kale.compose.mvi.demo.ui.theme

import retrofit2.http.GET
import retrofit2.http.Path

sealed class Repository {


    //远程数据仓库
    object RemoteRepository : Repository() {

        const val WANANDROID_BASE_URL = "https://www.wanandroid.com/"

        interface WanAndroid {
            @GET("article/list/{page}/json")
            fun homeList(@Path("page") page: Int)
        }


    }


}