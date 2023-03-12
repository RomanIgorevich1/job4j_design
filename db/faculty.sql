create table students (
	id serial primary key,
	name text
);
insert into students(name) values ('Roman'),('Ivan'),('Elena'),('Yana'),
('Oleg'),('Olya'),('Tanya'),('Anya'),('Kira'),('Petr'),('Irina');

create table faculties (
	id serial primary key,
	name text
);
insert into faculties(name) values ('Faculty of Mechanical Engineering'),
('Faculty of Computer Science'),('Faculty of Psychology'),('Faculty of Journalism');

create table subjects (
	id serial primary key,
	name text,
	faculty_id int references faculties(id)
);
insert into subjects(name, faculty_id) values ('Физика', 1),('Электрические машины', 1),
('Инженерная графика', 1),('Математика', 2),('Информатика', 2), ('Технологии баз данных', 2),
('Психофизиология', 3),('Психология сознания', 3), ('Общая психология', 3),('Политология', 4),
('Основы теории коммуникации', 4),('Основы теории журналистики', 4);

create table student_subject (
	id serial primary key,
	student_id int references students(id),
	subject_id int references subjects(id)
);
insert into student_subject(student_id, subject_id) values (1, 4),(1, 5),(2, 1),(2, 3),
(3, 7),(3, 8),(3, 9),(4, 6),(4, 5),(5, 1),(5, 3),(6, 2),(7, 3),(8, 9),(8, 8),(9, 10), (9, 11),
(9, 12),(10, 10),(11, 1);

create view show_student_and_faculty as
select st.name as student, count(fc.name), fc.name as faculty
from students as st
join student_subject as stsb on st.id = stsb.student_id
join subjects as sub on stsb.subject_id = sub.id
join faculties as fc on sub.faculty_id = fc.id
group by st.name, fc.name having count(fc.name) > 2;

select * from show_student_and_faculty;