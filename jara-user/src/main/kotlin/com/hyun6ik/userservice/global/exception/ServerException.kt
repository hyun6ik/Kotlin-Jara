package com.hyun6ik.userservice.global.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message)


data class UserExistsException(
    override val message: String = "이미 존재하는 유저입니다.",
) : ServerException(409, message)

data class UserNotFoundException(
    override val message: String = "해당 유저를 찾을 수 없습니다."
) : ServerException(404, message)

data class PasswordNotMatchedException(
    override val message: String = "비밀번호가 일치하지 않습니다."
) : ServerException(400, message)

data class InvalidJwtTokenException(
    override val message: String = "유효하지 않는 토큰 않습니다."
) : ServerException(400, message)
