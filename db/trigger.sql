create table products (
	id serial primary key,
	name varchar(50),
	producer varchar(50),
	count integer default 0,
	price integer
);

create table history_of_price (
	id serial primary key,
	name varchar(50),
	price integer,
	date timestamp
);
create or replace function tax_after()
returns trigger as
$$
begin
update products
set price = price + price * 0.13
where id = (select id from inserted) AND count >= 5;
return new;
end;
$$
language 'plpgsql';

create trigger trigger_after
after insert on products
referencing new table as inserted
execute procedure tax_after();

create or replace function tax_before()
returns trigger as
$$
begin
new.price = new.price + new.price * 0.13;
return new;
end;
$$
language 'plpgsql';

create trigger trigger_before
before insert on products
for each row
execute procedure tax_before();

create or replace function history_price()
returns trigger as
$$
begin
insert into history_of_price(name, price, date) values (new.name, new.price, now());
return new;
end;
$$
language 'plpgsql';

create trigger trigger_price
after insert on products
for each row
execute procedure history_price();
