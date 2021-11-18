package kata5p1.db.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MailListReaderDB {

    public static List<String> read(String fileName) {

        ArrayList<String> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            while (true) {
                String lineText = reader.readLine();
                if (lineText == null) {
                    break;
                }
                if (lineText.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                    list.add(lineText);
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR MailListReader::read FileNotFoundException" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR MailListReader::read IOException" + ex.getMessage());
        }
        return list;
    }

    public static void insertIntoDBFromFile(Connection conn, String filename) {
        String sql = "INSERT INTO EMAIL(direccion) VALUES(?)";
        List<String> readMails = read(filename);
        for (String email : readMails) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
