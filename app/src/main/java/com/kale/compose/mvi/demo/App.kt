package com.kale.compose.mvi.demo

import android.app.Application
import com.kale.compose.mvi.demo.ui.theme.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }


    companion object {

        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Repository.RemoteRepository.WANANDROID_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


    }


}