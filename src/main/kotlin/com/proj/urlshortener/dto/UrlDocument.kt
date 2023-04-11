package com.proj.urlshortener.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = URL_COLLECTION)
class UrlDocument (

	@Id
	val hash: String,

	@Field(LONG_URL_FIELD)
	@Indexed(unique = true)
	val url: String

) {
	data class Builder(
		var hash: String,
		var url: String
	) {
		fun build() = UrlDocument(hash, url);
	}
}
