package com.hyun6ik.userservice.infrastructure.user

import com.hyun6ik.userservice.domain.user.entity.User
import com.hyun6ik.userservice.global.exception.UserNotFoundException
import com.hyun6ik.userservice.infrastructure.user.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {

    suspend fun getUserBy(email: String) =
        userRepository.findByEmail(email) ?: throw UserNotFoundException()

    suspend fun getUserBy(id: Long): User =
        userRepository.findById(id) ?: throw UserNotFoundException()
}
