package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Connector {

    private String connLink;
    private Connection con;

    public Connector(){
        con = establishConnection();
    }

    private void getConnLink(){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/config.properties"));
            connLink = prop.getProperty("connLink");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection establishConnection(){
        try {
            getConnLink();
            return DriverManager.getConnection(connLink);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
