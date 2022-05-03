create table if not exists employee
(
    id serial primary key,
    name varchar(50) not null ,
    surname varchar(50) not null ,
    position varchar(50) not null ,
    salary int not null ,
    grade varchar(50),
    age int not null ,
    description text
);