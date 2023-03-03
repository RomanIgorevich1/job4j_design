create table genre (
	id serial primary key,
	name text
);
create table book (
	id serial primary key,
	name text,
	year int,
	genre_id int references genre(id)
);

insert into genre(name) values ('detective');
insert into genre(name) values ('fantastic');
insert into genre(name) values ('mistic');

insert into book(name, year, genre_id) values ('azazel', 2002, 1);
insert into book(name, year, genre_id) values ('sherlock', 1887, 1);
insert into book(name, year, genre_id) values ('sincity', 1991, 1);
insert into book(name, year, genre_id) values ('dune', 1963, 2);
insert into book(name, year, genre_id) values ('theshining', 1977, 3);
insert into book(name) values ('irobot');

select bk.name, bk.year, gr.name from book as bk join genre 
as gr on bk.genre_id = gr.id;
select bk.name as Имя, bk.year as Год, gr.name as Жанр 
from book as bk join genre as gr on bk.genre_id = gr.id;
select bk.name as "Название книги", bk.year as Год, gr.name as Жанр
from book bk join genre gr on bk.genre_id = gr.id;