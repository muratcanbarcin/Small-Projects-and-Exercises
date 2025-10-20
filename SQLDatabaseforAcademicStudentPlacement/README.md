# SQL Database for Academic Student Placement

## Project Overview

This project is a comprehensive relational database system designed in SQL to manage a complex academic student placement scenario. The system models the relationships between students, their university preferences, and the academic institutions (universities, faculties, and departments).

The core of the project is not just the database creation, but also the execution of complex SQL queries for data retrieval, manipulation, and updates, as specified in the assignment requirements.

## Core Technical Features

* **Entity-Relationship (ER) Diagram:** A detailed ER diagram (`SQLDatabasefor AcademicStudent Placement.jpg`) was designed to model the entities and their relationships. This includes `Students`, `Universities`, `Faculties`, `Departments`, and `Preferences`.
* **SQL Schema (DDL):** The `SQLDatabasefor AcademicStudent Placement.sql` file includes the complete Data Definition Language (DDL) scripts (`CREATE TABLE`) to build the database schema. This includes all necessary constraints such as Primary Keys, Foreign Keys, and data types.
* **Data Population (DML):** The SQL script also contains Data Manipulation Language (DML) statements (`INSERT INTO`) to populate the database with sample data.
* **Complex SQL Queries:** The project demonstrates proficiency in SQL by providing solutions to 10 distinct operational queries. These include:
    * Retrieving students placed in their first-choice department.
    * Listing departments with no placements.
    * Querying students who were not placed in any of their three preferences.
    * Performing `UPDATE` operations (e.g., changing a faculty's location, extending education periods).
    * Performing `DELETE` operations (e.g., deleting faculties from a specific university).

## Database Schema (How it Works)

The database schema is normalized to accurately represent the data model:

* **`University` Table:** Stores unique university information (ID, name, city, type).
* **`Faculty` Table:** Linked to a `University` via `university_id` (One-to-Many).
* **`Department` Table:** Linked to a `Faculty` via `faculty_id` (One-to-Many). Stores crucial placement data like `quota`, `language`, `min_score`, etc..
* **`Student` Table:** Stores student personal details, `examination_score`, and `ranking`.
* **`Preferences` Table:** A junction/linking table that manages the Many-to-Many relationship between `Student` and `Department`. It stores the 3 university preferences for each student.

## How to Use

1.  Create a new, empty database in your SQL management system (e.g., PostgreSQL, MySQL, SQL Server).
2.  Open the `SQLDatabasefor AcademicStudent Placement.sql` file in your database client.
3.  Execute the entire SQL script. The script is structured to:
    * First, create all the required tables (DDL).
    * Second, insert the sample data into the tables (DML).
    * Third, execute the 10 assignment queries (SELECT, UPDATE, DELETE) to show the results.

## Key Project Files

* **`SQLDatabasefor AcademicStudent Placement.sql`**: Contains all SQL code: `CREATE TABLE` (DDL), `INSERT INTO` (DML), and the 10 problem-solving queries.
* **`SQLDatabasefor AcademicStudent Placement.jpg`**: The Entity-Relationship (ER) Diagram illustrating the database schema and relationships between tables.
