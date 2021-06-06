package com.business.mykotlinapp.ViewModels

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.liveData
import com.business.mykotlinapp.NetworkClient.Repository
import com.business.mykotlinapp.model.ListItems
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DataViewModel(var repository: Repository): ViewModel() {



     var listData: List<ListItems>?=null



    fun getData() = liveData<List<ListItems>?>(Dispatchers.IO){
              listData=ArrayList()
             emit(listData)

        try {

            emit( repository.getListData())

        }catch (e:Exception){

            emit(null)

        }
    }

    fun postData(body:HashMap<String,String>):LiveData<Int>?{
        var postStatus:LiveData<Int>?=null


        postStatus=repository.postdata(body)
        return postStatus

        }
    }










