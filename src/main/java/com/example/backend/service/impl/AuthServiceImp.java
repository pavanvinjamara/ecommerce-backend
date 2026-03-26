package com.example.backend.service.impl;

import com.example.backend.dto.SignupRequest;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupRequest request) {

        User existingUser = userRepository.findByEmail(request.getEmail());

        if(existingUser != null) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

    }
}


/*
1️⃣ Package Declaration
package com.example.usermanagement.service.impl;

This tells Java:

This class belongs to the service implementation layer.

impl means implementation of service interfaces.

Project structure idea:

service
   AuthService.java
   impl
      AuthServiceImpl.java
2️⃣ Import Statements
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

These are Spring annotations.

@Service

Used to mark the class as a service layer bean.

@Autowired

Used for Dependency Injection.

Next imports:

import com.example.usermanagement.dto.SignupRequest;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.AuthService;

These import your project classes.

Class	Purpose
SignupRequest	DTO from frontend
User	Entity (database table)
UserRepository	Database access
AuthService	Interface
3️⃣ Service Annotation
@Service

This tells Spring:

Create an object (bean) of this class and manage it.

Spring creates:

AuthServiceImpl bean

This bean is stored inside the Spring container.

4️⃣ Class Definition
public class AuthServiceImpl implements AuthService

This means:

AuthServiceImpl implements the AuthService interface.

It must implement all methods defined in the interface.

Example interface:

public interface AuthService {
    void signup(SignupRequest request);
}

So this class must implement signup().

5️⃣ Dependency Injection
@Autowired
private UserRepository userRepository;

This is the important part.

Without Spring

Normally in Java you would write:

UserRepository userRepository = new UserRepository();

But UserRepository is an interface, so you cannot create it.

Also Spring automatically creates its implementation.

With Spring

Spring already created a repository bean.

UserRepository bean

When you write:

@Autowired
private UserRepository userRepository;

Spring injects the object automatically.

This is called Dependency Injection.

Dependency Injection Meaning

Your service depends on repository.

AuthServiceImpl
      ↓
UserRepository

Instead of creating it manually, Spring injects it.

6️⃣ Method Override
@Override
public void signup(SignupRequest request)

@Override means:

This method is implementing a method from the interface.

Method receives:

SignupRequest request

This comes from controller → frontend data.

Example JSON from React:

{
 "name":"Pavan",
 "email":"pavan@gmail.com",
 "password":"123"
}

Spring converts JSON → DTO.

7️⃣ Creating Entity Object
User user = new User();

We create a new database entity object.

Think of it like:

New row for users table
8️⃣ Setting Data
user.setName(request.getName());

Copy data from DTO → entity.

DTO        →      Entity
request.name      user.name

Next lines:

user.setEmail(request.getEmail());
user.setPassword(request.getPassword());

Same process.

9️⃣ Save to Database
userRepository.save(user);

This method comes from:

JpaRepository

Internally it does:

Hibernate ORM
      ↓
SQL Query
      ↓
INSERT INTO users

Equivalent SQL:

INSERT INTO users(name,email,password)
VALUES('Pavan','pavan@gmail.com','123');
🔟 Full Flow
React Signup Form
        ↓
Controller
        ↓
AuthService.signup()
        ↓
AuthServiceImpl
        ↓
UserRepository.save()
        ↓
Hibernate
        ↓
Database
1️⃣1️⃣ Why @Autowired Is Needed

Because Spring must inject the repository bean.

Without it:

userRepository = null

Calling:

userRepository.save()

Would cause:

NullPointerException
1️⃣2️⃣ Modern Way (Better Practice)

Instead of @Autowired, we use constructor injection.

Example:

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

}

Spring injects repository automatically.

This is recommended in real projects.

✅ You now understood:

@Service

@Autowired

Dependency Injection

DTO → Entity conversion

Repository save

If you want, the next step is Controller, which connects React frontend → Spring Boot API. This is where your signup endpoint is created.
*/


/*
Step 1: - Create a interface AuthService

Step 2: - implemet the signup method

Step 3:- Create a package which implements this interface name AuthImplements

Step 4:- Create a class SignupImplementAuth

Step 5:- Implement with AuthService and mention it with @Service which creates service bean

Step 6:- Get userRepository from Repository store in private variable
         userRepository object, we are injecting into the AuthAerviceImp1

Step 7:- implement the interface methods which is signup
Step 8:- Create a user instance using User Entity
Step 9:- save it in database using userRepository.save
*/