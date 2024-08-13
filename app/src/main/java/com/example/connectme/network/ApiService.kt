package com.example.connectme.network

import com.example.connectme.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/v1/users/me/")
    suspend fun getUserProfile(): User

    @POST("api/v1/users/refresh-token/")
    fun refreshToken(@Body body: Map<String, String>): Call<TokenResponse>
    @POST("auth/phone")
    suspend fun authenticatePhoneNumber(phoneNumber: String)

    @POST("auth/sms")
    suspend fun verifySmsCode(phoneNumber: String, code: String)
}

data class TokenResponse(val accessToken: String)