package ie.atu;

import java.sql.*;

public class Insert {
    public static void main(String[] args) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/exampledatabase", "root", "root");

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            stmt.setString(1, "Sean");
            stmt.setString(2, "Software Engineering");
            stmt.executeUpdate();

            stmt = conn.prepareStatement("INSERT INTO emails (user_id, email) VALUES (?, ?)");
            stmt.setInt(1, getLastInsertId(conn));
            stmt.setString(2, "SeanGrehan@atu.ie");
            stmt.executeUpdate();

            System.out.println("Insert completed successfully.");
        }
        catch (SQLException ex){

            System.out.println("Record insert failed");
            ex.printStackTrace();
        }
        conn.close();
    }
    private static int getLastInsertId(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        stmt.close();
        return id;
    }
}
