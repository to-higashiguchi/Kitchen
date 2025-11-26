package com.example.kitchen.presentation.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val state = uiState

    Scaffold(
        modifier = Modifier.fillMaxSize() // 画面いっぱいに広げる
    ) { innerPadding ->
        // innerPadding には「上:ステータスバーの高さ, 下:ナビバーの高さ」などの情報が入っています

        if (state == null) {
            // Loadingの時もpaddingを考慮する
            Text(
                text = "Loading...",
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                // 数字の表示
                Text(text = "🔥 ${state.streak}日連続")
                Text(text = "💰 ¥${state.totalSavings} 貯金")
                // 少し隙間をあける
                Spacer(modifier = Modifier.height(32.dp))
                // ボタンエリア
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp) // ボタンの間隔
                ) {
                    // 自炊ボタン
                    Button(onClick = { viewModel.onCook() }) {
                        Text("自炊した！")
                    }

                    // 外食ボタン（リセット）
                    // 誤操作防止のため色を変えてもいいですね
                    Button(
                        onClick = { viewModel.onResetStreak() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("外食した...")
                    }
                }
            }
        }
    }
}
