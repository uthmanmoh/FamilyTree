package com.example.familytree.service

import com.example.familytree.MemberRepository
import com.example.familytree.entity.Member
import com.example.familytree.exceptions.DataNotFoundException
import com.example.familytree.exceptions.InvalidDataException
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun getAllMembers() = memberRepository.findAll()

    fun addMember(member: Member): Member {
        validateMember(member)
        return memberRepository.save(member)
    }

    private fun validateMember(member: Member) {
        if (member.name.isBlank()) {
            throw InvalidDataException("Name cannot be blank")
        }
        if (member.deathDate != null && member.birthDate.isAfter(member.deathDate)) {
            throw InvalidDataException("Birth date cannot be after death date")
        }
    }

    fun deleteMember(id: Long) {
        if (!memberRepository.existsById(id)) {
            throw DataNotFoundException("Member with id $id not found")
        }
        memberRepository.deleteById(id)
    }
}
