package com.proj.urlshortener.router

import com.proj.urlshortener.dto.CREATE_URL_MAPPING
import com.proj.urlshortener.dto.GET_URL_MAPPING
import com.proj.urlshortener.exception.ServiceException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class UrlShortenerRouter {

	@Bean
	fun routeCreateShortUrl(urlShortenerHandler: UrlShortenerHandler) = coRouter {
		POST(CREATE_URL_MAPPING, urlShortenerHandler::create)
	}

	@Bean
	fun routeGetUrl(urlShortenerHandler: UrlShortenerHandler) = coRouter {
		GET(GET_URL_MAPPING, urlShortenerHandler::get)
		onError<Exception>(urlShortenerHandler::exceptionHandler)
	}
}
