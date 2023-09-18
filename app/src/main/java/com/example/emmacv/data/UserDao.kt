package com.example.emmacv.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.emmacv.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDetail(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllDetails(): LiveData<User>

    @Update
    suspend fun updateDetails(user: User)
}