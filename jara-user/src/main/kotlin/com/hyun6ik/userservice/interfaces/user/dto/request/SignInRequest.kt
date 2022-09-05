package com.hyun6ik.userservice.interfaces.user.dto.request

data class SignInRequest(
    val email: String,
    val password: String,
)
