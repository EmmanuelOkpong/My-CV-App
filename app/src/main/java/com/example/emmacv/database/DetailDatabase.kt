package com.example.emmacv.database

import android.content.Context
import android.os.Parcelable
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.emmacv.data.UserDao
import com.example.emmacv.model.User
import kotlinx.parcelize.Parcelize


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DetailDatabase() : RoomDatabase(){
    abstract fun userDoa(): UserDao

    companion object{

        @Volatile
        private var INSTANCE:DetailDatabase?=null

        fun getDatabase(context: Context):DetailDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    DetailDatabase::class.java,
                    "user database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}