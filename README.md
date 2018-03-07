# ManageEmployee | A Rest Service Example

The project is an example for a REST service that exposes an Employee Resource.

### Prerequisites
The following must be installed on your machine:
* Maven
* Java

Run the following commands to ensure that you have valid versions of Java and Maven installed:
```
$ java -version
java version "1.8.0_102"
Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)

$ mvn -v
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T16:41:47+00:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 1.8.0_102, vendor: Oracle Corporation
```

## Getting Started

1. Clone Repository from Github
2. At the Project root, run the following maven commands.
```
mvn package
mvn spring-boot:run
```
3. Voila ! The server is Up and Running !

### Installing

With the above commands in the Getting Started Session, you already have a tomcat server running with a REST service exposed 
at "http://localhost:8080/employee". The service uses an embedded H2 DB, with Employee details.

You can test it by getting all employees, using the following URL:

```
http://localhost:8080/employee/1
```
__Note:__ The REST service produces and consumes only JSON data. You can test using a tool like [Postman](https://www.getpostman.com/).

The output should look similar to below. The data may look different.

```
{
    "firstName": "First",
    "middleName": "Middle",
    "lastName": "Last",
    "doB": "1990-09-17",
    "dateOfEmployment": "2004-09-01",
    "status": true,
    "id": 1
}
```


## Running the tests

The current version of the application comes with a set of [Postman](https://www.getpostman.com/). The requests are work in progress and are currently without assertions and automations.

However, the requests serve as a good starting point to check the functionality of the application.

[Postman](https://www.getpostman.com/) Requests Collection can be found at: _/src/main/resources/Employee.postman_collection.json_

### What the test requests cover

The test requests are designed to cover high level API requirements. For example, what happens when you try to get 
an employee whose id does not exist? What happens when you try to get a deleted employee?

## Built With

* [Spring Framework](https://spring.io/)
* [Maven](https://maven.apache.org/)
