package com.alextos.plankingtimer.domain.services

import com.alextos.plankingtimer.domain.util.Result

interface AuthenticationService {
    fun signUp(
        email: String,
        password: String,
        completion: (Result<String>) -> Unit
    )

    fun login(
        email: String,
        password: String,
        completion: (Result<String>) -> Unit
    )
}