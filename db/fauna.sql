create table fauna (
	id serial primary key,
	name text,
	avg_age int,
	discovery_daty date
);
insert into fauna(name, avg_age, discovery_daty) values ('White shark', 25550,'01.01.1758');
insert into fauna(name, avg_age, discovery_daty) values ('Shark bull', 7300, '01.01.1793');
insert into fauna(name, avg_age, discovery_daty) values ('Tiger shark', 18250, '01.01.1822');
insert into fauna(name, avg_age, discovery_daty) values ('Crocodile', 29200, '01.01.1807');
insert into fauna(name, avg_age, discovery_daty) values ('Tiger', 9490, '01.01.1858');
insert into fauna(name, avg_age, discovery_daty) values ('Polar bear', 9855, null);

select * from fauna where name LIKE '%ar%'; 
select * from fauna where avg_age > 10000 AND avg_age < 30000;
select * from fauna where discovery_daty is null;
select * from fauna where discovery_daty < '01.01.1820';