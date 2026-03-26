package com.example.backend.dto;

public class SignupRequest {
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


/*
Step 3️⃣ Create DTO (Data Transfer Object)

DTO is used to receive data from the frontend and send data to the frontend.

We do not use Entity directly in API requests.

Why DTO Is Needed

Suppose your entity is:

@Entity
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

}


But when React sends signup request, it only sends:

{
  "name": "Pavan",
  "email": "pavan@gmail.com",
  "password": "123456"
}


So we create a DTO to receive this data.

Create DTO Folder
dto

Create Signup DTO

File:

SignupRequest.java


Code:

package com.example.usermanagement.dto;

public class SignupRequest {

    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

Folder Structure Now
entity
   User.java

repository
   UserRepository.java

dto
   SignupRequest.java

What DTO Does

Flow:

React Signup Form
        ↓
SignupRequest DTO
        ↓
Controller
        ↓
User Entity
        ↓
Database


DTO acts as a middle layer between frontend and entity.

Your Task Now

Create:

dto/SignupRequest.java


with fields:

name
email
password


After you finish, tell me and I will give the next step: Service Layer.

why dto entity and this are same

Good question 👍 This confusion happens to almost everyone learning Spring Boot.

You noticed:

DTO and Entity have the same fields. Why do we need DTO?

Right now they look the same, but their purpose is different.

1️⃣ Entity Purpose

Entity represents the database table.

Example:

@Entity
public class User {

    @Id
    private Long id;

    private String name;
    private String email;
    private String password;

}


Entity is used for:

database mapping

ORM (Hibernate)

persistence

It directly corresponds to:

users table
-------------
id
name
email
password

2️⃣ DTO Purpose

DTO = Data Transfer Object

It is used to transfer data between frontend and backend.

Example:

public class SignupRequest {

    private String name;
    private String email;
    private String password;

}


This class is only used to receive data from React.

3️⃣ Why Not Use Entity Directly?

There are 3 important reasons.

1️⃣ Security

Suppose entity has extra fields:

@Entity
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private boolean isAdmin;

}


If you expose entity directly, frontend could send:

{
 "name":"Pavan",
 "email":"pavan@gmail.com",
 "password":"123",
 "role":"ADMIN"
}


User could make themselves admin 😬

DTO prevents this.

DTO:

public class SignupRequest {

    private String name;
    private String email;
    private String password;

}


No role field → cannot manipulate.

2️⃣ Hide Sensitive Data

Example:

Entity:

private String password;


When returning response to frontend you should not send password.

DTO for response:

public class UserResponse {

    private Long id;
    private String name;
    private String email;

}


Password hidden.

3️⃣ Flexibility

Sometimes frontend needs different structure than database.

Example:

Database:

first_name
last_name


Frontend wants:

fullName


DTO allows transformation.

4️⃣ Real Backend Flow
React Request
      ↓
DTO
      ↓
Controller
      ↓
Entity
      ↓
Database

5️⃣ Example Conversion

Controller:

@PostMapping("/signup")
public void signup(@RequestBody SignupRequest dto) {

    User user = new User();

    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());

    userRepository.save(user);
}


DTO → Entity conversion.

6️⃣ In Small Projects

DTO and Entity may look the same.

But in real projects they become very different.

Example:

Entity fields: 15
DTO fields: 5

7️⃣ Industry Rule

Never expose entities directly in APIs.

Always use:

DTO

Interview Answer

If asked:

Why use DTO instead of entity in controller?

Answer:

DTO separates API data from database entities, improving security, flexibility, and maintainability.
*/



/*
DTO Purpose
DTO = Data Transfer Object
DTO is used to transfer data between layers of an application, especially between frontend and backend.

Step 1 :- Create a package DTO

Step 2 :- Create a class SignupRequest
              why it is class ? why not interface ?
              DTO works on data which can store so that's we use class
              interface we cannot do that

Step 3 :- create the variable which we can receive from front and made every
          variable private and variable need to have getter and setter.


*/