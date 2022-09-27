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

    /**
     * Get all accounts from the database.
     * @return A list of Hashmaps (one hashmaps represents one line of the result table).
     */
    public List<HashMap<String, String>> getAllAccounts(){
        List<HashMap<String, DynamicType>> dbResult = con.executeSelect(null, "SELECT * FROM accounts");
        return getHashMapResult(dbResult);
    }
    /**
     * Get all roles from the database.
     * @return A list of Hashmaps (one hashmaps represents one line of the result table).
     */
    public List<HashMap<String, String>> getAllRoles(){
        List<HashMap<String, DynamicType>> dbResult = con.executeSelect(null, "SELECT * FROM roles");
        return getHashMapResult(dbResult);
    }
    /**
     * Get all users from the database.
     * @return A list of Hashmaps (one hashmaps represents one line of the result table).
     */
    public List<HashMap<String, String>> getAllUsers(){
        List<HashMap<String, DynamicType>> dbResult = con.executeSelect(null, "SELECT * FROM users");
        return getHashMapResult(dbResult);
    }

    /**
     * Get all transactions from the database.
     * @return A list of Hashmaps (one hashmaps represents one line of the result table).
     */
    public List<HashMap<String, String>> getAllTransactions(){
        List<HashMap<String, DynamicType>> dbResult = con.executeSelect(null, "SELECT * FROM transactions");
        return getHashMapResult(dbResult);
    }
/**
     * Insert a role into the database
 *     @param name The name of the role
     * @return true if the insert was successful, false otherwise
     */
    public boolean insertRole(String name){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        return con.executeNoResult(map, "INSERT INTO roles (name) VALUES (?)");
    }

    /**
     * Insert a user into the database
     * @param name The username of the user
     * @param password The password of the user
     * @param roleId ID of the role the user should have (needs to be looked up in the database)
     * @return true if the insert was successful, false otherwise
     */
    public boolean insertUser(String name, String password, int roleId){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        map.put(2, new StringType(hashPassword(password)));
        map.put(3, new IntegerType(roleId));
        return con.executeNoResult(map, "INSERT INTO users (name, password_hash, role_id) VALUES (?, ?, ?)");
    }

    /**
     * Insert an account into the database
     * @param name The name of the account
     * @param balance The balance of the account
     * @param creationDate date of creation of the account
     * @param ownerId ID of the owner of the account (needs to be looked up in the database)
     * @return true if the insert was successful, false otherwise
     */
    public boolean insertAccount(String name, Double balance, Date creationDate, int ownerId){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        map.put(2, new DoubleType(balance));
        map.put(3, new DateType(creationDate));
        map.put(4, new IntegerType(ownerId));
        return con.executeNoResult(map, "INSERT INTO accounts (name, user_id) VALUES (?, ?)");
    }
    /**
     * Insert a transaction into the database
     * @param transactionDate The date of the transaction
     * @param amount amount of the transaction
     * @param userFrom ID of the sender of the transaction (needs to be looked up in the database)
     * @param userTo ID of the receiver of the transaction (needs to be looked up in the database)
     * @return true if the insert was successful, false otherwise
     */
    public boolean insertTransaction(Date transactionDate, Double amount, int userFrom, int userTo){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new DateType(transactionDate));
        map.put(2, new DoubleType(amount));
        map.put(3, new IntegerType(userFrom));
        map.put(4, new IntegerType(userTo));
        return con.executeNoResult(map, "INSERT INTO transactions (account_id, user_id, amount, date) VALUES (?, ?, ?, ?)");
    }

    /**
     * Converts the result from the connector method into a list of hashmaps that map column names to values (as String).
     * @return The converted list.
     */
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

    /**
     * Hashes a password using SHA-256m, then encodes it using Base64.
     * @param password The password to hash.
     * @return The encoded password.
     */
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
