package com.proj.urlshortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class UrlShortenerServiceApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<UrlShortenerServiceApplication>(*args)
}
