package com.testvass.database.di

import android.content.Context
import androidx.room.Room
import com.testvass.database.constants.DatabaseConstants.KEY_NAME_DATABASE
import com.testvass.database.factories.RamDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RamDatabase::class.java, KEY_NAME_DATABASE).build()

    @Singleton
    @Provides
    fun provideCharacterDao(database: RamDatabase) =
        database.getCharacterDao()
}