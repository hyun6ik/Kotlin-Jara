package com.hyun6ik.userservice.interfaces.user.controller

import com.hyun6ik.userservice.domain.user.UserService
import com.hyun6ik.userservice.global.annotation.AuthToken
import com.hyun6ik.userservice.interfaces.user.dto.request.SignInRequest
import com.hyun6ik.userservice.interfaces.user.dto.request.SignUpRequest
import com.hyun6ik.userservice.interfaces.user.dto.request.UserUpdateRequest
import com.hyun6ik.userservice.interfaces.user.dto.response.MeResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
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
        ResponseEntity.ok(MeResponse.of(userService.getBy(token)))

    @GetMapping("/{userId}/username")
    suspend fun getUsername(@PathVariable userId: Long) =
        ResponseEntity.ok(mapOf("reporter" to userService.getBy(userId).username))

    @PostMapping("/{id}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    suspend fun update(
        @PathVariable id: Long,
        @ModelAttribute request: UserUpdateRequest,
        @AuthToken token: String,
        @RequestPart("profileUrl") filePart: FilePart,
    ) = ResponseEntity.ok(userService.update(id, request, token, filePart))
}
