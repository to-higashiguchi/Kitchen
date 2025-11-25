package com.example.kitchen.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserStats(
    val streak: Int,
    val totalSavings: Int
)
