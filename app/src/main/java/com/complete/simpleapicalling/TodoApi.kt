package com.complete.simpleapicalling

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi  {
    @GET("/todos")
    suspend fun getAll(): Response<List<TodoModel>>
}