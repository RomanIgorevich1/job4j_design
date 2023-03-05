create table type (
	id serial primary key,
	name text
);
create table product (
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);
insert into type(name) values ('Сыр'), ('Мороженое');
insert into product(name, type_id, expired_date, price) 
values ('Сыр плавленный', 1, date '01.08.2023', 350.50);
insert into product(name, type_id, expired_date, price) 
values ('Сыр моцарелла', 1, date '01.08.2023', 450.50);
insert into product(name, type_id, expired_date, price) 
values ('Пармезан', 1, date '01.01.2023', 470.50);
insert into product(name, type_id, expired_date, price) 
values ('Сыр Чечил', 1, date '01.08.2023', 370.50);
insert into product(name, type_id, expired_date, price) 
values ('Сулугуни', 1, date '14.03.2023', 420.50);
insert into product(name, type_id, expired_date, price) 
values ('Гауда', 1, date '14.03.2023', 460.50);
insert into product(name, type_id, expired_date, price) 
values ('Сыр Фета', 1, date '14.03.2023', 470.50);
insert into product(name, type_id, expired_date, price) 
values ('Брынза', 1, date '14.03.2023', 400.50);
insert into product(name, type_id, expired_date, price) 
values ('Мороженое пломбир', 2, date '01.08.2023', 200.50);
insert into product(name, type_id, expired_date, price) 
values ('Сливочный стаканчик', 2, date '01.01.2023', 210.50);
insert into product(name, type_id, expired_date, price) 
values ('Фруктовый лед', 2, date '14.03.2023', 150.50);
insert into product(name, type_id, expired_date, price) 
values ('Щербет', 2, date '14.03.2023', 220.50);
insert into product(name, type_id, expired_date, price) 
values ('Мороженое крем-брюле', 2, date '01.08.2023', 470.50);
insert into product(name, type_id, expired_date, price) 
values ('Эскимо', 2, date '01.01.2023', 290.50);
insert into product(name, type_id, expired_date, price) 
values ('Мороженое торт', 2, date '01.08.2023', 470.50);

select * from product where type_id = 1;

select * from product where name LIKE '%Мороженое%';

select * from product where expired_date < '04.03.2023';

select tp.name, pd.type_id, max(price)
from product as pd
join type tp
on pd.type_id = tp.id
group by tp.name, pd.type_id;

select tp.name, count(*) 
from product as pd
join type tp
on pd.type_id = tp.id
group by tp.name

select * from product;

select tp.name, pd.name 
from product as pd
join type tp
on pd.type_id = tp.id
group by tp.name, pd.name

select tp.id, tp.name, count(*)
from product as pd
join type tp
on pd.type_id = tp.id
group by tp.id, tp.name
having count(*) > 7