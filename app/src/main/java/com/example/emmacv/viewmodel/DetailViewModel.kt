package com.example.emmacv.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.emmacv.database.DetailDatabase
import com.example.emmacv.model.User
import com.example.emmacv.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {

    val readAllDetail: LiveData<User>

    private val detailRepository: DetailRepository

    init {
        val userDao = DetailDatabase.getDatabase(application).userDoa()
        detailRepository= DetailRepository(userDao)
        readAllDetail=detailRepository.readAllDetail
    }
    fun addUser(user: User){
        viewModelScope.launch (  Dispatchers.IO ){
            detailRepository.addDetail(user)
        }
    }
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            detailRepository.updateDetail(user)
        }
    }
}