# Getting Started
just run:
docker-compose up

GET localhost:8081/greeting
POST localhost:8081/users
{
"userName": "some-username docker plus"
}
GET http://localhost:8081/users/1
PUT http://localhost:8081/users/1
{
"userName": "new-username docker2"
}
PUT http://localhost:8081/users/1/1
GET localhost:8082/greeting
POST localhost:8082/posts
{
"authorId": 1,
"text": "Hi friends, I recently watched amazing movie at the cinema01"
}
GET localhost:8082/posts/1
PUT localhost:8082/posts/1
{
"text": "Hi friends, I recently watched amazing movie at the cinema06b"
}
DELETE localhost:8082/posts/1



###
for info:
docker build -t igorpavlyushchik/posts-application:1.0.0 ./
docker push igorpavlyushchik/posts-application:1.0.0


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

