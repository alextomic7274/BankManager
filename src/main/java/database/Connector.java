package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * This class is used to establish a connection to an Azure SQL database.
 * It also provides methods to generify the execution of SQL queries.
 */
public class Connector {
    private Connection con;

    public Connector(){
        con = establishConnection();
    }

    //in development

    /**
     * This method can be used to execute SELECT queries on the database.
     * @param map A map of parameters to be used in the query. The parameters MUST be added in the order of appearance in the query.
     * @param query The query to be executed.
     * @return A List of Maps, where each map represents a row in the result set.
     * @param <T> The parameter value
     */
    public <T> List<HashMap<String, Object>> executeSelect(LinkedHashMap<String, T> map, String query){
        List<HashMap<String, Object>> result = new ArrayList<>();
        try(CallableStatement cstmt = con.prepareCall(query)) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                cstmt.setObject(entry.getKey(), entry.getValue());
            }
            try(ResultSet rs = cstmt.executeQuery()){
                //Convert ResultSet to List
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    HashMap<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnsNumber; i++) {
                        row.put(rsmd.getColumnName(i), rs.getObject(i));
                    }
                    result.add(row);
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method can be used to execute INSERT, UPDATE, DELETE queries on the database.
     * @param map A map of parameters to be used in the query. The parameters MUST be added in the order of appearance in the query.
     * @param query The query to be executed.
     * @return True if the query was executed successfully, false otherwise.
     * @param <T> The parameter value
     */
    public <T> boolean executeNoResult(LinkedHashMap<String, T> map, String query){
        try(CallableStatement cstmt = con.prepareCall(query)) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                cstmt.setObject(entry.getKey(), entry.getValue());
            }
            cstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get the link to connect to an Azure SQL database out of a .properties file.
     * @return the connection link
     */
    private String getConnLink(){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/config.properties"));
            return prop.getProperty("connLink");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Establish a connection to an Azure SQL database.
     * @return the connection
     */
    private Connection establishConnection(){
        try {
            getConnLink();
            return DriverManager.getConnection(getConnLink());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
