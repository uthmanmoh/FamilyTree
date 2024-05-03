package com.example.familytree.controller

import com.example.familytree.entity.Member
import com.example.familytree.service.MemberService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(private val memberService: MemberService) {
    @GetMapping
    fun getAllMembers() = memberService.getAllMembers()

    @PostMapping
    fun addMember(
        @RequestBody member: Member,
    ) = memberService.addMember(member)

    @DeleteMapping("/{id}")
    fun deleteMember(
        @PathVariable id: Long,
    ) = memberService.deleteMember(id)
}
