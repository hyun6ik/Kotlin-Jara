package com.hyun6ik.userservice.infrastructure.user

import com.hyun6ik.userservice.domain.user.entity.User
import com.hyun6ik.userservice.infrastructure.user.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserStore(
    private val userRepository: UserRepository,
) {
    suspend fun store(user: User) = userRepository.save(user)
}
