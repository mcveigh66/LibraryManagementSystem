QAP 1 - Library management system 

Overview - This project is a Java based Library Management System that supports adding books/users, searching the catalog, borrowing/returning books, and borrowing limits.

Project Structure
`src/main/java/qap1/`: domain classes (`Book`, `User`) and (`LibraryService`).
`src/test/java/qap1/`: Test suite (`LibraryServiceTest`) containing 12 unit tests using JUnit 5.

Clean code practice and examples:

1. Meaningful naming - In `LibraryService.java`, methods like `findBookByIsbn()` and `borrowBook()` clearly show their intent without needing inline comments to explain what they do.

2. Single Responsibility Principal - `Book.java` and `User.java` serve strictly as data models, while `LibraryService.java` handles all business logic (borrowing, returning, limits).

3. Dry code - Fields in `Book` and `User` are marked `private` with public getter/setter methods to protect state, and reusable test fixtures are set up cleanly using `@BeforeEach` in `LibraryServiceTest.java`

dependencies:

1. JUnit 5
2. Maven compiler 

Unit test breakdown: 

1. Adding books and users to the catalog. 
2. Successful borrowing and returning of books.
3. Preventing double-borrowing of active books.
4. Handling non-existent users and ISBN searches safely.
5. Enforcing the maximum 3-book borrow limit per user.

Challenges: 

Setting up github workflow 