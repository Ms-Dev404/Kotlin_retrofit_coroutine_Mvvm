package com.business.mykotlinapp.AppUtilities

import android.content.Context
import android.content.SharedPreferences

class SharedPref(var context: Context) {
    private val sharedPreferences:SharedPreferences
    companion object{

        const val PREF_NAME="preference"
        const val CONFIG="key_config"
        const val TAG_URL="key_url"
    }

    init {

       sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    }

    fun saveString( key:String,value:String):Boolean{

        var editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)

        return editor.commit()
    }

    fun saveBoolean(key: String,value: Boolean):Boolean{

        var editor:SharedPreferences.Editor = sharedPreferences.edit()
      editor.putBoolean(key, value)
       return editor.commit()
    }

    fun isConfig():Boolean=sharedPreferences.getBoolean(CONFIG,false)

    fun getUrl():String?=sharedPreferences.getString(TAG_URL,"null")
}