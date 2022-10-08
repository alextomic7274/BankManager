package database;

public class DatabaseTestRunner {

    public static void main(String[] args) {
        QueryWrapper qw = new QueryWrapper();
        System.out.println(qw.hashPassword("Test123"));
        System.out.println(qw.guavaHash("Test123"));
    }
}
