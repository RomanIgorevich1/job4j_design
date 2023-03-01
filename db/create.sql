create table books (
	id serial primary key,
	name text,
	year int,
	read boolean
);
insert into books (name, year, read) values('The Call of Cthulhu', 1926, true);
select * from books;