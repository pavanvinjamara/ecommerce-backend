package com.example.backend.service;

import com.example.backend.dto.SignupRequest;

public interface AuthService {
    void signup(SignupRequest request);
}
/*
Step 4️⃣ Create Service Interface

Service layer contains business logic like:

signup user

login user

validation

password hashing

First we create a service interface.

Create Folder
service
Create File
AuthService.java
Code
package com.example.usermanagement.service;

import com.example.usermanagement.dto.SignupRequest;

public interface AuthService {

    void signup(SignupRequest request);

}
What This Means

We are defining a contract:

signup(SignupRequest request)

Later we will implement it.

Folder Structure Now
entity
   User.java

repository
   UserRepository.java

dto
   SignupRequest.java

service
   AuthService.java

✅ Your task

Create:

service/AuthService.java

After that tell me, then I will give the next step (Service Implementation).
*/