create table company (
	id integer not null,
	name character varying,
	constraint company_pkey primary key(id)
);

create table person (
	id integer not null,
	name character varying,
	company_id integer references company(id),
	constraint person_pkey primary key (id)
);

insert into company(id, name) values (1, 'company1');
insert into company(id, name) values (2, 'company2');
insert into company(id, name) values (3, 'company3');
insert into company(id, name) values (4, 'company4');
insert into company(id, name) values (5, 'company5');

insert into person (id, name, company_id) values (1, 'person1', 1);
insert into person (id, name, company_id) values (2, 'person2', 1);
insert into person (id, name, company_id) values (3, 'person3', 2);
insert into person (id, name, company_id) values (4, 'person4', 2);
insert into person (id, name, company_id) values (5, 'person5', 3);
insert into person (id, name, company_id) values (6, 'person6', 4);
insert into person (id, name, company_id) values (7, 'person7', 4);
insert into person (id, name, company_id) values (8, 'person8', 4);
insert into person (id, name, company_id) values (9, 'person9', 1);
insert into person (id, name, company_id) values (10, 'person10', 5);
insert into person (id, name, company_id) values (11, 'person11', 5);
insert into person (id, name, company_id) values (12, 'person12', 5);
insert into person (id, name, company_id) values (13, 'person13', 5);
insert into person (id, name, company_id) values (14, 'person14', 1);

select per.name, com.name from person per
left join company com
on per.company_id = com.id
where company_id != 5
group by per.name, com.name

select com.name, count(per.company_id) as companyCount
from company as com
join person per
on per.company_id = com.id
group by com.name
having count(*) >= all (select count(per.company_id) from person per group by per.company_id)