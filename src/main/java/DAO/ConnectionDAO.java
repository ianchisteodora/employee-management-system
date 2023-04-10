package DAO;
import java.sql.*;

public class ConnectionDAO {
    public static Connection getCon() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sd1?useSSL=false", "root", "Teodora20!");
            return con;

        }catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found");
        } catch (SQLException e) {
            throw new SQLException("Error connecting to database: " + e.getMessage());
        }
    }
}
