package com.alextos.plankingtimer.di

import android.content.Context
import com.alextos.plankingtimer.common.services.TimerNotificationService
import com.alextos.plankingtimer.common.services.TimerNotificationServiceImpl
import com.alextos.plankingtimer.common.util.SoundPlayer
import com.alextos.plankingtimer.data.RepositoryServiceImpl
import com.alextos.plankingtimer.data.TimerServiceImpl
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
    fun provideTimerNotificationService(@ApplicationContext appContext: Context): TimerNotificationService {
        return TimerNotificationServiceImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideSoundPlayer(@ApplicationContext appContext: Context): SoundPlayer {
        return SoundPlayer(appContext)
    }

    @Provides
    @Singleton
    fun provideRepositoryService(@ApplicationContext appContext: Context): RepositoryService {
        return RepositoryServiceImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideTimerService(): TimerService {
        return TimerServiceImpl()
    }
}