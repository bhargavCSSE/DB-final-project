import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;

public class dbDAO {

    private Connection myConn;

    public dbDAO() throws Exception {
        Properties dbProp = new Properties();
        dbProp.load(new FileInputStream("dao.properties"));

        String user = dbProp.getProperty("user");
        String password = dbProp.getProperty("password");
        String dburl = dbProp.getProperty("dburl");

        myConn = DriverManager.getConnection(dburl, user, password);

        System.out.println("Connect to database: " + dburl);
    }

    public void printQueryResult() throws Exception {
        Statement stmt = null; 
        ResultSet rslt = null; 

        try{
            stmt = myConn.createStatement();
            rslt = stmt.executeQuery("SELECT * FROM book");

            while(rslt.next()) {
                int id = rslt.getInt("BookID");
                String name = rslt.getString("Title");
                System.out.println(id + "---" + name);
            }
        }
        finally {
            rslt.close();
            myConn.close();
        }
    }

    public static void main(String[] args) throws Exception {
		
		dbDAO dao = new dbDAO();
        dao.printQueryResult();
		
	}
}