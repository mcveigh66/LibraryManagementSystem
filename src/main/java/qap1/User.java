package qap1;


public class User {
    private final String userId;
    private final String name;
    private int borrowedCount;


    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedCount = 0;
    }


    public String getUserId() {
        return userId;
    }


    public String getName() {
        return name;
    }


    public int getBorrowedCount() {
        return borrowedCount;
    }


    public boolean canBorrow() {
        int MAX_LIMIT = 3;
        return borrowedCount < MAX_LIMIT;
    }


    public void incrementBorrowedCount() {
        this.borrowedCount++;
    }


    public void decrementBorrowedCount() {
        if (this.borrowedCount > 0) {
            this.borrowedCount--;
        }
    }
}

