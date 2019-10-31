drop schema if exists university_examination;

create schema university_examination character set utf8 collate utf8_general_ci;
use university_examination;

CREATE TABLE speciality
(
    id              INT AUTO_INCREMENT NOT NULL,
    speciality_name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE student
(
    id            INT AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    email         VARCHAR(255),
    stud_password VARCHAR(255),
    role          VARCHAR(45),
    speciality_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (speciality_id) REFERENCES speciality (id)
);

CREATE TABLE exam
(
    id            INT AUTO_INCREMENT NOT NULL,
    exam_name     VARCHAR(255),
    speciality_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (speciality_id) REFERENCES speciality (id)
);

CREATE TABLE exam_student_rating
(
    id           INT NOT NULL,
    student_id   INT,
    mark         VARCHAR(10),
    date_of_exam BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES exam (id),
    foreign key (student_id) REFERENCES student (id)
);

INSERT INTO speciality (speciality_name)
VALUES ('Programming'),
       ('Economy'),
       ('Mechanics'),
       ('Literature');

INSERT INTO exam (exam_name, speciality_id)
VALUES ('Math', 1),
       ('Math', 2),
       ('Math', 3),
       ('English', 1),
       ('English', 4),
       ('Physicist', 3),
       ('Chemistry', 3),
       ('History', 4),
       ('History', 2),
       ('English', 2),
       ('Phylosophy', 4),
       ('Algorithms', 1);

INSERT INTO student (first_name, last_name, speciality_id, email, stud_password, role)
VALUES ('Petya', 'Vorobushkin', 1, 'petya_vor@ynd.com', '1', 'USER'),
       ('Vasiay', 'Gorb', 2, 'gorb@da.net', '1', 'USER'),
       ('Katia', 'Datova', 3, 'katia_da@gmail.com', '1', 'USER'),
       ('Kiane', 'Reaves', 4, 'thechosenone@yahoo.hoo', '1', 'USER');