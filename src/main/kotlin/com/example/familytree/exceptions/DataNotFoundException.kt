package com.example.familytree.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class DataNotFoundException(message: String) : RuntimeException(message)
