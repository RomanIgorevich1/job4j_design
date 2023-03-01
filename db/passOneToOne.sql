create table ticket (
	id serial primary key,
	seat text,
	gate int
);
create table people (
	id serial primary key,
	name text,
	pass_id int references ticket(id) unique
)