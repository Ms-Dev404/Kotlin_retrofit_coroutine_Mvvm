package com.business.mykotlinapp.NetworkClient


import com.business.mykotlinapp.model.ListItems
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ApiClient {


    @GET(UrlRepo.GET_LIST)

      suspend fun getdata():ArrayList<ListItems>

    @POST(UrlRepo.GET_LIST)

       fun postData(@Body body: HashMap<String, String> ):Call<Void>



}