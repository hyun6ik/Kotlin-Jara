package com.hyun6ik.userservice.global.config

import com.hyun6ik.userservice.global.argumentResolver.AuthTokenResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer

@Configuration
class WebConfig(
    private val authTokenResolver: AuthTokenResolver,
) : WebFluxConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET","POST","PUT","DELETE")
            .maxAge(3600)
    }

    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.addCustomResolver(authTokenResolver)
    }
}
