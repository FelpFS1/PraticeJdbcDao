package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    static Connection conn = null;

    public static Connection getConnection(){

        try{
            Properties properties = loadProperties();
            String url = properties.getProperty("dburl");
            conn = DriverManager.getConnection(url,properties);
            return conn;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }

    }

    public static void closeConnection(){
        if(conn != null){
            try {
            conn.close();

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties(){
        try(FileInputStream stream = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(stream);

            return properties;

        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(PreparedStatement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
