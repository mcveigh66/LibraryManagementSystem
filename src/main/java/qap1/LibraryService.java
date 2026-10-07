package qap1;


import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private final List<Book> books;
    private final List<User> users;


    public LibraryService() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
    }


    public void addUser(User user) {
        users.add(user);
    }


    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }


    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }


    public boolean borrowBook(String userId, String isbn) {
        User user = findUserById(userId);
        Book book = findBookByIsbn(isbn);


        if (user == null || book == null) {
            return false;
        }


        if (book.isBorrowed() || !user.canBorrow()) {
            return false;
        }


        book.setBorrowed(true);
        user.incrementBorrowedCount();
        return true;
    }


    public boolean returnBook(String userId, String isbn) {
        User user = findUserById(userId);
        Book book = findBookByIsbn(isbn);


        if (user == null || book == null) {
            return false;
        }


        if (!book.isBorrowed()) {
            return false;
        }


        book.setBorrowed(false);
        user.decrementBorrowedCount();
        return true;
    }


    public List<Book> getBooks() {
        return books;
    }


    public List<User> getUsers() {
        return users;
    }
}


