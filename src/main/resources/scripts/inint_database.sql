create schema university_examination charset utf8 collate utf8_general_ci;

USE university_examination;

create table student (
id INT auto_increment not NULL,
first_name VARCHAR(45),
last_name VARCHAR (100),
email VARCHAR (240),
pass_word VARCHAR (240),
system_role VARCHAR (45),
primary key (id)
);

create table faculty (
id INT auto_increment not NULL,
name VARCHAR (240),
PRIMARY KEY (id)
);

create table speciality (
id INT AUTO_INCREMENT NOT NULL,
name VARCHAR (240),
id_faculty INT,
PRIMARY KEY (ID),
FOREIGN KEY (id_faculty) REFERENCES faculty (id));

create table exam_mark (
id INT AUTO_INCREMENT NOT NULL,
exam_date BIGINT,
mark VARCHAR(45),
id_student INT,
id_speciality INT,
PRIMARY KEY (id),
FOREIGN KEY (id_speciality) REFERENCES speciality (id),
FOREIGN KEY (id_student) REFERENCES student (id));

