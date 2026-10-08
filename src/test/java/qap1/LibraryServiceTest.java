package qap1;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class LibraryServiceTest {


    private LibraryService libraryService;
    private Book book1;
    private User user1;


    @BeforeEach
    void setUp() {
        libraryService = new LibraryService();
        book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
        Book book2 = new Book("978-0132350884", "Clean Code", "Robert C. Martin");
        user1 = new User("U001", "Alice Smith");


        libraryService.addBook(book1);
        libraryService.addBook(book2);
        libraryService.addUser(user1);
    }


    // 1. Test adding books
    @Test
    void testAddBook() {
        assertEquals(2, libraryService.getBooks().size());
    }


    // 2. Test adding users
    @Test
    void testAddUser() {
        assertEquals(1, libraryService.getUsers().size());
    }


    // 3. Test finding a book by ISBN
    @Test
    void testFindBookByIsbn_Found() {
        Book found = libraryService.findBookByIsbn("978-0134685991");
        assertNotNull(found);
        assertEquals("Effective Java", found.getTitle());
    }


    // 4. Test searching for a non-existent ISBN returns null
    @Test
    void testFindBookByIsbn_NotFound() {
        Book found = libraryService.findBookByIsbn("000-0000000000");
        assertNull(found);
    }


    // 5. Test finding a user by ID
    @Test
    void testFindUserById_Found() {
        User found = libraryService.findUserById("U001");
        assertNotNull(found);
        assertEquals("Alice Smith", found.getName());
    }


    // 6. Test successful book borrowing
    @Test
    void testBorrowBook_Success() {
        boolean success = libraryService.borrowBook("U001", "978-0134685991");
        assertTrue(success);
        assertTrue(book1.isBorrowed());
        assertEquals(1, user1.getBorrowedCount());
    }


    // 7. Test borrowing an already borrowed book fails
    @Test
    void testBorrowBook_AlreadyBorrowed() {
        libraryService.borrowBook("U001", "978-0134685991");


        User user2 = new User("U002", "Bob Jones");
        libraryService.addUser(user2);


        boolean result = libraryService.borrowBook("U002", "978-0134685991");
        assertFalse(result);
    }


    // 8. Test borrowing with invalid user or book returns false
    @Test
    void testBorrowBook_InvalidUserOrBook() {
        assertFalse(libraryService.borrowBook("INVALID_USER", "978-0134685991"));
        assertFalse(libraryService.borrowBook("U001", "INVALID_ISBN"));
    }


    // 9. Test exceeding the 3-book borrow limit
    @Test
    void testBorrowBook_ExceedLimit() {
        Book book3 = new Book("111-1111111111", "Java 101", "Author A");
        Book book4 = new Book("222-2222222222", "Java 102", "Author B");
        libraryService.addBook(book3);
        libraryService.addBook(book4);


        libraryService.borrowBook("U001", "978-0134685991");
        libraryService.borrowBook("U001", "978-0132350884");
        libraryService.borrowBook("U001", "111-1111111111");


        boolean fourthAttempt = libraryService.borrowBook("U001", "222-2222222222");
        assertFalse(fourthAttempt);
    }


    // 10. Test returning a book successfully
    @Test
    void testReturnBook_Success() {
        libraryService.borrowBook("U001", "978-0134685991");


        boolean returned = libraryService.returnBook("U001", "978-0134685991");
        assertTrue(returned);
        assertFalse(book1.isBorrowed());
        assertEquals(0, user1.getBorrowedCount());
    }

    // 11. Test returning a book that was never borrowed fails
    @Test
    void testReturnBook_NotBorrowed() {
        boolean result = libraryService.returnBook("U001", "978-0134685991");
        assertFalse(result);
    }


    // 12. Test book getters
    @Test
    void testBookGetters() {
        assertEquals("978-0134685991", book1.getIsbn());
        assertEquals("Effective Java", book1.getTitle());
        assertEquals("Joshua Bloch", book1.getAuthor());
    }

}
