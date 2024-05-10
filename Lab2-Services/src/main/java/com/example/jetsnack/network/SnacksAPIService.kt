package com.example.jetsnack.network

import com.example.jetsnack.model.Snack
import retrofit2.http.GET

interface SnacksAPIService {
    @GET("snacks")
    suspend fun getSnacks(): List<Snack>
}