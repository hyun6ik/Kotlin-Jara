package com.hyun6ik.userservice.interfaces.user.dto.response

import com.hyun6ik.userservice.domain.user.entity.User
import java.time.LocalDateTime

data class MeResponse(
    val id: Long,
    val profileUrl: String?,
    val username: String,
    val email: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
) {
    companion object {
        fun of(user: User) : MeResponse {
            return with(user) {
                MeResponse(
                    id = id!!,
                    profileUrl = if (profileUrl.isNullOrEmpty()) null else "http://localhost:9090/images/$profileUrl",
                    username = username,
                    email = email,
                    createdAt = createdAt,
                    updatedAt = updatedAt
                )
            }
        }
    }
}

