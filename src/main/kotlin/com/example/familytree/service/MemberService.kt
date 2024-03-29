package com.example.familytree.service

import com.example.familytree.MemberRepository
import com.example.familytree.entity.Member
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun getAllMembers() = memberRepository.findAll()

    fun addMember(
        name: String,
        birthDate: LocalDate,
        deathDate: LocalDate,
    ): Member {
        val member = Member(name = name, birthDate = birthDate, deathDate = deathDate)
        return memberRepository.save(member)
    }
}
