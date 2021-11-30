package sqlSample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class exec {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String dbName = "Your Database Name";
            String port = "Your Database Server Port";
            String pwd = "Your root Password";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:"
                                    + port + "/" + dbName + "?" +
                                   "user=root&password=" + pwd);
                                   Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Planets");
            while(rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                System.out.println(id + "---" + name);
            }
        } catch (SQLException ex) {
            // handle the error
            System.out.println("SQLException: " + ex.getMessage()); System.out.println("SQLState: " + ex.getSQLState()); System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch (Exception e)
        {
            System.out.println("Unkown Error:" + e.getMessage());
        }
    } 
}