create table products (
	id serial primary key,
	name varchar(50),
	producer varchar(50),
	count integer default 0,
	price integer
);

create or replace procedure insert_data
(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
begin
insert into products (name, producer, count, price)
values (i_name, prod, i_count, i_price);
end
$$;

create or replace procedure update_data (u_count integer, tax float,
u_id integer)
language 'plpgsql' 
as $$
begin 
if u_count > 0 then
update products set count = count - u_count where id = u_id;
end if;
if tax > 0 then
update products set price = price + price * tax;
end if;
end;
$$;

create or replace function func_insert_data
(f_name varchar, f_prod varchar, f_count integer, f_price integer)
returns void
language 'plpgsql'
as $$
begin
insert into products(name, producer, count, price)
values(f_name, f_prod, f_count, f_price);
end;
$$;

create or replace function func_update_data
(f_count integer, tax float, f_id integer)
returns integer 
language 'plpgsql'
as $$
declare 
result integer;
begin
if f_count > 0 then
update products set count = count - f_count where id = f_id;
select into result count from products where id = f_id;
end if;
if tax > 0 then
update products set price = price + price * tax;
select into result sum(price) from products;
end if;
return result;
end;
$$;

create or replace procedure prod_delete_data(p_count integer, p_id integer)
language 'plpgsql'
as $$
begin
if p_count = 0 then
delete from products where count = p_count;
end if;
end;
$$;

create or replace function func_delete_data(f_count integer, f_id integer)
returns integer
language 'plpgsql'
as $$
declare
result integer;
begin
if f_count = 0 then 
delete from products where count = f_count;
select into result sum(price) from products;
return result;
end if;
end;
$$