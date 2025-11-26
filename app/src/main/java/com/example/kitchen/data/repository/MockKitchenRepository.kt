package com.example.kitchen.data.repository

import com.example.kitchen.domain.model.UserStats
import com.example.kitchen.domain.repository.KitchenRepository
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

/** Mock implementation of KitchenRepository for testing purposes */
class MockKitchenRepository : KitchenRepository {
    override suspend fun getUserStats(): UserStats {
        delay(1.seconds)

        return UserStats(
            streak = 1,
            totalSavings = 5
        )
    }
}
