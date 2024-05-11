package com.example.jetsnack.data

import com.example.jetsnack.model.Snack
import com.example.jetsnack.model.User
import com.example.jetsnack.network.SnacksAPIService

interface SnackRepository{
    suspend fun getSnacks(): List<Snack>
    suspend fun getUsers(): List<User>
}

class NetworkSnackRepository(
    private val snackApiService: SnacksAPIService
): SnackRepository {
    override suspend fun getSnacks(): List<Snack> = snackApiService.getSnacks()

    override suspend fun getUsers(): List<User> = snackApiService.getUsers()
}