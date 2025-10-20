-- Step 1: Create Tables

CREATE TABLE University(
    universityID SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(100),
    email VARCHAR(100),
    city VARCHAR(200),
    universityType VARCHAR(50),
    foundationYear INT
);

CREATE TABLE  Faculty(
    facultyID SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    universityID INT REFERENCES university(universityID)
);

CREATE TABLE Department (
    departmentID SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    language VARCHAR(20),
    educationType VARCHAR(20),
    quota INT,
    topRankedQuota INT,
    educationPeriod INT,
    minScore2024 FLOAT,
    minOrder2024 INT,
    facultyID INT REFERENCES Faculty(facultyID)
);

CREATE TABLE Student (
    studentID SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50),
    examScore FLOAT,
    ranking INT,
    topRanked BOOLEAN,
    pref1 INT REFERENCES Department(departmentID),
    pref2 INT REFERENCES Department(departmentID),
    pref3 INT REFERENCES Department(departmentID)

);

-- Step 2: Insert Sample Data

-- Insert sample universities
INSERT INTO University (name, address, email, city, universityType, foundationYear)
VALUES 
('Ankara University', 'Ulus', 'info@au.edu', 'Ankara', 'state', 1998),
('Bilkent University', 'Keçiören', 'info@bilkent.edu', 'Ankara', 'private', 2010),
('Dokuz Eylül University', 'Buca', 'info@izmiruni.edu', 'Izmir', 'state', 2000),
('Izmir Technical University', 'Karaburun', 'info@itu.edu', 'Izmir', 'state', 2020);
('Izmir University', 'Balçova', 'info@iu.edu', 'Izmir', 'private', 2015),


-- Insert sample faculties
INSERT INTO Faculty (name, email, universityID)
VALUES 
('Engineering Faculty', 'info@eng.au.edu', 1),
('Medicine Faculty', 'info@med.au.edu', 1),
('Engineering Faculty', 'info@eng.bilkent.edu', 2),
('Law Faculty', 'info@law.iu.edu', 5),
('Engineering Faculty', 'info@eng.deu.edu', 3),
('Engineering Faculty', 'info@eng.itu.edu', 4);

-- Insert sample departments
INSERT INTO Department (name, email, language, educationType, quota, topRankedQuota, educationPeriod, minScore2024, minOrder2024, facultyID)
VALUES 
('Computer Engineering', 'info@ce.au.edu', 'English', 'fe', 100, 10, 4, 90.5, 1500, 1),
('Mechanical Engineering', 'info@me.au.edu', 'English', 'ee', 80, 5, 4, 85.0, 2000, 1),
('Civil Engineering', 'info@cie.bilkent.edu', 'Turkish', 'fe', 120, 15, 4, 88.0, 1800, 2),
('Medicine', 'info@med.au.edu', 'English', 'fe', 200, 20, 6, 95.0, 500, 1),
('Law', 'info@law.iu.edu', 'Turkish', 'fe', 150, 10, 4, 80.0, 2500, 3);

-- Insert sample students
INSERT INTO Student (name, surname, examScore, ranking, topRanked, pref1, pref2, pref3)
VALUES 
('Goksu', 'Tuysuzoglu', 92.0, 1400, true, 1, 3, 4),
('Murat', 'Barcin', 88.0, 1600, false, 2, 4, 1),
('Sarp', 'Topaloglu', 85.0, 2000, true, 3, 1, 2),
('Altan', 'Baysal', 90.0, 1500, false, 1, 4, 3);

-- Step 3: SQL Queries

-- Query 1: Find the university names which are located in the cities whose name starts with “A” and founded after 1990
SELECT name 
FROM University 
WHERE city LIKE 'A%' AND foundationYear > 1990;

-- Query 2: Find the universities which include “Engineering” and “Medicine” Faculties.
SELECT u.name 
FROM University u
JOIN Faculty f1 ON u.universityID = f1.universityID AND f1.name LIKE '%Engineering%'
JOIN Faculty f2 ON u.universityID = f2.universityID AND f2.name LIKE '%Medicine%';

-- Query 3: Find the count of faculties according to university types.
SELECT u.universityType, COUNT(f.facultyID) AS facultyCount
FROM University u
JOIN Faculty f ON u.universityID = f.universityID
GROUP BY u.universityType;

-- Query 4: Find the departments that contain “engineering” and are the type of “ee”.
SELECT d.name 
FROM Department d
WHERE d.name ILIKE '%engineering%' AND d.educationType = 'ee';

-- Query 5: Find the top five departments with the longest education period and the highest score
SELECT name 
FROM Department 
ORDER BY educationPeriod DESC, minScore2024 DESC 
LIMIT 5;

-- Query 6: Find the most preferred 4-year departments
SELECT d.name, COUNT(*) AS preferenceCount
FROM Department d
JOIN Student s ON d.departmentID IN (s.pref1, s.pref2, s.pref3)
WHERE d.educationPeriod = 4
GROUP BY d.name
ORDER BY preferenceCount DESC;

-- Query 7: List the students who prefer the Department of Computer Engineering as their first choice according to their exam score in a descending order.
SELECT s.name, s.surname, s.examScore 
FROM Student s
JOIN Department d ON s.pref1 = d.departmentID
WHERE d.name = 'Computer Engineering'
ORDER BY s.examScore DESC;

-- Query 8: Update the Faculty of Engineering in Dokuz Eylül University to be located in Izmir Technical University.
UPDATE Faculty
SET universityID = (SELECT universityID FROM University WHERE name = 'Izmir Technical University')
WHERE name = 'Engineering Faculty' AND universityID = (SELECT universityID FROM University WHERE name = 'Dokuz Eylül University');

-- Query 9: Extend the current education period of the departments under the Faculty of Law by one year.
UPDATE Department
SET educationPeriod = educationPeriod + 1
WHERE facultyID IN (SELECT facultyID FROM Faculty WHERE name = 'Law Faculty');


-- Query 10:  Delete the faculties and departments in İzmir University
-- Delete departments in İzmir University
DELETE FROM Department
WHERE facultyID IN (SELECT facultyID FROM Faculty WHERE universityID = (SELECT universityID FROM University WHERE name = 'Izmir University'));

-- Delete faculties in İzmir University
DELETE FROM Faculty
WHERE universityID = (SELECT universityID FROM University WHERE name = 'Izmir University');
