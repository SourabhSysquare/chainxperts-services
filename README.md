# chainxperts-services

##### Submission project for Formidium Hackathon 2024

## Tech Stack Used
* Java 11
* Spring Boot
* Spring Security (JWT Based Authentication and Authorization)
* Spring Data JPA/Hibernate
* Postgresql
* Lombok


## Entities
##### SQL Migration Scripts can be viewed at src/main/resources/db/migration
##### Read in detail about entities used in ENTITIES.md in project root.
![ER Diagram of DB Entities](/home/sysquare/hackthon/fin-manager/src/main/resources/static/119250906-e7159b80-bbc0-11eb-930d-944714b986f6.jpeg)

## Security Flow
* On Successful validation of login credentials, a JWT will be returned representing the user 
* decode the below sample JWT on jwt.io for reference

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb3VyYWJoMUBnbWFpbC5jb20iLCJhY2NvdW50X2NyZWF0aW9uX3RpbWVzdGFtcCI6IjIwMjQtMDYtMjJUMTE6MDQ6MjIuMDQxIiwidXNlcl9pZCI6MSwidG90YWxfYmFsYW5jZV9pZCI6MSwic2NvcGUiOiJ1c2VyIiwibmFtZSI6IlJlYmVjY2EgR3JlZW4iLCJleHAiOjE3MTkwOTc0NzksImlhdCI6MTcxOTA2MTQ3OX0.e0rMVJKqa9vObAX00jG63ejcbV2QJGeV2Lzl9aA9wsE
```
* The received JWT should be included in the headers when calling a protected API
* Authentication Bearer format to be used **(Header key should be 'Authentication' and value should start with Bearer followed with a single blank space and recieved JWT)**

```
Authentication : Bearer <JWT>
```

## Setup Locally
* Install Java 11
* Install Maven
* Install PostgreSQL

Recommended way is to use [sdkman](https://sdkman.io/) for installing both maven and java

Create postgres user (superuser) with name and password as Pass#123

```
CREATE USER sa WITH PASSWORD 'Pass#123' SUPERUSER;
```

Create Database with name 'cx_db' and assign the above created user to the database with preferable CLI or GUI tool

```
create database cx_db;
```
```
grant all privileges on database auction to auction;
```

Run the below commands in the core

```
mvn clean
```

```
mvn install
```

Execute any of the two commands below to run the application

```
java -jar target/url-shortner-h2-db-0.0.1-SNAPSHOT.jar
```

```
mvn spring-boot:run
```

The Default port is 8081 and base-url is set to /fin-manager (both can be changed in application.properties)

## Setup With Docker
* Install Java 15
* Install Maven

Recommended way is to use [sdkman](https://sdkman.io/) for installing both maven and java

Run mvn clean install in the core

```
mvn clean install
```

Run docker commands

```
sudo docker-compose build
sudo docker-compose up -d
```
Service port is 9090 and Postgres Port is 6432. They both can be changed in the [docker-compose.yml](docker-compose.yml) file


To View Logs

```
docker-compose logs -f service
```

To stop the container run

```
sudo docker-compose stop
```

