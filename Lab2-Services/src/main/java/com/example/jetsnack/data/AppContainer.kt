package com.example.jetsnack.data

import com.example.jetsnack.network.SnacksAPIService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val snackRepository : SnackRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl: String = "https://api.github.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: SnacksAPIService by lazy {
        retrofit.create(SnacksAPIService::class.java)
    }

    override val snackRepository: SnackRepository by lazy {
        NetworkSnackRepository(retrofitService)
    }
}
