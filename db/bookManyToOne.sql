create table genre (
	id serial primary key,
	name text
);
create table books (
	id serial primary key,
	name text,
	position_id int references genre(id) 
);
insert into genre(name) values ('Fantasy');
insert into books(name, position_id) values ('The Lord of the Rings', 1);
select * from books;
select * from genre where id in(select position_id from books); 