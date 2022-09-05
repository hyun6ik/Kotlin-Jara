package com.hyun6ik.userservice.global.argumentResolver

import com.hyun6ik.userservice.global.annotation.AuthToken
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class AuthTokenResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthToken::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<Any> {
        val authHeader = getAuthHeader(exchange)
        checkNotNull(authHeader)

        val token = removeBearer(authHeader)
        return token.toMono()
    }

    private fun getAuthHeader(exchange: ServerWebExchange) =
        exchange.request.headers["Authorization"]?.first()

    private fun removeBearer(authHeader: String) = authHeader.split(" ")[1]
}
