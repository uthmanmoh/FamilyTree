package com.example.familytree.controller

import com.example.familytree.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(private val memberService: MemberService) {
    @GetMapping
    fun getAllMembers() = memberService.getAllMembers()
}
