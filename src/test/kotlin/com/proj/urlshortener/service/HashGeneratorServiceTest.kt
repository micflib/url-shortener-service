package com.proj.urlshortener.service

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HashGeneratorServiceTest {

    @InjectMockKs
    lateinit var hashGeneratorService: HashGeneratorService

    @Test
    fun `should generate hash when url valid`() {
        val hash = hashGeneratorService.get("url")
        assertNotNull(hash)
        assertEquals(hash.length, 8)
    }
}
