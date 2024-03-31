package com.example.familytree.entity

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.LocalDate

@Node
class Member(
    @Id @GeneratedValue var id: Long? = null,
    var name: String,
    var birthDate: LocalDate,
    var deathDate: LocalDate? = null,
    @Relationship(type = "CHILD", direction = Relationship.Direction.OUTGOING)
    var children: List<Member> = emptyList(),
)
