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

1. Create the **newspaper** database at MySQL CLI
```
CREATE DATABASE newspaper;
```
<p>
There is no need to create any table because the <b>application.properties</b> file is set to follow a <b>Code First</b> approach.  
</p>

2. Open the database integration file **src/main/resources/application.properties** 

3. Update with your own credentials (user/password for your local MySQL) and other fields if necessary
<pre>
spring.datasource.username=<b>USER</b>
spring.datasource.password=<b>PASSWORD</b>
</pre>

4. Clone this repo locally
```
git clone https://github.com/ducoingviri/NewspaperSbApp.git
cd NewspaperSbApp/
```

5. Remove previous compilation and compile again
```
mvn clean && mvn compile
```
6. If no errors, create the database from the project code
```
mvn install
```
7. If no errors, run the backend application
```
mvn spring-boot:run
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

## Frontend Integration

Set Cors Registry at controller level to enable global backend consumption from a frontend application. For example, at PostController, we must add the following @CrossOrigin annotation line to enable consumption.

<pre>
...
@CrossOrigin(origins = "http://<b>FRONT.APP.IP</b>:<b>FRONT.APP.PORT</b>", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {
...
</pre>

## Enable New Endpoints

Due to our repository interface extends JpaRepository, we can add customized endpoints using an specific syntax. For example, for adding an endpoint to retrieve all the posts filtering by _author_ field, we must declare a method in our repository like the following one.

<pre>
List&lt;Post> findByAuthor(String value);
</pre>

Likewise, if we would like to add an endpoint that retrieve all the posts filtering by _createdat_ field, we must declare a method in our repository like the following one.

<pre>
List&lt;Post> findByCreatedat(Date value);
</pre>

Finally, we should implement the action in our controller. For example, for the _findByAuthor_ repository method, we can implement an action controller like the following one.

<pre>
@GetMapping("/find-by-author")
public ResponseEntity&lt;List&lt;Post>> findByAuthor(@RequestParam String value) {
    try {
        List&lt;Post> items = new ArrayList&lt;Post>();
        repository.findByAuthor(value).forEach(items::add);
        if (items.isEmpty()) {
            return new ResponseEntity&lt;>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity&lt;>(items, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity&lt;>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
</pre>

For more information about how to declare repository methods for specific needs, visit 
<a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details">Defining Query Methods</a>.

