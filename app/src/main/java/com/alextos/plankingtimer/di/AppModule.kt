package com.alextos.plankingtimer.di

import android.content.Context
import com.alextos.plankingtimer.common.util.SoundPlayer
import com.alextos.plankingtimer.data.AuthenticationServiceImpl
import com.alextos.plankingtimer.data.RepositoryServiceImpl
import com.alextos.plankingtimer.data.TimerServiceImpl
import com.alextos.plankingtimer.domain.services.AuthenticationService
import com.alextos.plankingtimer.domain.services.RepositoryService
import com.alextos.plankingtimer.domain.services.TimerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSoundPlayer(@ApplicationContext appContext: Context): SoundPlayer {
        return SoundPlayer(appContext)
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(): AuthenticationService {
        return AuthenticationServiceImpl()
    }

    @Provides
    @Singleton
    fun provideRepositoryService(): RepositoryService {
        return RepositoryServiceImpl()
    }

    @Provides
    @Singleton
    fun provideTimerService(): TimerService {
        return TimerServiceImpl()
    }
}