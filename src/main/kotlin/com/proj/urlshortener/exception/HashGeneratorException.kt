package com.proj.urlshortener.exception

class HashGeneratorException(message: String) :
    ServiceException("HASH_GENERATOR_ERROR", message)
