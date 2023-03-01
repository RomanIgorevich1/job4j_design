create table films (
	id serial primary key,
	name text
);
create table viewers (
	id serial primary key,
	name text
);
create table films_viewers (
	id serial primary key,
	films_id int references films(id),
	viewers_id int references viewers(id)
);
insert into films(name) values ('The Green Mile');
insert into films(name) values ('Forrest Gump');
insert into films(name) values ('Interstellar');
insert into viewers(name) values ('Roman');
insert into viewers(name) values ('Viktor');
insert into viewers(name) values ('Gleg');
insert into films_viewers(viewers_id, films_id) values (1, 1);
insert into films_viewers(viewers_id, films_id) values (1, 3);
insert into films_viewers(viewers_id, films_id) values (2, 2);
insert into films_viewers(viewers_id, films_id) values (3, 1);
insert into films_viewers(viewers_id, films_id) values (3, 2);
insert into films_viewers(viewers_id, films_id) values (3, 3);
select * from films_viewers;
select * from films where id in (select films_id from films_viewers);
select * from viewers where id in (select viewers_id from films_viewers);