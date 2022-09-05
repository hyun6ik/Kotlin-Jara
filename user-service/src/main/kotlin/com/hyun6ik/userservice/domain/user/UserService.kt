package com.hyun6ik.userservice.domain.user

import com.hyun6ik.userservice.domain.user.entity.User
import com.hyun6ik.userservice.global.utils.BCryptUtils
import com.hyun6ik.userservice.infrastructure.user.UserReader
import com.hyun6ik.userservice.infrastructure.user.UserStore
import com.hyun6ik.userservice.infrastructure.user.UserValidator
import com.hyun6ik.userservice.interfaces.user.dto.request.SignUpRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userReader: UserReader,
    private val userStore: UserStore,
    private val userValidator: UserValidator,
) {

    @Transactional
    suspend fun signUp(request: SignUpRequest) {
        with(request) {
            userValidator.validateExistsUserBy(email)
            val initUser = User(
                email = email,
                password = BCryptUtils.hash(password),
                username = username,
            )
            userStore.store(initUser)
        }
    }
}
