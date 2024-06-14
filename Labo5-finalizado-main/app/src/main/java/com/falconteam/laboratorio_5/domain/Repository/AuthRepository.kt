package com.falconteam.laboratorio_5.domain.Repository

import com.falconteam.laboratorio_5.data.resource.Resource
import com.falconteam.laboratorio_5.data.service.LoginResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponse>
}