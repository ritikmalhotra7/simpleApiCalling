package com.complete.simpleapicalling

import retrofit2.Response

class TodoRepo {
    suspend fun getTodo():Response<List<TodoModel>>{
        return RetrofitInstance.api.getAll()
    }
}