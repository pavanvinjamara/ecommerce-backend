package com.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
/*
Step 7️⃣ Password Hashing

Right now your code stores password like this:

user.setPassword(request.getPassword());

This stores password as plain text, which is very dangerous ❌.

Database example:

id | name | email              | password
1  | Pavan| pavan@gmail.com    | 123456

If database is hacked → all passwords exposed.

Solution: Password Hashing

We store encrypted (hashed) password.

Example stored in DB:

$2a$10$QWERTYUIOPasdfghjklzxcvbn

Even developers cannot see the real password.

Spring uses:

BCryptPasswordEncoder
Step 1: Add Dependency (if using Spring Security)

In pom.xml:

<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-security</artifactId>
</dependency>
Step 2: Create Password Encoder Bean

Create file:

config/SecurityConfig.java

Code:

package com.example.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

Now Spring creates a password encoder bean.

Step 3: Inject Encoder in Service

Update your service:

@Autowired
private BCryptPasswordEncoder passwordEncoder;
Step 4: Encrypt Password Before Saving

Change this line:

❌ Old

user.setPassword(request.getPassword());

✅ New

user.setPassword(passwordEncoder.encode(request.getPassword()));
Final Service Code
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }
}
Flow Now
React Signup
     ↓
Controller
     ↓
Service
     ↓
Password hashed
     ↓
Repository
     ↓
Database
What Database Stores

Example:

id | email            | password
1  | pavan@gmail.com  | $2a$10$fjdkasjfhkjahfkjahf

Not real password.

Interview Tip ⭐

If asked:

How do you store passwords securely in Spring Boot?

Answer:

Passwords are hashed using BCryptPasswordEncoder before saving to the database.
*/

/*
Step 1:- Create a class SecurityConfig
         where we can do hashing using SpringSecurity in that we use BCryptPasswordEncoder
         above the class give annotation @Configuration

Step 2:- Create a passwordEncoder method using BCryptPasswordEncoder
         which returns instance of BCryptPasswordEncoder
         above that give @Bean annotation

Step 3:- Using this in service

@Bean
public BCryptPasswordEncoder passwordEncoder()

Spring creates a singleton bean for password encryption.

Internally Spring does:

BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

And stores it in the Spring container.
*/