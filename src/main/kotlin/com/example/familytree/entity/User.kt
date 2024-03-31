package com.example.familytree.entity

import org.springframework.data.annotation.Id
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class User(
    @Id @GeneratedValue var id: Long? = null,
    var username: String,
    var password: String,
    var email: String,
    var fullName: String,
    @Relationship(type = "CONNECTS_TO", direction = Relationship.Direction.OUTGOING)
    var connectedMembers: Set<Member> = emptySet(),
)
