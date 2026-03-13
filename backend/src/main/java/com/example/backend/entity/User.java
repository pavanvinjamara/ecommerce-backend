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
