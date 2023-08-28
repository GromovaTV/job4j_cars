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