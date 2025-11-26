package com.example.kitchen.data.repository

import com.example.kitchen.domain.model.UserStats
import com.example.kitchen.domain.repository.KitchenRepository
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

/** Mock implementation of KitchenRepository for testing purposes */
class MockKitchenRepository : KitchenRepository {
    private var currentStats = UserStats(
        streak = 0,
        totalSavings = 0
    )

    // userStatsを取得する（擬似的に遅延を入れる）
    override suspend fun getUserStats(): UserStats {
        delay(0.5.seconds)

        return currentStats
    }

    // userStatsを更新する（擬似的に遅延を入れる）
    override suspend fun updateStats(newStats: UserStats) {
        delay(0.5.seconds)
        currentStats = newStats
    }
}
