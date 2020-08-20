package com.example.lists.di

import android.content.Context
import androidx.room.Room
import com.example.lists.data.room.ListDao
import com.example.lists.data.room.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideLocalDb(@ApplicationContext appContext: Context): LocalDatabase{
        return Room.databaseBuilder(
            appContext,
            LocalDatabase::class.java,
            "lists_database.db"
        ).build()
    }

    @Provides
    fun provideListDao(localDb: LocalDatabase): ListDao{
        return localDb.listDao()
    }

}