package com.proj.urlshortener.router

import com.ninjasquad.springmockk.MockkBean
import com.proj.urlshortener.dto.*
import com.proj.urlshortener.repository.UrlRepository
import com.proj.urlshortener.service.HashGeneratorService
import io.mockk.coEvery
import io.mockk.coVerify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriBuilder
import java.util.*

@WebFluxTest
@ContextConfiguration(classes = [UrlShortenerRouter::class, UrlShortenerHandler::class])
class UrlShortenerHandlerIntegrationTest
{
	@MockkBean
	lateinit var repository: UrlRepository

	@MockkBean
	lateinit var hashGeneratorService: HashGeneratorService

	@Autowired
	lateinit var webTestClient: WebTestClient

	private val hashCode = "947d6f24"
	private val url = "htpp://test.com"
	private val urlDocument = UrlDocument(hashCode, url)


	@Test
	fun `should create hash when param is valid`() {
		coEvery { hashGeneratorService.get(any()) }.returns(hashCode)
		coEvery { repository.save(any()) }.returns(urlDocument)

		webTestClient.post()
			.uri { uriBuilder: UriBuilder ->
				uriBuilder
					.path(CREATE_URL_MAPPING)
					.queryParam(URL_PARAM, url)
					.build()
			}
			.exchange()
			.expectStatus().isOk

		coVerify(exactly = 1) { repository.save(any()) }
		coVerify(exactly = 1) { hashGeneratorService.get(any()) }
	}

	@Test
	fun `should get url when hash is valid`() {
		coEvery { repository.findById(any()) }.returns(Optional.of(urlDocument))

		webTestClient.get()
			.uri { uriBuilder: UriBuilder ->
				uriBuilder
					.path(GET_URL_MAPPING)
					.queryParam(HASH_PARAM, hashCode)
					.build()
			}
			.exchange()
			.expectStatus().isOk

		coVerify(exactly = 1) { repository.findById(any()) }
	}

	@Test
	fun `should return 404 when hash is not valid`() {
		coEvery { repository.findById(any()) }.returns(Optional.empty())

		webTestClient.get()
			.uri { uriBuilder: UriBuilder ->
				uriBuilder
					.path(GET_URL_MAPPING)
					.queryParam(HASH_PARAM, hashCode)
					.build()
			}
			.exchange()
			.expectStatus().isNotFound

		coVerify(exactly = 1) { repository.findById(any()) }
	}
}

