package com.example.backend.entity;
//This class represents the users table in the database.
import jakarta.persistence.*;
//This imports JPA annotations used for database mapping.
//JPA = Java Persistence API
//It allows Java objects to map to database tables.
//Persistence means storing data permanently so it still exists after the application stops.

//This tells Spring Boot
//        This class represents a database table.
//Without @Entity , Spring Boot will treat it as a normal Java class, not a databasetable.
@Entity
//    Make Table for user using @Table annotation
//    inside parentheses we give the name of the table
@Table(name = "users")
public class User {

//    @Id marks the primary key of the table.
//    Primary key = unique identifier.
    @Id
//    This tells the database:
//    Automatically generate the ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
1️⃣ Package
package com.example.usermanagement.entity;

This tells Java that the class belongs to the entity folder/package.

Project structure example:

src/main/java/com/example/usermanagement
    └── entity
          └── User.java
2️⃣ Import JPA Annotations
import jakarta.persistence.*;

This imports JPA annotations used for database mapping.

JPA = Java Persistence API

It allows Java objects to map to database tables.

3️⃣ @Entity
@Entity

This tells Spring Boot:

This class represents a database table.

Without @Entity, Spring Boot will treat it as a normal Java class, not a database table.

So this:

@Entity
public class User

means:

User class  →  users table
4️⃣ @Table(name = "users")
@Table(name = "users")

This defines the table name in the database.

If you do not specify this:

@Entity
public class User

Spring Boot will automatically create table:

user

But we explicitly say:

@Table(name = "users")

So database table becomes:

users
5️⃣ Class Definition
public class User

This class represents one row in the table.

Example row:

id | name   | email             | password
------------------------------------------------
1  | John   | john@gmail.com    | 123456
6️⃣ @Id
@Id

This marks the primary key of the table.

Primary key = unique identifier.

Example:

id
1
2
3

Each user has a unique ID.

7️⃣ @GeneratedValue
@GeneratedValue(strategy = GenerationType.IDENTITY)

This tells the database:

Automatically generate the ID.

Example:

INSERT user

Database automatically creates:

id = 1
id = 2
id = 3

You don't need to manually assign the ID.

8️⃣ Fields (Columns)
private Long id;
private String name;
private String email;
private String password;

These become database columns.

Table created by Hibernate:

users
--------------------------------
id        BIGINT
name      VARCHAR
email     VARCHAR
password  VARCHAR
9️⃣ Complete Mapping

Java class → Database table

User.java
@Entity
@Table(name="users")

Fields:
id
name
email
password

Database table:

users
--------------------------------
id        BIGINT  (PRIMARY KEY)
name      VARCHAR
email     VARCHAR
password  VARCHAR
🔟 Very Important (Missing in Your Code)

You must also add getters and setters.

Example:

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

Why?

Because Spring Boot uses them to read/write values.

Final Entity Code
package com.example.usermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
Visual Understanding
React Signup Form
      │
      ▼
Backend receives data
      │
      ▼
User Entity Object Created
      │
      ▼
Saved to Database

Example:

{
 "name": "Pavan",
 "email": "pavan@gmail.com",
 "password": "123456"
}

Becomes:

User object → saved into users table

If you want, I can also explain 3 extremely important entity annotations used in real projects:

@Column

@OneToMany

@ManyToOne

These are very common Spring Boot interview questions.

why id has getter and setter it primary key

Good question 👍 Many beginners think “id is primary key, so why do we need getter and setter?”.
Let’s understand clearly.

1️⃣ What id Represents
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

This means:

id is primary key

Database automatically generates it

Example table:

id | name   | email
-------------------------
1  | Pavan  | pavan@gmail.com
2  | John   | john@gmail.com

You do not manually set id when creating a user.

2️⃣ Why Getter is Needed

Even though the database generates the id, your application still needs to read it.

Example:

User user = userRepository.save(newUser);

System.out.println(user.getId());

After saving:

id = 1

Spring/Hibernate reads the id from database and sets it in the object, so you can access it.

Without getId() you cannot read the id.

3️⃣ Why Setter is Sometimes Needed

Even though the database generates id, frameworks like Hibernate/JPA need setter internally.

Example flow:

Database → Hibernate → Java Object

Hibernate reads row:

id = 1
name = Pavan

Then it internally does something like:

user.setId(1);
user.setName("Pavan");

So setter helps Hibernate populate the object.

4️⃣ What Happens During Save

Example:

User user = new User();
user.setName("Pavan");
user.setEmail("pavan@gmail.com");

userRepository.save(user);

Steps:

1️⃣ user object created
2️⃣ Hibernate sends INSERT query
3️⃣ Database generates id
4️⃣ Hibernate receives generated id
5️⃣ Hibernate calls user.setId(id)
6️⃣ user object now has id

So your object becomes:

User {
 id = 1
 name = Pavan
 email = pavan@gmail.com
}
5️⃣ Without Getter/Setter

If you remove them:

private Long id;

Problems:

Hibernate may fail mapping

Framework cannot access the field

Serialization issues (JSON response)

6️⃣ Another Example (API Response)

Controller:

@PostMapping("/signup")
public User signup(@RequestBody User user){
    return userRepository.save(user);
}

Response:

{
 "id": 1,
 "name": "Pavan",
 "email": "pavan@gmail.com"
}

Here getter is used to convert object → JSON.

7️⃣ Simple Rule

Even for primary keys, always create:

getter
setter

Because:

Reason	Explanation
Hibernate	sets id internally
Serialization	converts object to JSON
Access	allows reading id
8️⃣ Real Projects (Cleaner Approach)

Many developers use Lombok to avoid writing getters/setters.

Example:

import lombok.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
}

@Data automatically creates:

getters

setters

toString

equals

hashCode

✅ Interview Tip

If asked:

Why do we need getters/setters in entity?

Answer:

Getters and setters allow Hibernate to populate entity fields and enable frameworks like Jackson to serialize objects into JSON responses.

If you want, next I can explain 3 very important annotations you will use in almost every Spring Boot entity:

@Column
@OneToMany
@ManyToOne

These are extremely common in real projects and interviews.

@Column

@OneToMany

@ManyToOne

These three annotations are very important in Spring Boot JPA. They define how your Java objects map to database columns and relationships between tables.

Let’s understand them one by one with simple examples.

1️⃣ @Column

@Column is used to customize a database column.

Basic example:

@Column(name = "email", unique = true, nullable = false)
private String email;

This tells Hibernate:

Property	Meaning
name	column name in DB
unique	value must be unique
nullable	cannot be NULL

Example entity:

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
}

Database table:

users
--------------------------------
id        BIGINT  PRIMARY KEY
name      VARCHAR NOT NULL
email     VARCHAR UNIQUE
password  VARCHAR

When to use @Column:

enforce unique email

prevent null values

change column names

specify length

Example:

@Column(length = 100)
private String name;
2️⃣ @OneToMany

@OneToMany defines a one-to-many relationship.

Meaning:

One record in table A can have many records in table B.

Example:

One User → Many Posts

User
-----
id
name

Post
-----
id
title
user_id

A user can create many posts.

User entity:

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}

Post entity:

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

Database structure:

users
-----
id
name

posts
-----
id
title
user_id

Example data:

users
1  Pavan

posts
1  Spring Boot Guide  user_id=1
2  React Tutorial     user_id=1

One user → many posts.

3️⃣ @ManyToOne

@ManyToOne means:

Many records belong to one parent record.

Example again:

Many posts belong to one user.

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

Meaning:

Post → belongs to → User

@JoinColumn(name="user_id") creates the foreign key.

Database:

posts
------------------
id
title
user_id  (foreign key)
4️⃣ Relationship Visualization
User
  |
  |  OneToMany
  |
  ▼
Post

Post
  |
  |  ManyToOne
  |
  ▼
User
5️⃣ Simple Real-World Examples
Relationship	Example
OneToMany	User → Orders
OneToMany	Blog → Comments
ManyToOne	Order → Customer
ManyToOne	Comment → Post
6️⃣ Most Common JPA Relationships
Annotation	Meaning
@OneToOne	one user → one profile
@OneToMany	one user → many posts
@ManyToOne	many posts → one user
@ManyToMany	students ↔ courses
7️⃣ Interview Tip

Very common question:

What is the difference between @OneToMany and @ManyToOne?

Answer:

@OneToMany means one parent has multiple children.

@ManyToOne means multiple children belong to one parent.

The foreign key is stored on the @ManyToOne side.

Example:

posts table contains user_id

So Post has @ManyToOne.

If you want, I can also explain 4 annotations every Spring Boot developer must know for entities:

@JoinColumn
@Enumerated
@CreationTimestamp
@UpdateTimestamp

These are used in almost every real production backend.
*/


/*
This class represents the users table in the database.

Step 1 :- import jakarta.persistence.*;
          //This imports JPA annotations used for database mapping.
          //JPA = Java Persistence API
          //It allows Java objects to map to database tables.
          //Persistence means storing data permanently so it still exists after the application stops.

Step 2 :- Make class User
            why it is class ? why not interface
            JPA entities must be concrete classes because Hibernate needs to instantiate them and map fields to database columns. Interfaces cannot hold persistent state.
            In JPA (Spring Boot), entities must be classes, not interfaces.
            Reason:
                JPA/Hibernate needs to:
                create objects
                store data in fields
                map fields to database columns
                instantiate objects using new
                An interface cannot store data.

Step 3 :- Note top of class with @Entity Annotation.
          Why?
          This tells Spring Boot
                  This class represents a database table.
          Without @Entity , Spring Boot will treat it as a normal Java class, not a databasetable.

Step 4 :- Declare below Entity annotation @Table
          Make Table for user using @Table annotation
          inside parentheses we give the name of the table
          @Table(name = "users")

Step 5 :- We make private variable id with type long
          Above the a variable declaration
          We declare @Id
              This marks the primary key of the table.
              Primary key = unique identifier.
          We declare @GeneratedValue(strategy = GenerationType.IDENTITY)
              This tells the database:
              Automatically generate the ID.

Step 6:- Declare all required properties which consider as columns

            Note:- Use private before every variable, so it cannot change externally.
                   Make Getter and Setter for every variable so we can change use them.
*/