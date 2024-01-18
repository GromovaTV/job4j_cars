create table if not exists drivers(
  id serial primary key,
  name varchar(255)
);

create table if not exists engines(
  id serial primary key,
  name varchar(255)
);

create table if not exists cars(
  id serial primary key,
  name varchar(255),
  engine_id int not null unique references engines(id)
);

create table if not exists cars_drivers(
  id serial primary key,
  driver_id int not null references drivers(id),
  car_id int not null references cars(id)
);

create table if not exists brands(
  id serial primary key,
  name varchar(255)
);

create table if not exists bodies(
  id serial primary key,
  name varchar(255)
);

create table if not exists photos(
  id serial primary key,
  path text
);

create table if not exists users (
  id serial primary key,
  name varchar(200),
  email varchar(255) UNIQUE,
  password varchar(255)
);

create table if not exists ads(
  id serial primary key,
  description text,
  sold boolean,
  brand_id int not null references brands(id),
  body_id int not null references bodies(id),
  user_id int not null references users(id)
);