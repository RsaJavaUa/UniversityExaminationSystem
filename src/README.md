Requirements:
 1. DB - MySql/PostgreSql
 2. Java version up 8.
 3. Maven
 
 
 How to install:
 1. Clone project
 2. Run inint_database.sql from resources/scripts folder
 3. Go to link localhost:8080/university

Bussiness Logic
1) User could login  - LoginPage (LoginServlet all servlets would refactored to comands with one main dispatcher servlet)
2) A student could register on-site. - (RegisterServlet)
3) The welcome page is University page (General information) - UniversityServlet now (GET)
4) From UniversityPage user can go to Specialities pages - getMethod
5) Concrete Speciality has a list of exams and a list of students. SpecialityServlet later command
From Speciality, you can go to concrete exam; (GET)
6) Exams show list of students and results; ExamServlet
Admin can fix exam results as well as deleteStudent from Speciality;
7) After all, exams passed part of students pressed some drop out,
according to their average mark and available places on speciality;
8)Admin can add speciality (and delete) with some already created exams; - AdminServlet
Set available places on speciality;
Make the results of exams with random values;
9) Users can logout;