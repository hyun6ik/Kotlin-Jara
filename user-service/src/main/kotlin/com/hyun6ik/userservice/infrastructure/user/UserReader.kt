package com.hyun6ik.userservice.infrastructure.user

import com.hyun6ik.userservice.infrastructure.user.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {
}
