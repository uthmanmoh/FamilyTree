package com.example.familytree.entity

import org.springframework.data.annotation.Id
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class User(
    @Id @GeneratedValue var id: Long? = null,
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var fullName: String? = null,
    @Relationship(type = "CONNECTS_TO", direction = Relationship.Direction.OUTGOING)
    var connectedMembers: Set<Member> = emptySet(),
)

