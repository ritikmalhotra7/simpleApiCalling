package com.complete.simpleapicalling

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class TodoViewModel(val repo : TodoRepo): ViewModel() {
    val myResponse :MutableLiveData<Response<List<TodoModel>>> = MutableLiveData()
    fun getTodo(){
        viewModelScope.launch {
            val response = repo.getTodo()
            myResponse.value = response
        }
    }
}