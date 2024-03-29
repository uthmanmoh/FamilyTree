package com.example.familytree.service

import com.example.familytree.UserRepository
import com.example.familytree.entity.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers() = userRepository.findAll()

    fun createUser(
        username: String,
        password: String,
        email: String,
        fullName: String,
    ): User {
        val user = User(username = username, password = password, email = email, fullName = fullName)
        return userRepository.save(user)
    }
}

