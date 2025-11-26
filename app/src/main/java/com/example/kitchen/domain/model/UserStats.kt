package com.example.kitchen.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserStats(
    val streak: Int,
    val totalSavings: Int
) {
    // 自炊した場合の処理
    fun cook(): UserStats {
        return this.copy(
            streak = this.streak + 1,
            totalSavings = this.totalSavings + 1100
        )
    }

    // 突発的に外食した場合の処理
    fun resetStreak(): UserStats {
        return this.copy(
            streak = 0
        )
    }
}
