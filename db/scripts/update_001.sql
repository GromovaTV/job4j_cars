create table drivers(
  id serial primary key,
  name varchar(255)
);

create table engines(
  id serial primary key,
  name varchar(255)
);

create table cars(
  id serial primary key,
  name varchar(255),
  engine_id int not unique references engine(id)
);

create table cars_drivers(
  id serial primary key,
  driver_id int not null references driver(id),
  car_id int not null references car(id)
);

create table brands(
  id serial primary key,
  name varchar(255)
);

create table bodies(
  id serial primary key,
  name varchar(255)
);

create table photos(
  id serial primary key,
  path text
);

create table users (
  id serial primary key,
  name varchar(2000),
  email TEXT UNIQUE,
  password TEXT
);

create table ads(
  id serial primary key,
  description text,
  sold boolean,
  brand_id int not null references brands(id),
  body_id int not null references bodies(id),
  user_id int not null references users(id)
);