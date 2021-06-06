package com.business.mykotlinapp

import android.app.Application
import com.business.mykotlinapp.NetworkClient.ApiClient
import com.business.mykotlinapp.NetworkClient.Repository
import com.business.mykotlinapp.NetworkClient.RetrofitClient


class MyApplication:Application() {
     lateinit var apiClient: ApiClient
        companion object{

            lateinit var repository: Repository

        }

        override fun onCreate() {
                super.onCreate()


                 apiClient = RetrofitClient().getRetroClient().create(ApiClient::class.java)
                 repository=Repository(apiClient)


        }

}