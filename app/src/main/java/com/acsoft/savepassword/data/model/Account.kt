package com.acsoft.savepassword.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "account_name")
    val name: String = "",
    @ColumnInfo(name = "password")
    val password: String = "",
    @ColumnInfo(name = "website")
    val website: String = "",
    @ColumnInfo(name = "notes")
    val notes: String = "",
    @ColumnInfo(name = "favorite")
    val favorite: Boolean = false,
    @ColumnInfo(name = "date")
    val date: String = "",
)

