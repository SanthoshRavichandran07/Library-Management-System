# Library Management System

A **Java + MySQL** console-based application that allows users to manage a library’s collection of books.  
This project demonstrates **CRUD operations**, **input validation**, and **database integration** using the **DAO pattern**.

---

## Features
- Add new books with details (title, author, year, publisher, genre)
- Update existing book information
- Search books by ID, title, author, year, publisher, or genre
- View books:
  - All books
  - Books after a given year
  - Books between two years
  - Books sorted by title or year
  - Title, Author & Genre only
- Delete books by ID
- Input validation for IDs, names, years, and genres

---

## Project Architecture

```
src
|- com.lms.book
|  |- BookDAO.java -> Interface defining CRUD operations
|  |- **BookInput.java** -> Collects user input, validates it, and passes it to DAO
|  |- **BookDAOImpl.java** -> Executes SQL queries with JDBC
|- com.lms.model
|  |- **Book.java** -> Model class representing a book entity
|- com.lms.util
|  |- **DBConnection.java** -> Utility class for establishing database connections via `db.properties`
|- com.lms.validator
|  |- **InputValidator.java** -> Ensures data integrity (regex + DB checks)
|  |- **MainMenu.java** -> Entry point, handles user interaction and menu navigation
```

---
## Execution Order
- **MainMenu.java** -> Entry point, handles user interaction and menu navigation
- **BookDAO.java** -> Interface defining CRUD operations
- **BookInput.java** -> Collects user input, validates it, and passes it to DAO
- **InputValidator.java** -> Ensures data integrity (regex + DB checks)
- **Book.java** -> Model class representing a book entity
- **BookDAOImpl.java** -> Executes SQL queries with JDBC
- **DBConnection.java** -> Utility class for establishing database connections via `db.properties`
---

## Tech Stack
- **Language:** Java  
- **Database:** MySQL  
- **Libraries:** JDBC  
- **Design Pattern:** DAO (Data Access Object)  

---

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/SanthoshRavichandran07/Library-Management-System.git

2. Configure database:
- Create a MySQL database named library.
- Create a table book with columns:
  ```
    CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    yearPublished INT,
    publisher VARCHAR(100),
    genre VARCHAR(100)
  );

3. Add a db.properties file in the project root:
  ```
    db.url=jdbc:mysql://localhost:3306/library
    db.username=your_username
    db.password=your_password`
  ```

4. Compile and Run


## Author
**Santhosh Ravichandran**  
Focused on building scalable, recruiter-friendly projects with clean architecture and professional documentation.
