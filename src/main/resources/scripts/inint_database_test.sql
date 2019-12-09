drop schema if exists university_examination_test;

create schema university_examination_test character set utf8 collate utf8_general_ci;
use university_examination_test;

CREATE TABLE speciality
(
    id              INT AUTO_INCREMENT NOT NULL,
    speciality_name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id            INT AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    email         VARCHAR(255) UNIQUE NOT NULL ,
    stud_password VARCHAR(255),
    role          VARCHAR(45),
    speciality_id INT DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (speciality_id) REFERENCES speciality (id)
);

CREATE TABLE exam
(
    id            INT AUTO_INCREMENT NOT NULL,
    exam_name     VARCHAR(255),
    speciality_id INT,
    date_of_exam BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (speciality_id) REFERENCES speciality (id) ON DELETE CASCADE
);


CREATE TABLE exam_student_rating
(
    id           INT AUTO_INCREMENT NOT NULL ,
    student_id   INT,
    exam_id INT,
    mark         VARCHAR(10),
    PRIMARY KEY (id),
    FOREIGN KEY (exam_id) REFERENCES exam (id) ON DELETE CASCADE ,
    FOREIGN KEY (student_id) REFERENCES user (id) ON DELETE CASCADE
);

INSERT INTO speciality (speciality_name)
VALUES ('Programming'),
       ('Economy'),
       ('Mechanics'),
       ('Literature');

INSERT INTO exam (exam_name, speciality_id, date_of_exam)
VALUES ('Math', 1, 1573207200000),
       ('English', 1, 1573387200000),
       ('Algorithms', 1, 1573473600000),
       ('Math', 2, 1573214400000),
       ('History', 2, 1573466400000),
       ('English', 2, 1573587200000),
       ('Math', 3, 1573224400000),
       ('Physicist', 3, 1573466400000),
       ('Chemistry', 3, 1573466400000),
       ('English', 4, 1573787200000),
       ('History', 4, 1573666400000),
       ('Phylosophy', 4, 1573639200000);

INSERT INTO user (first_name, last_name, speciality_id, email, stud_password, role)
VALUES ('Petya', 'Vorobushkin', 1, 'petya_vor@ynd.com', '1', 'USER'),
       ('Vasiay', 'Gorb', 2, 'gorb@da.net', '1', 'USER'),
       ('Katia', 'Damova', 3, 'katia_dam@gmail.com', '1', 'USER'),
       ('Lenya', 'Golubkov', 1, 'golub@bink.com', '1', 'USER'),
       ('Kiane', 'Reaves', 4, 'thechosenone@yahoo.hoo', '1', 'USER');

INSERT INTO exam_student_rating (exam_id, student_id, mark) VALUES
(1, 1, 'A'),
(2, 1, 'A'),
(3, 1, 'C'),

(1, 5, 'A'),
(2, 5, 'B'),
(3, 5, 'C'),

(4, 2, 'B'),
(5, 2, 'B'),
(6, 2, 'A'),

(3, 3, 'A'),
(8, 3, 'A'),
(9, 3, 'C'),

(10, 3, 'C'),
(11, 3, 'C'),
(12, 3, 'C');

