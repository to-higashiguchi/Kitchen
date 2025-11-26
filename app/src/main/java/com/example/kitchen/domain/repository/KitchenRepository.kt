package com.example.kitchen.domain.repository

import com.example.kitchen.domain.model.UserStats

interface KitchenRepository {
    suspend fun getUserStats(): UserStats

    suspend fun updateStats(newStats: UserStats)
}
