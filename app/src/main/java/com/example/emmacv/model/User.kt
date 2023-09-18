package com.example.emmacv.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val fullName: String,
    val title: String,
    val slackName:String,
    val gitHandle:String,
    val shortBio:String
): Parcelable {
}