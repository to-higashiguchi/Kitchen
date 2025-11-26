package com.example.kitchen.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.padding

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
            // 2. Columnに padding(innerPadding) をつける ← これが解決策！
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(text = "連続記録: ${state.streak}日")
                Text(text = "節約額: ¥${state.totalSavings}")
            }
        }
    }
}
