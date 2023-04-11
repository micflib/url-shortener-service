package com.proj.urlshortener.service

import com.proj.urlshortener.exception.HashGeneratorException
import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Component
class HashGeneratorService {

    fun get(longURL: String): String {
        return try {
            // Create MD5 Hash
            val digest = MessageDigest.getInstance("MD5")
            digest.update(longURL.toByteArray())
            val messageDigest = digest.digest()
            // Create Hex String
            val hexString = StringBuilder()
            for (b in messageDigest) {
                hexString.append(Integer.toHexString(0xFF and b.toInt()))
	            if (hexString.length > 7) break
            }
            hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            throw HashGeneratorException("Error while generating hash.")
        }
    }
}
