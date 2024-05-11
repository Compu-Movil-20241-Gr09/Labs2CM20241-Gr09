package com.example.jetsnack.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val password: String,
    val favoriteSnacks: List<String>
)