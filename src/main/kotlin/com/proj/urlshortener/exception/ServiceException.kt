package com.proj.urlshortener.exception

sealed class ServiceException(val code: String, message: String) : Exception(message)
