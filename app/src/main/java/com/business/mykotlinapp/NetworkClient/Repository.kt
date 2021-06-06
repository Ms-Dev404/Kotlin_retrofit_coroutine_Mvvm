package com.business.mykotlinapp.NetworkClient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


import com.business.mykotlinapp.model.ListItems
import com.google.gson.Gson
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(var apiClient: ApiClient){





     suspend fun getListData()=apiClient.getdata()



 fun postdata(Body:HashMap<String,String>): LiveData<Int> {
        var status:LiveData<Int>;
        status=MutableLiveData()
        status.value=1
          try {


               apiClient.postData(Body).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.code() == 200) {
                             status?.value=response.code()
                             Log.e("resp", "200")
                        }

                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        status?.value=503
                        Log.e("repoer", t.localizedMessage)
                    }

                })
          } catch (e: Exception) {
              status.value=503
              Log.e("retro", e.localizedMessage)
          }

return status
}





}