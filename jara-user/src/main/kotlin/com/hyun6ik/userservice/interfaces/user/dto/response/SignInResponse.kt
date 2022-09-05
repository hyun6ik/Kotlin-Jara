package com.hyun6ik.userservice.interfaces.user.dto.response

data class SignInResponse(
    val email: String,
    val username: String,
    val token: String,
)
