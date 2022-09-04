package com.alextos.plankingtimer.domain.services

import com.alextos.plankingtimer.common.util.Result

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

    fun isUserAuthenticated(): Boolean

    fun getUserId(): String?
}