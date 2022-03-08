# NewspaperSbApp

This is a backend application developed with **Spring Boot**.
Exposes a relationship between 2 entities: **Post** and **Comment**.
These entities keep a **ONE-TO-MANY** relationship.

## Environment

**OS** CentOS 8.4  
**Framework** Spring Boot 2.6.4  
**Database** MySQL 8.0.26  
**Package Manager** Maven 3.8.4  
**OpenJDK** 1.8.0 312  

## Deployment

1. Create the **newspaper** database
```
mysql> CREATE DATABASE newspaper;
```
<p>
There is no need to create any table because the **application.properties** file is set to follow a **Code First** approach.  
</p>

2. Open the database connection file **src/main/resources/application.properties** 

3. Update with your own credentials (user/password for your local MySQL) and other fields if necessary
<pre>
spring.datasource.username=<b>YOUR.USER</b>
spring.datasource.password=<b>YOUR.PASSWORD</b>
</pre>

4. Clone this repo locally
```
$ git clone https://github.com/ducoingviri/NewspaperSbApp.git
$ cd NewspaperSbApp/
```

5. Remove previous compilation and compile again
```
$ mvn clean && mvn compile
```
6. If no errors, create the database from the project code
```
$ mvn install
```
7. If no errors, run the backend application
```
$ mvn spring-boot:run
```

## Endpoints

To display the JSON outputs in a readable way, install the following module (by using **npm**):
<pre>
# npm i -g json
</pre>
Then, append the **| json** stetement piece to every cURL call like I do it in the following instructions. 

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
