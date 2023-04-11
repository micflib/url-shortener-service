package com.proj.urlshortener.repository

import com.proj.urlshortener.dto.UrlDocument
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : CrudRepository<UrlDocument, String>
