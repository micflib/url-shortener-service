package com.proj.urlshortener.router

import com.proj.urlshortener.dto.HASH_PARAM
import com.proj.urlshortener.dto.URL_PARAM
import com.proj.urlshortener.dto.UrlDocument
import com.proj.urlshortener.exception.ErrorResponse
import com.proj.urlshortener.exception.HashNotFoundException
import com.proj.urlshortener.repository.UrlRepository
import com.proj.urlshortener.service.HashGeneratorService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class UrlShortenerHandler(
    private val repository: UrlRepository,
    private val hashGeneratorService: HashGeneratorService
) {
    suspend fun create(request: ServerRequest): ServerResponse {
        val url = request.queryParam(URL_PARAM).get()
        val document = UrlDocument.Builder(hashGeneratorService.get(url), url).build()
        repository.save(document)
        return ServerResponse.ok().bodyValue(document.hash).awaitSingle()
    }

    suspend fun get(request: ServerRequest): ServerResponse {
        val hash = request.queryParam(HASH_PARAM).get()
        val urlDocument = repository.findById(hash)
        if (urlDocument.isEmpty) throw HashNotFoundException("Invalid operation. Cannot resolve $hash")
        return ServerResponse.ok().bodyValue(urlDocument.get().url).awaitSingle()
    }

    @Suppress("UnusedPrivateMember")
    suspend fun exceptionHandler(
        throwable: Throwable,
        serverRequest: ServerRequest
    ): ServerResponse {
        val serviceException = throwable as HashNotFoundException
        return ServerResponse
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(ErrorResponse(serviceException.code, serviceException.message!!)))
            .awaitSingle()
    }
}
