package com.example.kitchen.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchen.domain.model.UserStats
import com.example.kitchen.domain.repository.KitchenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: KitchenRepository
): ViewModel() {
    // 内部で書き換える用のデータ
    private val _uiState = MutableStateFlow<UserStats?>(null)
    // 画面に公開する用のデータ（読み取り専用）
    val uiState: StateFlow<UserStats?> = _uiState.asStateFlow()

    init {
        loadStats()
    }

    private fun loadStats() {
        viewModelScope.launch {
            val status = repository.getUserStats()
            _uiState.value = status
        }
    }

    fun onCook() {
        viewModelScope.launch {
            // 1. 現在の状態を取得(もしnullなら何もしない)
            val current = _uiState.value ?: return@launch
            // 2. cook() を呼び出して新しい状態を取得
            val newStats = current.cook()
            // 3. リポジトリに新しい状態を保存
            repository.updateStats(newStats)
            // 4. StateFlowを更新して画面を再描画
            _uiState.value = newStats
        }
    }

    fun onResetStreak() {
        viewModelScope.launch {
            val current = _uiState.value ?: return@launch
            val newStats = current.resetStreak()
            repository.updateStats(newStats)
            _uiState.value = newStats
        }
    }
}
