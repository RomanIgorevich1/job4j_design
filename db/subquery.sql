CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);
CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);
insert into customers(first_name, last_name, age, country) values ('Иван ', 'Петров', 25, 'Москва');
insert into customers(first_name, last_name, age, country) values ('Олег', 'Усачев', 28, 'Рязань');
insert into customers(first_name, last_name, age, country) values ('Елена', 'Попова', 22, 'Волгоград');
insert into customers(first_name, last_name, age, country) values ('Мария', 'Фролова', 20, 'Новосибирск');
insert into customers(first_name, last_name, age, country) values ('Юля', 'Романова', 18, 'Казань');
insert into orders(amount, customer_id) values (2, 1), (1, 2),(0, 3);

select * from customers
where age < (select max(age) from customers);

select first_name
from customers, orders
where 
customers.id = customer_id and amount < 1 
or customers.id not in (select orders.id from orders)
group by first_name;