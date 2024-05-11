package com.example.jetsnack.network

import com.example.jetsnack.model.Snack
import com.example.jetsnack.model.User
import retrofit2.http.GET

interface SnacksAPIService {
    @GET("get-snacks")
    suspend fun getSnacks(): List<Snack>

    @GET("get-users")
    suspend fun getUsers(): List<User>
}