package database;

import database.dynamicType.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import com.google.common.hash.Hashing;

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
        List <HashMap<String, DynamicType>> dbResult;
        try {
            dbResult = con.executeSelect(null, "SELECT * FROM accounts");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getAllAccounts\"", e);
        }
        return getHashMapResult(dbResult);
    }

    /**
     * Get an account from the database by its id.
     * @param id The id of the account.
     * @return A list of size 1 or 0 (if no account with the given id exists).
     */
    public List<HashMap<String, String>> getAccountById(int id){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        List<HashMap<String, DynamicType>> dbResult;
        map.put(1, new IntegerType(id));
        try {
            dbResult = con.executeSelect(map, "SELECT * FROM accounts WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getAccountById\"", e);
        }
        return getHashMapResult(dbResult);
    }
    /**
     * Get all roles from the database.
     * @return A list of Hashmaps (one hashmaps represents one line of the result table).
     */
    public List<HashMap<String, String>> getAllRoles(){
        List<HashMap<String, DynamicType>> dbResult;
        try{
            dbResult = con.executeSelect(null, "SELECT * FROM roles");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getAllRoles\"", e);
        }
        return getHashMapResult(dbResult);
    }

    /**
     * Get a role from the database by its id.
     * @param id The id of the role.
     * @return A list of size 1 or 0 (if no role with the given id exists).
     */
    public List<HashMap<String, String>> getRoleById(int id){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        List<HashMap<String, DynamicType>> dbResult;
        map.put(1, new IntegerType(id));
        try {
            dbResult = con.executeSelect(map, "SELECT * FROM roles WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getRoleById\"", e);
        }
        return getHashMapResult(dbResult);
    }

    /**
     * Get all users from the database.
     * @return A list of Hashmaps (one hashmaps represents one line of the result table).
     */
    public List<HashMap<String, String>> getAllUsers(){
        List<HashMap<String, DynamicType>> dbResult;
        try{
            dbResult = con.executeSelect(null, "SELECT * FROM users");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getAllUsers\"", e);
        }
        return getHashMapResult(dbResult);
    }

    /**
     * Get a user from the database by its id.
     * @param id The id of the user.
     * @return A list of size 1 or 0 (if no user with the given id exists).
     */
    public List<HashMap<String, String>> getUserById(int id){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        List<HashMap<String, DynamicType>> dbResult;
        map.put(1, new IntegerType(id));
        try {
            dbResult = con.executeSelect(map, "SELECT * FROM users WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getUserById\"", e);
        }
        return getHashMapResult(dbResult);
    }

    /**
     * Get a user from the database by its username.
     * @param name The username of the user.
     * @return A list of size 1 or 0 (if no user with the given username exists).
     */
    public List<HashMap<String, String>> getUserByName(String name){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        List<HashMap<String, DynamicType>> dbResult;
        map.put(1, new StringType(name));
        try {
            dbResult = con.executeSelect(map, "SELECT * FROM users WHERE name = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getUserByName\"", e);
        }
        return getHashMapResult(dbResult);
    }

    /**
     * Get all transactions from the database.
     * @return A list of Hashmaps (one hashmaps represents one line of the result table).
     */
    public List<HashMap<String, String>> getAllTransactions(){
        List<HashMap<String, DynamicType>> dbResult;
        try{
            dbResult = con.executeSelect(null, "SELECT * FROM transactions");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getAllTransactions\"", e);
        }
        return getHashMapResult(dbResult);
    }

    /**
     * Get a transaction from the database by its id.
     * @param id The id of the transaction.
     * @return A list of size 1 or 0 (if no transaction with the given id exists).
     */
    public List<HashMap<String, String>> getTransactionById(int id){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        List<HashMap<String, DynamicType>> dbResult;
        map.put(1, new IntegerType(id));
        try {
            dbResult = con.executeSelect(map, "SELECT * FROM transactions WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"getTransactionById\"", e);
        }
        return getHashMapResult(dbResult);
    }
    /**
     * Insert a role into the database
     * @param name The name of the role
     * @return true if the insert was successful, false otherwise
     */
    public boolean insertRole(String name){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        try {
            return con.executeNoResult(map, "INSERT INTO roles (name) VALUES (?)");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"insertRole\"", e);
        }
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
        try {
            return con.executeNoResult(map, "INSERT INTO users (name, password_hash, role_id) VALUES (?, ?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"insertUser\"", e);
        }
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
        try {
            return con.executeNoResult(map, "INSERT INTO accounts (name, balance, creation_date, owner_id) VALUES (?, ?, ?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"insertAccount\"", e);
        }
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
        try {
            return con.executeNoResult(map, "INSERT INTO transactions (transaction_date, amount, user_from, user_to) VALUES (?, ?, ?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"insertTransaction\"", e);
        }
    }

    /**
     * Change the name of a role
     * @param id ID of the role to change
     * @param name The new name of the role
     * @return true if the update was successful, false otherwise
     */
    public boolean changeRoleName(int id, String name){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        map.put(2, new IntegerType(id));
        try {
            return con.executeNoResult(map, "UPDATE roles SET name = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"changeRoleName\"", e);
        }
    }

    /**
     * Change the name of a user
     * @param id ID of the user to change
     * @param name The new name of the user
     * @return true if the update was successful, false otherwise
     */
    public boolean changeUserName(int id, String name){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        map.put(2, new IntegerType(id));
        try {
            return con.executeNoResult(map, "UPDATE users SET name = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"changeUserName\"", e);
        }
    }

    /**
     * Change the password of a user
     * @param id ID of the user to change
     * @param password The new password of the user
     * @return true if the update was successful, false otherwise
     */
    public boolean changeUserPassword(int id, String password){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(hashPassword(password)));
        map.put(2, new IntegerType(id));
        try {
            return con.executeNoResult(map, "UPDATE users SET password = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"changeUserPassword\"", e);
        }
    }

    /**
     * Change the role of a user
     * @param id ID of the user to change
     * @param roleId ID of the new role of the user (needs to be looked up in the database)
     * @return true if the update was successful, false otherwise
     */
    public boolean changeUserRoleId(int id, int roleId){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new IntegerType(roleId));
        map.put(2, new IntegerType(id));
        try {
            return con.executeNoResult(map, "UPDATE users SET role_id = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"changeUserRoleId\"", e);
        }
    }

    /**
     * Change the name of an account
     * @param id ID of the account to change
     * @param name The new name of the account
     * @return true if the update was successful, false otherwise
     */
    public boolean changeAccountName(int id, String name){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new StringType(name));
        map.put(2, new IntegerType(id));
        try {
            return con.executeNoResult(map, "UPDATE accounts SET name = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"changeAccountName\"", e);
        }
    }

    /**
     * Change the balance of an account
     * @param id ID of the account to change
     * @param balance The new balance of the account
     * @return true if the update was successful, false otherwise
     */
    public boolean changeAccountBalance(int id, Double balance){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new DoubleType(balance));
        map.put(2, new IntegerType(id));
        try {
            return con.executeNoResult(map, "UPDATE accounts SET balance = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"changeAccountBalance\"", e);
        }
    }

    /**
     * Delete a role
     * @param id ID of the role to delete
     * @return true if delete was successful, false otherwise
     */
    public boolean deleteRole(int id){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new IntegerType(id));
        try {
            return con.executeNoResult(map, "DELETE FROM roles WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"deleteRole\"", e);
        }
    }

    /**
     * Delete a user
     * @param id ID of the user to delete
     * @return true if delete was successful, false otherwise
     */
    public boolean deleteUser(int id){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new IntegerType(id));
        try {
            return con.executeNoResult(map, "DELETE FROM users WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"deleteUser\"", e);
        }
    }

    /**
     * Delete an account
     * @param id ID of the account to delete
     * @return true if delete was successful, false otherwise
     */
    public boolean deleteAccount(int id){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new IntegerType(id));
        try {
            return con.executeNoResult(map, "DELETE FROM accounts WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"deleteAccount\"", e);
        }
    }

    /**
     * Delete a transaction
     * @param id ID of the transaction to delete
     * @return true if delete was successful, false otherwise
     */
    public boolean deleteTransaction(int id){
        LinkedHashMap<Integer, DynamicType> map = new LinkedHashMap<>();
        map.put(1, new IntegerType(id));
        try {
            return con.executeNoResult(map, "DELETE FROM transactions WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query \"deleteTransaction\"", e);
        }
    }

    /**
     * Check if the given login credentials are correct
     * @param username Username of the user
     * @param password Password of the user
     * @return true if the credentials are correct, false otherwise
     */
    public boolean checkUserLogin(String username, String password){
        List<HashMap<String, String>> result = getUserByName(username);
        if(result.size() == 1){
            String dbHash = result.get(0).get("password_hash");
            String hash = hashPassword(password);
            return dbHash.equals(hash);
        }
        return false;
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
    public String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String guavaHash(String password){
        String sha256Hex =  Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return Base64.getEncoder().encodeToString(sha256Hex.getBytes());
    }
}
