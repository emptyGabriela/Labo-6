package com.falconteam.laboratorio_5.data.remote


import com.falconteam.laboratorio_5.data.repository.AuthRepositoryImpl
import com.falconteam.laboratorio_5.data.repository.PostRepositoryImpl
import com.falconteam.laboratorio_5.data.service.AuthService
import com.falconteam.laboratorio_5.data.service.PostService
import com.falconteam.laboratorio_5.domain.Repository.AuthRepository
import com.falconteam.laboratorio_5.domain.Repository.PostRepository
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dei.uca.edu.sv/moviles/apil6/"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val apiService: AuthService by lazy {
        RetrofitClient.retrofit.create(AuthService::class.java)
    }

    val postService: PostService by lazy {
        RetrofitClient.retrofit.create(PostService::class.java)
    }
}

object RepositoryProvider {
    private val gson = Gson()
    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(ApiClient.apiService, gson)
    }

    val postRepository: PostRepository by lazy {
        PostRepositoryImpl(ApiClient.postService, gson)
    }
}