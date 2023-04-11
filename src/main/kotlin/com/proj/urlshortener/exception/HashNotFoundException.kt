package com.proj.urlshortener.exception

class HashNotFoundException (message: String) :
	ServiceException("HASH_NOT_FOUND", message)
