package com.hyun6ik.userservice.interfaces.user.controller

import com.hyun6ik.userservice.domain.user.UserService
import com.hyun6ik.userservice.global.annotation.AuthToken
import com.hyun6ik.userservice.interfaces.user.dto.request.SignInRequest
import com.hyun6ik.userservice.interfaces.user.dto.request.SignUpRequest
import com.hyun6ik.userservice.interfaces.user.dto.response.MeResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    suspend fun signUp(@RequestBody request: SignUpRequest) =
        ResponseEntity.ok(userService.signUp(request))

    @PostMapping("/signin")
    suspend fun signIn(@RequestBody request: SignInRequest) =
        ResponseEntity.ok(userService.signIn(request))

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun logout(@AuthToken token: String) =
        ResponseEntity.ok(userService.logout(token))

    @GetMapping("/me")
    suspend fun get(@AuthToken token: String) =
        ResponseEntity.ok(userService.getBy(token))
}
