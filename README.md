# Family Tree Backend

This is the backend for the Family Tree project. It lets you manage a database of people and their relationships.

It is written in Kotlin using the Spring Boot framework, and Neo4j as the database.

## Running the backend alone (if the database is already running)
```bash
gradle bootRun
```

## Running the backend with the database
Create a `.env` file in the root directory with the following variables set:
```bash
NEO4J_USERNAME=<YOUR_NEO4J_USERNAME>
NEO4J_PASSWORD=<YOUR_NEO4J_PASSWORD>
```

And then run:
```bash
docker-compose up
```

## Run the tests
```bash
gradle test
```
This will startup a Neo4j database in a docker container and run the tests.
