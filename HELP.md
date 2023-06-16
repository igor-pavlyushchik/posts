# Getting Started
To run locally with k8s:
from k8s folder run to start/delete:
kubectl apply -f manifest.yaml
kubectl delete -f manifest.yaml

to check:
kubectl get all -n=k8s-program
kubectl describe service postsapp -n=k8s-program

to test:

GET localhost:32315/greeting
POST localhost:32315/users
{
"userName": "some-username k8s 4"
}
GET http://localhost:32315/users/1
PUT http://localhost:32315/users/1
{
"userName": "new-username k8s2"
}
PUT http://localhost:32315/users/1/1
GET localhost:32316/greeting
POST localhost:32316/posts
{
"authorId": 1,
"text": "Hi friends, I recently watched amazing movie at the cinema01",
"topic": "Movie topic"
}
GET localhost:32316/posts/1
PUT localhost:32316/posts/1
{
"text": "Hi friends, I recently watched amazing movie at the cinema06b"
}
DELETE localhost:32316/posts/1

Notes: StatefulSets using Secrets for db creds. Probes for users and posts dbs implemented differently.
Users db initiated with script and spring.jpa.hibernate.ddl-auto=validate. Posts db initiated with JPA and
spring.jpa.hibernate.ddl-auto=update.

# Module 3, Subtask 3:
new docker image for posts application:
docker build -t igorpavlyushchik/posts-application:2.0.0 ./
docker push igorpavlyushchik/posts-application:2.0.0

# Module 3, Subtask 4:
Sub-task 4: Deployment history
As you deployed a new version of your application, you can see the history of your deployments. Your task is to roll back to previous version of your deployment without changing your manifest files.
Put in comments the solution of this task:

kubectl rollout history deployment.apps/postsapp-pod -n=k8s-program
kubectl rollout history deployment.apps/postsapp-pod --revision=1 -n=k8s-program
kubectl rollout undo deployment.apps/postsapp-pod -n=k8s-program
kubectl get all -n=k8s-program


###
for info:
docker build -t igorpavlyushchik/posts-application:1.0.0 ./
docker push igorpavlyushchik/posts-application:1.0.0

# Generate secret values:
~ $ echo -n 'users' | base64
dXNlcnM=
~ $ echo -n 'admin' | base64
YWRtaW4=
~ $ echo -n 'admin1234' | base64
YWRtaW4xMjM0

k8s $ echo -n 'posts' | base64
cG9zdHM=

### Module 3 Sub-task 1: Secrets and config-maps
Add sql scripts to init databases (create tables) to config maps - not added as tables generated with JPA

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

