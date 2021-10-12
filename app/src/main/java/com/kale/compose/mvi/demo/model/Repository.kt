package com.kale.compose.mvi.demo.model

import com.kale.compose.mvi.demo.App
import retrofit2.http.GET
import retrofit2.http.Path

sealed class Repository {


    //远程数据仓库
    object RemoteRepository : Repository() {

        const val WANANDROID_BASE_URL = "https://www.wanandroid.com/"

        interface WanAndroid {
            @GET("article/list/{page}/json")
            suspend fun getHomePageModel(@Path("page") page: Int): ResultModel<HomePageModel>
        }

        val wanAndroid by lazy { App.retrofit.create(WanAndroid::class.java) }

    }


}