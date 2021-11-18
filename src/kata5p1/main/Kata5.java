package kata5p1.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import kata5p1.db.utils.MailListReaderDB;

public class Kata5 {

    private static String filename;
    
    public static void main(String[] args) {
        filename = "email.txt";
        Connection connection = connect("KATA5.db");
        MailListReaderDB.insertIntoDBFromFile(connection, filename);
    }
    
    
    private static Connection connect(String dbName) {
        String url = "jdbc:sqlite:" + dbName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
