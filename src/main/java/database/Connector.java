package database;

import database.dynamicType.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * This class is used to establish a connection to an Azure SQL database.
 * It also provides methods to generify the execution of SQL queries.
 */
public class Connector {
    private final Connection con;

    public Connector(){
        con = establishConnection();
        System.out.println("Connection established");
        try {
            System.out.println(con.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //in development

    /**
     * This method can be used to execute SELECT queries on the database.
     * @param map A map of parameters to be used in the query. The parameters MUST be added in the order of appearance in the query. Set to null if no parameters are used.
     * @param query The query to be executed.
     * @return A List of Maps, where each map represents a row in the result set.
     */
    public List<HashMap<String, DynamicType>> executeSelect(LinkedHashMap<Integer, DynamicType> map, String query){
        List<HashMap<String, DynamicType>> result = new ArrayList<>();
        try(CallableStatement cstmt = con.prepareCall(query)) {
            if(map != null) {
                for (Map.Entry<Integer, DynamicType> entry : map.entrySet()) {
                    if(entry.getValue() instanceof StringType){
                        cstmt.setString(entry.getKey(), entry.getValue().toString());
                    } else if(entry.getValue() instanceof IntegerType){
                        cstmt.setInt(entry.getKey(), ((IntegerType) entry.getValue()).value());
                    } else if(entry.getValue() instanceof DoubleType){
                        cstmt.setDouble(entry.getKey(), ((DoubleType) entry.getValue()).value());
                    } else if(entry.getValue() instanceof DateType){
                        cstmt.setDate(entry.getKey(), ((DateType) entry.getValue()).value());
                    } else if(entry.getValue() instanceof BooleanType){
                        cstmt.setBoolean(entry.getKey(), ((BooleanType) entry.getValue()).value());
                    } else {
                        cstmt.setObject(entry.getKey(), ((ObjectType) entry.getValue()).value());
                    }
                }
            }
            try(ResultSet rs = cstmt.executeQuery()){
                //Convert ResultSet to List
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    HashMap<String, DynamicType> row = new HashMap<>();
                    for (int i = 1; i <= columnsNumber; i++) {
                        if(rs.getObject(i) instanceof String){
                            row.put(rsmd.getColumnName(i), new StringType(rs.getString(i)));
                        } else if(rs.getObject(i) instanceof Integer){
                            row.put(rsmd.getColumnName(i), new IntegerType(rs.getInt(i)));
                        } else if(rs.getObject(i) instanceof Double){
                            row.put(rsmd.getColumnName(i), new DoubleType(rs.getDouble(i)));
                        } else if(rs.getObject(i) instanceof Boolean){
                            row.put(rsmd.getColumnName(i), new BooleanType(rs.getBoolean(i)));
                        } else if(rs.getObject(i) instanceof Date) {
                            row.put(rsmd.getColumnName(i), new DateType(rs.getDate(i)));
                        }
                        else {
                            row.put(rsmd.getColumnName(i), new ObjectType(rs.getObject(i)));
                        }
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
     * @param map A map of parameters to be used in the query. The parameters MUST be added in the order of appearance in the query. Set to null if no parameters are used.
     * @param query The query to be executed.
     * @return True if the query was executed successfully, false otherwise.
     */
    public boolean executeNoResult(LinkedHashMap<Integer, DynamicType> map, String query){
        try(CallableStatement cstmt = con.prepareCall(query)) {
            if(map != null) {
                for (Map.Entry<Integer, DynamicType> entry : map.entrySet()) {
                    if (entry.getValue() instanceof StringType) {
                        cstmt.setString(entry.getKey(), ((StringType) entry.getValue()).value());
                    } else if (entry.getValue() instanceof IntegerType) {
                        cstmt.setInt(entry.getKey(), ((IntegerType) entry.getValue()).value());
                    } else if (entry.getValue() instanceof DoubleType) {
                        cstmt.setDouble(entry.getKey(), ((DoubleType) entry.getValue()).value());
                    } else if (entry.getValue() instanceof BooleanType) {
                        cstmt.setBoolean(entry.getKey(), ((BooleanType) entry.getValue()).value());
                    } else if (entry.getValue() instanceof DateType) {
                        cstmt.setDate(entry.getKey(), ((DateType) entry.getValue()).value());
                    } else if (entry.getValue() instanceof ByteArrayType) {
                        cstmt.setBytes(entry.getKey(), ((ByteArrayType) entry.getValue()).value());
                    } else {
                        cstmt.setObject(entry.getKey(), entry.getValue());
                    }
                }
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
