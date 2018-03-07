create table employee(
id identity auto_increment primary key,
firstname varchar(30) not null,
middlename varchar(30),
lastname  varchar(30) not null,
dateOfBirth date not null,
dateOfEmployment date not null,
status boolean not null
);
