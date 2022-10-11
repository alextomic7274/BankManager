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
     * @param username The username which id is to be retrieved.
     * @return The id of the user with the given username.
     */
    private int getUserIdExample(String username) {
        List<HashMap<String, String>> users = qw.getUserByName(username);
        if(users.size() == 0) {
            return -1;
        } else {
            String userId = users.get(0).get("id");
            return Integer.parseInt(userId);
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
