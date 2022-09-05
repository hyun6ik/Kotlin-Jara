package com.hyun6ik.userservice.infrastructure.user

import com.hyun6ik.userservice.global.exception.UserExistsException
import com.hyun6ik.userservice.infrastructure.user.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserValidator(
    private val userRepository: UserRepository,
) {
    suspend fun validateExistsUserBy(email: String) {
        userRepository.findByEmail(email)?.let {
            throw UserExistsException()
        }
    }
}
