package com.business.mykotlinapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.business.mykotlinapp.NetworkClient.ApiClient
import com.business.mykotlinapp.NetworkClient.Repository
import org.jetbrains.annotations.NotNull

 class ViewModelsFactory(@NotNull var repository:Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DataViewModel::class.java)){

        return DataViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }

}