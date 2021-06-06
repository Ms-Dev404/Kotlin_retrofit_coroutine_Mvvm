package com.business.mykotlinapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListItems {

    @SerializedName("title")
    @Expose
   lateinit var title:String

    @SerializedName("description")
    @Expose
    lateinit var description:String


}