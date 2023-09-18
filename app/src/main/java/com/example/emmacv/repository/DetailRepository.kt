package com.example.emmacv.repository

import androidx.lifecycle.LiveData
import com.example.emmacv.data.UserDao
import com.example.emmacv.model.User


class DetailRepository (private val userDao: UserDao){
    val readAllDetail : LiveData<User> = userDao.readAllDetails()


    suspend fun addDetail(user: User) {
        userDao.addDetail(user)
    }
    suspend fun updateDetail(user: User){
        userDao.updateDetails(user)
    }
}