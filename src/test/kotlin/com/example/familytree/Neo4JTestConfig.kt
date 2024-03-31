package com.example.familytree

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.Neo4jContainer
import org.testcontainers.lifecycle.Startables
import org.testcontainers.utility.DockerImageName
import java.time.Duration

class Neo4jTestConfig : ApplicationContextInitializer<ConfigurableApplicationContext> {
    private val neo4jContainer: Neo4jContainer<*> =
        Neo4jContainer(DockerImageName.parse("neo4j:5"))
            .withRandomPassword()
            .withStartupTimeout(Duration.ofMinutes(2))

    init {
        // we can start all the containers here, we have only one in this example
        Startables.deepStart(neo4jContainer).join()
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        println("Initializing Neo4J container for test on: ${neo4jContainer.boltUrl}")
        TestPropertyValues.of(
            "spring.neo4j.uri=${neo4jContainer.boltUrl}",
            "spring.neo4j.authentication.username=neo4j",
            "spring.neo4j.authentication.password=${neo4jContainer.adminPassword}",
        ).applyTo(applicationContext.environment)
    }
}

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = [Neo4jTestConfig::class])
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
annotation class Neo4jIntegrationTest
