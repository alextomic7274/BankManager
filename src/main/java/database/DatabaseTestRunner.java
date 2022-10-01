package database;

public class DatabaseTestRunner {

    public static void main(String[] args) {
        QueryWrapper qw = new QueryWrapper();
        // Add 1 admin
        qw.insertUser("Alex", "javacoding", 2);
    }
}
