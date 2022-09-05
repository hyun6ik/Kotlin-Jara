package com.hyun6ik.userservice.global.utils

import com.hyun6ik.userservice.global.config.JWTProperties
import mu.KotlinLogging
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

class JwtUtilsTest{

    private val logger = KotlinLogging.logger{}

    @Test
    fun `토큰 만들기 테스트`()  {
        //given
        val jwtClaim = JWTClaim(
            userId = 1L,
            email = "hyun6ik@naver.com",
            profileUrl = "profile.jpg",
            username = "현식"
        )

        val properties = JWTProperties(
            issuer = "jara",
            subject = "auth",
            expiresTime = 3600,
            secret = "my-secret"
        )
        //when
        val token = JwtUtils.createToken(jwtClaim, properties)
        logger.info { "token = $token" }
        //then
        assertThat(token).isNotNull
    }

    @Test
    fun `토큰 디코드 테스트`() {
        //given
        val jwtClaim = JWTClaim(
            userId = 1L,
            email = "hyun6ik@naver.com",
            profileUrl = "profile.jpg",
            username = "현식"
        )

        val properties = JWTProperties(
            issuer = "jara",
            subject = "auth",
            expiresTime = 3600,
            secret = "my-secret"
        )

        val token = JwtUtils.createToken(jwtClaim, properties)
        //when
        val decode = JwtUtils.decode(token, properties.secret, properties.issuer)
        //then
        with(decode) {
            logger.info { "claims : $claims"  }

            val userId = claims["userId"]!!.asLong()
            assertThat(userId).isEqualTo(jwtClaim.userId)

            val email = claims["email"]!!.asString()
            assertThat(email).isEqualTo(jwtClaim.email)

            val profileUrl = claims["profileUrl"]!!.asString()
            assertThat(profileUrl).isEqualTo(jwtClaim.profileUrl)

            val username = claims["username"]!!.asString()
            assertThat(username).isEqualTo(jwtClaim.username)

        }
    }
}
