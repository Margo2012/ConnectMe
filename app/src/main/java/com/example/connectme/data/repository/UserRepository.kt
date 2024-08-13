package com.example.connectme.data.repository

import com.example.connectme.data.model.User
import com.example.connectme.db.UserDao
import com.example.connectme.network.ApiService
import com.example.connectme.network.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

/*class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val tokenManager: TokenManager
) {
    *//*fun authenticatePhoneNumber(phoneNumber: String): Flow<Unit> //Индикация завершения операции:
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
    }*//*

    fun getUserProfile(): Flow<User> = flow {
        try {
            val userProfile = apiService.getUserProfile()
            userDao.apply {
                deleteAll()
                insert(userProfile)
            }
            emit(userProfile)
        } catch (e: Exception) {
            // If there's an error (e.g., network error), fetch from local database
            emitAll(userDao.getUser())
        }
    }

    fun authenticate(phoneNumber: String, code: String): Flow<Unit> = flow {
        val tokenResponse = apiService.verifySmsCode(mapOf("phone_number" to phoneNumber, "code" to code))
        tokenManager.accessToken = tokenResponse.accessToken
        tokenManager.refreshToken = tokenResponse.refreshToken
        emit(Unit)
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        tokenManager.accessToken = accessToken
        tokenManager.refreshToken = refreshToken
    }
    fun getUser(id: Int): Flow<User> = userDao.getUser()

}*/

class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val tokenManager: TokenManager
) {

    fun getUserProfile(): Flow<User> = flow {
        try {
            val userProfile = apiService.getUserProfile()
            userDao.apply {
                deleteAll()
                insert(userProfile)
            }
            emit(userProfile)
        } catch (e: Exception) {
            // If there's an error (e.g., network error), fetch from local database
            emitAll(userDao.getUser())
        }
    }

    fun authenticate(phoneNumber: String, code: String): Flow<Unit> = flow {
        val tokenResponse = apiService.verifySmsCode(mapOf("phone_number" to phoneNumber, "code" to code))
        tokenManager.accessToken = tokenResponse.accessToken
        tokenManager.refreshToken = tokenResponse.refreshToken
        emit(Unit)
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        tokenManager.accessToken = accessToken
        tokenManager.refreshToken = refreshToken
    }
}