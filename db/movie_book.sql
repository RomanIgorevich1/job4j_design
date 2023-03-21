create table movie (
	id serial primary key,
	name text,
	director text
);
create table book (
	id serial primary key,
	title text,
	author text
);

select name
from movie
intersect
select title
from book;

select name
from movie
union
select title
from book
except
select name
from movie
order by name;

(select name 
from movie
except
select title 
from book)
union
(
select title
from book
except
select name
from movie
);