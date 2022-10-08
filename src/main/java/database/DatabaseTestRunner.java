package database;

public class DatabaseTestRunner {

    public static void main(String[] args) {
        QueryWrapper qw = new QueryWrapper();
        //qw.insertUser("alex7274", "java123", 1);
        // admin id = 1, teller id = 4
        // [{role_id=1, password_hash=0qNq/wFgFZehshnlC9SUCM3qMcvLE4L7Ok5JzFu2UqI=, name=alex7274, id=6}]
        System.out.println(qw.getAllUsers().toString());

    }
}
