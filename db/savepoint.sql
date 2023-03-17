create table books (
	id serial primary key, 
	name varchar(50),
	page integer
);
insert into books(name, page) values ('azazel', 500);
insert into books(name, page) values ('sherlock', 400);
insert into books(name, page) values ('irobot', 350);
begin transaction;
insert into books(name, page) values ('dune', 600);
commit transaction;
begin transaction;
delete from books;
drop table books;
rollback transaction;
select * from books;

begin transaction;
insert into books(name, page) values ('sincity', 200);
savepoint first_savepoint;
delete from books where page = 600;
select * from books;
rollback to savepoint first_savepoint;
select * from books;
insert into books(name, page) values ('theshining', 150);
savepoint second_savepoint;
update books set page = 0 where name = 'azazel';
savepoint third_savepoint;
drop table books;
rollback to savepoint second_savepoint;
select * from books;