package com.hyun6ik.userservice.infrastructure.user.repository

import com.hyun6ik.userservice.domain.user.entity.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<User, Long> {

    suspend fun findByEmail(email: String) : User?
}
