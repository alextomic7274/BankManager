package manager;

import database.QueryWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class RecordManager {
    private QueryWrapper qw;
    private Scanner s;

    public RecordManager() {
        qw = new QueryWrapper();
        s = new Scanner(System.in);
    }

    public void createTeller() {
        System.out.println("Enter teller username");
        String userName = s.next();
        System.out.println("Enter teller password");
        String passWord = s.next();
        boolean t = qw.insertUser(userName, passWord, 4);
        Menus.adminMenu();
    }

    public void deleteTeller() {
        System.out.println("Enter teller username");
        String userName = s.next();
        System.out.println("Enter teller password");
        String passWord = s.next();
        if (qw.checkUserLogin(userName, passWord)) {
            qw.deleteUser(getUserID(userName));
        }   else System.out.println("Wrong credentials");
        Menus.adminMenu();
    }

    public int getUserID(String username) {
        String user = qw.getUserByName(username).toString();
        String[] array;
        array = user.split("=");
        return Character.getNumericValue(array[5].charAt(0));
    }

    /**
     * This is an example on how to use the QueryWrapper class.
     */
    private void QueryWrapperExample() {
        List<HashMap<String, String>> users = qw.getAllUsers();
        for (HashMap<String, String> user : users) {
            System.out.println("Current user id: " + user.get("id"));
            System.out.println("Current user name: " + user.get("name"));
        }
    }

    public void changeTellerPassword() {
        System.out.println("Enter teller username: ");
        String userName = s.next();
        System.out.println("Enter teller password: ");
        String passWord = s.next();
        System.out.println("Enter new password: ");
        String newPass = s.next();
        if (qw.checkUserLogin(userName, passWord)) {
            qw.changeUserPassword(getUserID(userName), newPass);
        }   else System.out.println("Wrong credentials");
        Menus.adminMenu();
    }

}
