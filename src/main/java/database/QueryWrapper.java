package database;

import database.dynamicType.*;

import java.security.MessageDigest;
import java.sql.Date;
import java.util.*;

/**
 * This class wraps SQL queries into methods that can be called from outside.
 * They make it easy to execute queries without actually having to write the query.
 */
public class QueryWrapper {
    Connector con;
    public QueryWrapper(){
        con = new Connector();
    }

    public List<HashMap<String, String>> getAllAccounts(){
        List<HashMap<String, DynamicType>> dbResult = con.executeSelect(null, "SELECT * FROM accounts");
        return getHashMapResult(dbResult);
    }

    public List<HashMap<String, String>> getAllRoles(){
        List<HashMap<String, DynamicType>> dbResult = con.executeSelect(null, "SELECT * FROM roles");
        return getHashMapResult(dbResult);
    }

    public List<HashMap<String, String>> getAllUsers(){
        List<HashMap<String, DynamicType>> dbResult = con.executeSelect(null, "SELECT * FROM users");
        return getHashMapResult(dbResult);
    }

    public List<HashMap<String, String>> getAllTransactions(){
        List<HashMap<String, DynamicType>> dbResult = con.executeSelect(null, "SELECT * FROM transactions");
        return getHashMapResult(dbResult);
    }

    public boolean insertRole(String name){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        return con.executeNoResult(map, "INSERT INTO roles (name) VALUES (?)");
    }

    public boolean insertUser(String name, String password, int roleId){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        map.put(2, new StringType(hashPassword(password)));
        map.put(3, new IntegerType(roleId));
        return con.executeNoResult(map, "INSERT INTO users (name, password_hash, role_id) VALUES (?, ?, ?)");
    }

    public boolean insertAccount(String name, Double balance, Date creationDate, int ownerId){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        map.put(2, new DoubleType(balance));
        map.put(3, new DateType(creationDate));
        map.put(4, new IntegerType(ownerId));
        return con.executeNoResult(map, "INSERT INTO accounts (name, user_id) VALUES (?, ?)");
    }

    public boolean insertTransaction(Date transactionDate, Double amount, int userForm, int userTo){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new DateType(transactionDate));
        map.put(2, new DoubleType(amount));
        map.put(3, new IntegerType(userForm));
        map.put(4, new IntegerType(userTo));
        return con.executeNoResult(map, "INSERT INTO transactions (account_id, user_id, amount, date) VALUES (?, ?, ?, ?)");
    }

    private List<HashMap<String, String>> getHashMapResult(List<HashMap<String, DynamicType>> dbResult) {
        List<HashMap<String, String>> result = new ArrayList<>();
        for(HashMap<String, DynamicType> row : dbResult){
            HashMap<String, String> map = new HashMap<>();
            for(String key : row.keySet()){
                map.put(key, row.get(key).toString());
            }
            result.add(map);
        }
        return result;
    }

    private String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
