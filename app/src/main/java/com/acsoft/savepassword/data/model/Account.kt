package com.acsoft.savepassword.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "account")
    val account: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
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
) : Parcelable

