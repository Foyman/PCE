CREATE DATABASE PCE;
use PCE;

CREATE TABLE Course (
    CourseId INT PRIMARY KEY,
    Dept VARCHAR(5) NOT NULL,
    CourseNum INT,
    CourseName VARCHAR(100),
    UNIQUE (Dept, CourseNum, CourseName)
);


CREATE TABLE Professor (
	ProfId INT PRIMARY KEY,
	FirstName VARCHAR(15),
	LastName VARCHAR(15),
	UNIQUE(FirstName, LastName)
);

CREATE TABLE ProfCourse (
	CourseId INT,
	ProfId INT,
	FOREIGN KEY (CourseId) REFERENCES Course(CourseId),
	FOREIGN KEY (ProfId) REFERENCES Professor(ProfId)
);

CREATE TABLE Reviews (
	ReviewId INT PRIMARY KEY,
	CourseId INT,
	Rating1 DOUBLE,
	Rating2 DOUBLE,
	Rating3 DOUBLE,
	StudentGrade VARCHAR(2),
	Review VARCHAR(500),
	FOREIGN KEY (CourseId) REFERENCES Course(CourseId)
);

CREATE TABLE Syllabus (
	CourseId INT,
	ProfId INT,
	FileName VARCHAR(50),
	FOREIGN KEY (CourseId) REFERENCES Course(CourseId),
	FOREIGN KEY (ProfId) REFERENCES Professor(ProfId)
);