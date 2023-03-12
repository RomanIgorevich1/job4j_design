create table car_bodies (
	id serial primary key,
	name text
);
create table car_engines (
	id serial primary key,
	name text
);
create table car_transmissions (
	id serial primary key,
	name text
);
create table cars (
	id serial primary key,
	name text,
	bodi_id int references car_bodies,
	engine_id int references car_engines,
	transmission_id int references car_transmissions
);
insert into car_bodies(name) values ('Седан'), ('Хетчбек'), ('Пикап'), ('Внедорожник');
insert into car_engines(name) values ('Двигатель 1.8'), ('Двигатель 2.0'), ('Двигатель 2.5'),
('Двигатель 3.0'), ('Двигатель 3.5');
insert into car_transmissions(name) values ('Механическая'), ('Автоматическая'), ('Робот'), ('Вариатор');
insert into cars(name, bodi_id, engine_id, transmission_id) values 
('Mazda', 1, 2, 1), ('Honda', 2, 1, 2), ('Audi', 1, 2, 3), ('Ford', 4, 4, 3), ('Toyota', 4, 3, 1),
('Kia', 2, null, 2), ('Skoda', 1, 3, null), ('Opel', null, 2, 1), ('Dodge', 1, 3, 3), ('Maserati', null, null, null);

select cars.name, car_bodies.name, car_engines.name, car_transmissions.name
from cars
left join car_bodies on cars.bodi_id = car_bodies.id
left join car_engines on cars.engine_id = car_engines.id
left join car_transmissions on cars.transmission_id = car_transmissions.id;

select cb.name, cr.name
from  car_bodies as cb
left join cars as cr 
on cb.id = cr.bodi_id
where cr.name is null
group by cb.name, cr.name;

select ce.name, cr.name
from  car_engines as ce
left join cars as cr 
on ce.id = cr.engine_id
where cr.id is null
group by ce.name, cr.name;

select ct.name, cr.name
from  car_transmissions as ct
left join cars as cr 
on ct.id = cr.transmission_id
where cr.id is null
group by ct.name, cr.name;