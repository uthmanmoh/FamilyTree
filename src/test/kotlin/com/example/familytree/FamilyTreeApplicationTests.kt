package com.example.familytree

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.neo4j.driver.Driver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Neo4jIntegrationTest
class FamilyTreeApplicationTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun contextLoads() {
    }

    @Autowired
    private lateinit var driver: Driver

    @BeforeEach
    fun setUp() {
        driver.session().use { session ->
            session.writeTransaction { tx ->
                // Clear existing data
                tx.run("MATCH (n) DETACH DELETE n").consume()

                // Create members
                tx.run(
                    """
                    CREATE (:Member {name: 'Alice Smith', birthDate: date('1975-05-15')}),
                           (:Member {name: 'Bob Smith', birthDate: date('1978-08-22')}),
                           (parent:Member {name: 'Carol Jones', birthDate: date('1950-02-10')}),
                           (child1:Member {name: 'Dave Jones', birthDate: date('1972-07-01')}),
                           (child2:Member {name: 'Eve Jones', birthDate: date('1974-12-24')})
                    MERGE (parent)-[:CHILD]->(child1)
                    MERGE (parent)-[:CHILD]->(child2)
                    """.trimIndent(),
                ).consume()
            }
        }
    }

    @Test
    fun `should return all members`() {
        mockMvc.perform(get("/members"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(5))
    }
}
