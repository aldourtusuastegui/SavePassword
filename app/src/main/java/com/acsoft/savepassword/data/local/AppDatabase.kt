package com.acsoft.savepassword.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acsoft.savepassword.data.model.Account

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun AccountDao(): AccountDao
}