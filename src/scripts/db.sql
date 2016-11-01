-- source this file as a user with DB creation privileges to generate an empty database.
create database oilchange_db;

use oilchange_db;

create table Vehicle (
    id int not null auto_increment,
    make varchar(50) not null,
    model varchar(50) not null,
    year varchar (5) not null,
    primary key(id)
);

create table Oil (
    id int not null,
    type varchar(50) not null,
    brand varchar(50) not null,
    quantity int not null,
    price decimal not null,
    filterBrand varchar(50) not null,
    filterCost decimal not null,
    primary key(id),
    foreign key(id) references Vehicle(id) on delete cascade
);

create table Date (
    id int not null,
    month int(5) not null,
    day int(4) not null,
    year int not null,
    primary key(id),
    foreign key(id) references Vehicle(id) on delete cascade
);