create table department (
	id serial primary key,
	name text
);
create table employees (
	id serial primary key,
	name text,
	department_id int references department(id)
);
insert into department(name) values ('Department 1');
insert into department(name) values ('Department 2');
insert into department(name) values ('Department 3');
insert into employees(name, department_id) values ('Roman', 1);
insert into employees(name, department_id) values ('Elena', null);
insert into employees(name, department_id) values ('Tanya', 2);
insert into employees(name, department_id) values ('Olya', null);
insert into employees(name, department_id) values ('Natasha', 1);
insert into employees(name, department_id) values ('Igor', 2);

select * from department dep
left join employees emp
on dep.id = emp.department_id
where emp.department_id is not null;

select * from employees em
right join department dep
on dep.id = em.department_id
where emp.department_id is not null;

select * from department dep
full join employees em
on dep.id = em.department_id;

select * from department dep
cross join employees em;

select * from department dep
left join employees emp
on dep.id = emp.department_id
where emp.department_id is null;

select emp.name, dep.name from  employees emp
right join   department dep
on  dep.id = emp.department_id
group by emp.name, dep.name;

select emp.name, dep.name from  department dep
left join  employees emp
on  dep.id = emp.department_id
group by emp.name, dep.name;