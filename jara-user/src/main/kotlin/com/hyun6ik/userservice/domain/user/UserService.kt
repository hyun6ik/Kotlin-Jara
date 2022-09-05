package com.hyun6ik.userservice.domain.user

import com.hyun6ik.userservice.domain.user.entity.User
import com.hyun6ik.userservice.global.config.JWTProperties
import com.hyun6ik.userservice.global.exception.InvalidJwtTokenException
import com.hyun6ik.userservice.global.utils.BCryptUtils
import com.hyun6ik.userservice.global.utils.FileUtils
import com.hyun6ik.userservice.global.utils.JWTClaim
import com.hyun6ik.userservice.global.utils.JwtUtils
import com.hyun6ik.userservice.infrastructure.user.UserReader
import com.hyun6ik.userservice.infrastructure.user.UserStore
import com.hyun6ik.userservice.infrastructure.user.UserValidator
import com.hyun6ik.userservice.interfaces.user.dto.request.SignInRequest
import com.hyun6ik.userservice.interfaces.user.dto.request.SignUpRequest
import com.hyun6ik.userservice.interfaces.user.dto.request.UserUpdateRequest
import com.hyun6ik.userservice.interfaces.user.dto.response.MeResponse
import com.hyun6ik.userservice.interfaces.user.dto.response.SignInResponse
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Service
@Transactional(readOnly = true)
class UserService(
    private val userReader: UserReader,
    private val userStore: UserStore,
    private val userValidator: UserValidator,
    private val jwtProperties: JWTProperties,
    private val cacheManager: CoroutineCacheManager<User>,
) {

    companion object {
        private val CACHE_TTL = Duration.ofMinutes(1)
    }

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

    suspend fun signIn(request: SignInRequest): SignInResponse {
        val user = userReader.getUserBy(request.email)
        return with(user) {
            userValidator.verifyPassword(request.password, password)

            val jwtClaim = JWTClaim(
                userId = id!!,
                email = email,
                profileUrl = profileUrl,
                username = username
            )

            val token = JwtUtils.createToken(jwtClaim, jwtProperties)

            cacheManager.awaitPut(key = token, value = this, ttl = CACHE_TTL)

            SignInResponse(
                email = email,
                username = username,
                token = token
            )
        }
    }

    suspend fun logout(token: String) {
        cacheManager.awaitEvict(token)
    }

    suspend fun getBy(token: String) : User {

        val cachedUser = cacheManager.awaitGetOrPut(key = token, ttl = CACHE_TTL) {
            // 캐시가 유효하지 않은 경우 동작
            val decodedJWT = JwtUtils.decode(token, jwtProperties.secret, jwtProperties.issuer)

            val userId = decodedJWT.claims["userId"]?.asLong() ?: throw InvalidJwtTokenException()
            userReader.getUserBy(userId)
        }
        return cachedUser
    }

    suspend fun getBy(userId: Long) = userReader.getUserBy(userId)

    @Transactional
    suspend fun update(id: Long, request: UserUpdateRequest, token: String, filePart: FilePart) : User {
        val profileUrl = FileUtils.createFile(id, filePart)
        val user = getBy(token)

        return user.copy(username = request.username, profileUrl = profileUrl ?: user.profileUrl).also {
            cacheManager.awaitPut(key = token, value = it, ttl = CACHE_TTL)
        }
    }
}
