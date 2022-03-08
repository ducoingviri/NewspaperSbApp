# NewspaperSbApp

This is a backend application developed with Spring Boot.
Exposes a relationship between 2 entities: Post and Comment.
These entities keep a one-to-many relationship.

## Environment

**Framework** Spring Boot 2.6.4<br>
**Database** MySQL 8.0.26<br>
**Package Manager** Maven 3.8.4<br>
**OpenJDK** 1.8.0 312<br>

## Endpoints

### POST

**[store]**<br>
curl -s -X POST -H "Content-Type: application/json" -d '{"author": "Ima String", "title": "Ima String", "brief": "Ima String", "content": "Ima String", "createdat": "2022-02-21", "ispublished": true}' http://localhost:8080/api/post | json
<br>
**[update]**<br>
curl -s -X PUT -H "Content-Type: application/json" -d '{"author": "Ima String", "title": "Ima String", "brief": "Ima String", "content": "Ima String", "createdat": "2022-02-21", "ispublished": true}' http://localhost:8080/api/post/1 | json
<br>
**[index]**<br>
curl -s http://localhost:8080/api/post | json
<br>
**[show]**<br>
curl -s http://localhost:8080/api/post/1 | json
<br>
**[destroy]**<br>
curl -X DELETE http://localhost:8080/api/post/1

### COMMENT

**[store]**<br>
curl -s -X POST -H "Content-Type: application/json" -d '{"email": "Ima String", "content": "Ima String", "createdat": "2022-02-21"}' http://localhost:8080/api/comment/1 | json
<br>
**[update]**<br>
curl -s -X PUT -H "Content-Type: application/json" -d '{"email": "Ima String", "content": "Ima String", "createdat": "2022-02-21", "post": {"id": 1}}' http://localhost:8080/api/comment/1 | json
<br>
**[index]**<br>
curl -s http://localhost:8080/api/comment | json
<br>
**[show]**<br>
curl -s http://localhost:8080/api/comment/1 | json
<br>
**[destroy]**<br>
curl -X DELETE http://localhost:8080/api/comment/1
