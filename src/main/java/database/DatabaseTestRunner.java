package database;

public class DatabaseTestRunner {

    public static void main(String[] args) {
        QueryWrapper qw = new QueryWrapper();
        System.out.println(qw.checkUserLogin("alex7274", "java123"));
    }
}
