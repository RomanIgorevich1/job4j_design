create table teens (
	id serial primary key,
	name text,
	gender text
);
insert into teens(name, gender) values ('Roman', 'man');
insert into teens(name, gender) values ('Svetlana', 'woman');
insert into teens(name, gender) values ('Igor', 'man');
insert into teens(name, gender) values ('Elena', 'woman');

select n1.name as name_man,
n2.name as name_women
from teens n1 cross join teens n2
where n1.gender = 'man' AND
n2.gender = 'woman';