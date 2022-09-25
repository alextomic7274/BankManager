package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Connector {
    private Connection con;

    public Connector(){
        con = establishConnection();
    }

    //in development
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
