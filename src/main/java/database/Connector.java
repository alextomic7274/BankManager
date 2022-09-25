package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Connector {
    private Connection con;

    public Connector(){
        con = establishConnection();
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
