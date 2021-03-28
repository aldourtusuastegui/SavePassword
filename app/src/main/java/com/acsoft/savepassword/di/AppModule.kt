package com.acsoft.savepassword.di

import android.content.Context
import androidx.room.Room
import com.acsoft.savepassword.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "database")
        .build()

    @Singleton
    @Provides
    fun provideAccountDao(db:AppDatabase) = db.AccountDao()

}