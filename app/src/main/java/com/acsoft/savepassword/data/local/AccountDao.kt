package com.acsoft.savepassword.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acsoft.savepassword.data.model.Account

@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    suspend fun getAllAccounts() : LiveData<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)


}