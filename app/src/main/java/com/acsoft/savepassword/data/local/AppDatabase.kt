package com.acsoft.savepassword.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.acsoft.savepassword.data.model.Account

@Database(entities = [Account::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun AccountDao(): AccountDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun  getDatabase(context: Context) : AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "database")
                .build()

            return  INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}