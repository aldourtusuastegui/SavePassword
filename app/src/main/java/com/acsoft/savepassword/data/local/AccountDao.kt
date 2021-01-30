package com.acsoft.savepassword.data.local

import androidx.room.*
import com.acsoft.savepassword.data.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM Account ORDER BY account")
    fun getAllAccounts() : Flow<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)

    @Update
    suspend fun updateAccount(account: Account)

    @Query("UPDATE Account SET favorite = :isFavorite where id = :id")
    suspend fun setFavorite(id:Int,isFavorite: Boolean)

    @Delete
    suspend fun deleteAccount(account: Account)

}