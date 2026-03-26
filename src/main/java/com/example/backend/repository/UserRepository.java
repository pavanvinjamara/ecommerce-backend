package com.example.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}


/*
The repository layer is responsible for communicating with the database.
Spring Boot uses Spring Data JPA to automatically generate database queries.

Step 1:- create package repository

Step 2:- create interface UserRepository
            why interface only ? why not class ?
            Interface allows Spring to:
            generate implementation automatically
            add database logic dynamically
            keep code clean
            Spring Data repositories are defined as interfaces because Spring automatically generates their implementation at runtime using proxy classes.

Step 3:- extend with an interface JpaRepository
         which accepts the Two thing  JpaRepository<EntityClass, PrimaryKeyType>
         3️⃣ What Methods It Provides
         By extending JpaRepository, you automatically get many methods.
         Save Data
         userRepository.save(user);

         SQL executed internally:
         INSERT INTO users (...)

         JpaRepository is an interface provided by Spring Data JPA that gives you ready-made database operations for your entity.
*/



/*
Step 2️⃣ Create Repository

The repository layer is responsible for communicating with the database.

Spring Boot uses Spring Data JPA to automatically generate database queries.

Create Repository Interface

Folder:

repository/

File:

UserRepository.java

Code:

package com.example.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.usermanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
What This Line Means
extends JpaRepository<User, Long>
Part	Meaning
User	Entity class
Long	Primary key type

So Spring automatically gives you methods like:

save()
findById()
findAll()
deleteById()

Example usage later in service:

userRepository.save(user);
Folder Structure Now
src/main/java/com/example/usermanagement

entity
   User.java

repository
   UserRepository.java
What Happens Internally

When Spring Boot starts:

UserRepository
      ↓
Spring Data JPA
      ↓
Hibernate
      ↓
Database

Spring automatically creates the implementation.

You do NOT write SQL manually.

✅ Your task now

Create:

repository/UserRepository.java

with:

public interface UserRepository extends JpaRepository<User, Long> {

}
*/