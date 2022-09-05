package hyun6ik.issueservice.global.argumentResolver

import hyun6ik.issueservice.global.annotation.Auth
import hyun6ik.issueservice.global.exception.UnAuthorizedException
import hyun6ik.issueservice.global.model.AuthUser
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class AuthUserHandlerArgumentResolver(
    @Value("\${auth.url}")
    val authUrl: String,

) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(Auth::class.java)
        // return AuthUser::class.java.isAssignableFrom(parameter.parameterType)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {

        val authHeader = webRequest.getHeader("Authorization") ?: throw UnAuthorizedException()

        return runBlocking {
            WebClient.create()
                .get()
                .uri(authUrl)
                .header("Authorization", authHeader)
                .retrieve()
                .awaitBody<AuthUser>()
        }
    }
}
