package database;

import java.util.LinkedHashMap;

/**
 * This class wraps SQL queries into methods that can be called from outside.
 * They make it easy to execute queries without actually having to write the query.
 */
public class queryWrapper {
    Connector con;
    public queryWrapper(){
        con = new Connector();
    }

    public LinkedHashMap<String, String> getAllAccounts(){

        return null;
    }
}
