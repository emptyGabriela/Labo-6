package com.falconteam.laboratorio_5.data.repository

import com.falconteam.laboratorio_5.data.resource.ErrorResponse
import com.falconteam.laboratorio_5.data.resource.Resource
import com.falconteam.laboratorio_5.data.service.LoginRequest
import com.falconteam.laboratorio_5.data.service.LoginResponse
import com.falconteam.laboratorio_5.domain.Repository.AuthRepository
import com.google.gson.Gson

class AuthRepositoryImpl<AuthService>(
    private val authService: AuthService,
    private val gson: Gson
): AuthRepository {
    override suspend fun login(username: String, password: String): Resource<LoginResponse> {
        val loginRequest = LoginRequest(username, password)

        try {
            val response = authService.login(loginRequest)
            if (response.isSuccessful) {
                val loginResponse = response.body()!!
                return Resource.Success(loginResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse.message
                    } catch (e: Exception) {
                        "Error message could not be obtained"
                    }
                } else {
                    "Error message not found"
                }
                return Resource.Error(errorMessage)
            }
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Network error")
        }
    }
}