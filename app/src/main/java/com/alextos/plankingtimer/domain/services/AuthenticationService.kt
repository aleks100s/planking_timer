package com.alextos.plankingtimer.domain.services

interface AuthenticationService {
    fun signUp(email: String, password: String): String
    fun login(email: String, password: String): String
}