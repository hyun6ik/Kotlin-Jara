package com.hyun6ik

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinJaraApplication

fun main(args: Array<String>) {
    runApplication<KotlinJaraApplication>(*args)
}
