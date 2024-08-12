package com.example.connectme.data.repository

import com.example.connectme.data.model.User
import com.example.connectme.db.UserDao
import com.example.connectme.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(private val apiService: ApiService, private val userDao: UserDao) {
    fun authenticatePhoneNumber(phoneNumber: String): Flow<Unit> //Индикация завершения операции:
    //добавить обработку ошибок, чтобы пользователи могли реагировать на потенциальные проблемы.
            = flow {
        apiService.authenticatePhoneNumber(phoneNumber = phoneNumber)
        emit(Unit)
    }

    fun verifySmsCode(
        phoneNumber: String,
        code: String
    ): Flow<Unit> //Индикация завершения операции:
    //добавить обработку ошибок, чтобы пользователи могли реагировать на потенциальные проблемы.
            = flow {
        apiService.verifySmsCode(phoneNumber = phoneNumber, code = code)
        emit(Unit)
    }

    fun getUser(id: Int): Flow<User> = userDao.getUser(id)

}