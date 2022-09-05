package com.hyun6ik.userservice.interfaces.user.dto.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val username: String,
)
