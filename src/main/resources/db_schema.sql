//run below query for all tables in db:
show create table table_name;

//sample create table statement for a students table:
CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    email VARCHAR(100)
);