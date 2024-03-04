package com.example.rickapi.data

import com.example.rickapi.model.Character
import com.example.rickapi.model.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    fun getAllCartoons() : Response<Character>

    @GET("character/{id}")
    suspend fun getAllCharacters(@Path("id") id: Int) : Response<Result>
}