# Library Management System

A Java-based Library Management System built using the DAO (Data Access Object) pattern.  This project demonstrates clean separation of concerns between UI, Service, DAO, Model, and Validation layers, ensuring maintainability and scalability.

---

## Features 
- Book Management
  - Add, update, delete, search, and view books
  - Filter books by year, title, genre, publisher
  - Track availability (Yes/No)
- Member Management
  - Add, update, delete, search, and view members
  - Manage roles (ADMIN, USER)
  - View member details
- Transaction Management
  - Issue books (with automatic issue/return dates)
  - Return books
  - View all transactions
  - View transactions by member
- Validation Layer
  - Regex-based validation for names, titles, publishers, genres, and emails
  - Year range validation
  - Database-backed validation for IDs and availability
  - Role verification (ADMIN vs USER)

---

## Project Structure

```txt
src
|- com.lms.dao
|  |- BookDAO.java
|  |- BookDAOImpl.java
|  |- MemberDAO.java
|  |- MemberDAOImpl.java
|  |- TransactionDAO.java
|  |- TransactinDAOImpl.java
|- com.lms.model
|  |- Books.java
|  |- Members.java
|  |- Transactions.java
|- com.lms.services
|  |- LibraryService.java
|  |- LibraryServiceImpl.java
|- com.lms.ui
|  |- BookInput.java
|  |- MemberInput.java
|- com.lms.util
|  |- DBConnection.java
|- com.lms.validation
|  |- InputValidator.java
|- MainMenu.java
```

---

## Execution Order
- `MainMenu.java` -> Entry point, handles user interaction and menu navigation
- `LibraryService.java` -> Interface defining high-level operations for books, members, and transactions
- `LibraryServiceImpl.java` -> Implements LibraryService, orchestrates calls to DAO classes
- `BookDAO.java` / `MemberDAO.java` / `TransactionDAO.java` -> Interfaces defining CRUD operations for each entity
- `BookInput.java` / `MemberInput.java` -> Collects user input, validates it, and passes it to DAO via service layer
- `InputValidator.java` → Ensures data integrity (regex validation + DB checks)
- `Books.java` / `Members.java` / `Transactions.java` -> Model classes representing entities in the system
- `BookDAOImpl.java` / `MemberDAOImpl.java` / `TransactionDAOImpl.java` -> Executes SQL queries with JDBC, isolates persistence logic
- `DBConnection.java` -> Utility class for establishing database connections via `db.properties`
---

## Tech Stack
- **Language:** Java  
- **Database:** MySQL  
- **Libraries:** JDBC
- **Design Pattern:** DAO (Data Access Object)  

---

## Setup Instructions
1. Clone the repository:
   ```git
   git clone https://github.com/SanthoshRavichandran07/Library-Management-System.git

2. Configure database:
- Create a MySQL database named library_management_system_db.
- Create a table book with columns:
  ```sql
    CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    yearPublished INT,
    publisher VARCHAR(100),
    genre VARCHAR(100)
    );

    CREATE TABLE members ( id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    role VARCHAR(50)
    );

    CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    member_id INT,
    issue_date DATE,
    return_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
    );

   ```

3. Add a db.properties file in the project root:
   ```properties
     db.url=jdbc:mysql://localhost:3306/library_management_system_db
     db.username=your_username
     db.password=your_password
   ```

4. Compile and Run
   ```
     javac MainMenu.java
     java MainMenu
   ```

---

## Author
**Santhosh Ravichandran**  
Focused on building scalable, recruiter-friendly projects with clean architecture and professional documentation.
