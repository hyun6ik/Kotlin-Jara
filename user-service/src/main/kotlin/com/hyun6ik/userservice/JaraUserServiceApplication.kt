package com.hyun6ik.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class JaraUserServiceApplication

fun main(args: Array<String>) {
    runApplication<JaraUserServiceApplication>(*args)
}
