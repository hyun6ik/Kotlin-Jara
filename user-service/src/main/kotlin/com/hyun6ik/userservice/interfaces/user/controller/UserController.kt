package com.hyun6ik.userservice.interfaces.user.controller

import com.hyun6ik.userservice.domain.user.UserService
import com.hyun6ik.userservice.interfaces.user.dto.request.SignInRequest
import com.hyun6ik.userservice.interfaces.user.dto.request.SignUpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
}
