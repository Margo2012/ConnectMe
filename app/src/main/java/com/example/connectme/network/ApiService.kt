package com.example.connectme.network

import retrofit2.http.POST

interface ApiService {
    @POST("auth/phone")
    suspend fun authenticatePhoneNumber(phoneNumber: String)

    @POST("auth/sms")
    suspend fun verifySmsCode(phoneNumber: String, code: String)
}