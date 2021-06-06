package com.business.mykotlinapp.NetworkClient

import com.business.mykotlinapp.ui.gallery.GalleryViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient() {

    var httpLoggingInterceptor:HttpLoggingInterceptor?=null
    var okHttpClient:OkHttpClient?=null
    var retrofit:Retrofit?=null

    fun getRetroClient():Retrofit{

        if(retrofit==null){

            httpLoggingInterceptor= HttpLoggingInterceptor()
            httpLoggingInterceptor!!.level=HttpLoggingInterceptor.Level.BODY

            okHttpClient=OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build()

            retrofit=Retrofit
                .Builder()
                .baseUrl(UrlRepo.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()


        }

     return retrofit as Retrofit
    }




}