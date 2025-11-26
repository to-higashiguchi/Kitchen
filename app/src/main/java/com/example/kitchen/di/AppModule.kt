package com.example.kitchen.di

import com.example.kitchen.data.repository.MockKitchenRepository
import com.example.kitchen.domain.repository.KitchenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKitchenRepository(): KitchenRepository {
        return MockKitchenRepository()
    }
}
