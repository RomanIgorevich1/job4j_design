create table devices (
	id serial primary key,
	name text,
	price float
);
create table people (
	id serial primary key,
	name text
);
create table device_people (
	id serial primary key,
	device_id int references devices(id),
	people_id int references people(id) 
);
insert into people(name) values ('Roman'), ('Igor'), ('Elena'), ('Olya');
insert into devices(name, price) 
values ('Iphone 13', 8000.50), ('Iphone 14', 10000.50),
('Samsung S21', 7000.50), ('Samsung S22', 9500.50);
insert into device_people(device_id, people_id) values (1, 1), (2, 3), (3, 2), (4, 4);

select avg(price) from devices;

select pep.name, avg(dev.price)
from devices as dev, people as pep
join device_people devpep
on  devpep.people_id= pep.id
group by pep.name
having avg(price) > 8600;
