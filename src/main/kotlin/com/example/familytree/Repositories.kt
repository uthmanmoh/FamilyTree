package com.example.familytree

import com.example.familytree.entity.Member
import com.example.familytree.entity.User
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : Neo4jRepository<User, Long>

@Repository
interface MemberRepository : Neo4jRepository<Member, Long>

