create table driver(
  id serial primary key,
  name varchar(255),
);

create table engine(
  id serial primary key,
  name varchar(255),
);

create table car(
  id serial primary key,
  ame varchar(255),
  engine_id int not unique references engine(id)
);

create table history_owner(
  id serial primary key,
  driver_id int not references driver(id),
  car_id int not references car(id)
);