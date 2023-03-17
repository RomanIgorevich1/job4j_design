create database library;
\c library;
/* read committed */
/* Первая транзакция */
create table books (
    id serial primary key,
    name varchar(50),
    page integer
);
insert into books(name, page) values ('azazel', 500);
insert into books(name, page) values ('sherlock', 400);
insert into books(name, page) values ('irobot', 350);
begin transaction isolation level read committed;
select * from books;
insert into books(name, page) values ('dune', 600);
delete from books where page = 400;
update books set page = 520 where name = 'azazel';
select * from books;
/* Вторая транзакция */
begin transaction isolation level read committed;
select * from books;
/* Первая транзакция */
commit;
/* Вторая транзакция */
select * from books;

/* repeatable read */
/* Первая транзакция */
create table books (
    id serial primary key,
    name varchar(50),
    page integer
);
insert into books(name, page) values ('azazel', 500);
insert into books(name, page) values ('sherlock', 400);
insert into books(name, page) values ('irobot', 350);
begin transaction isolation level repeatable read;
/* Вторая транзакция */
begin transaction isolation level repeatable read;
/* Первая транзакция */
select * from books;
/* Вторая транзакция */
select * from books;
/* Первая транзакция */
insert into books(name, page) values ('dune', 600);
delete from books where page = 400;
update books set page = 520 where name = 'azazel';
/* Вторая транзакция --> Ожидание*/
update books set page = 520 where name = 'azazel';
/* Первая транзакция */
rollback;
/* Вторая транзакция --> Выполнение второй транзакции после отката первой */
select * from books;
/* Первая транзакция выполнилась успешно */
commit;
/* Вторая транзакция --> Ошибка из за параллельного изменения */
update books set page = 520 where name = 'azazel';