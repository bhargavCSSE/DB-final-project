import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
            close(stmt, rslt);
        }
    }

    public List<String> getTableList() throws Exception {
        Statement stmt = null; 
        ResultSet rslt = null; 
        List<String> tableNames = new ArrayList<String>();

        try{

            stmt = myConn.createStatement();
            rslt = stmt.executeQuery("SHOW TABLES;");

            while(rslt.next()) {
                String tableName = rslt.getString("Tables_in_db_sys_class");
                tableNames.add(tableName);
            }
        }
        finally {
            close(stmt, rslt);
        }

        return tableNames;

    }

    public List<String> getTableColumnList(String tableName) throws Exception {
        
        Statement stmt = null; 
        ResultSet rslt = null; 
        List<String> columnNames = new ArrayList<String>();

        try{

            stmt = myConn.createStatement();
            rslt = stmt.executeQuery("SHOW COLUMNS FROM " + tableName +";");

            while(rslt.next()) {
                String columnName = rslt.getString("Field");
                String columnDataType = rslt.getString("Type");
                columnNames.add(columnName);
            }
        }
        finally {
            close(stmt, rslt);
        }

        return columnNames;

    }

    public List<String> getTableTuples(String tableName) throws Exception {
        
        Statement stmt = null; 
        ResultSet rslt = null; 
        List<String> columnNames = new ArrayList<String>();

        try{

            stmt = myConn.createStatement();
            rslt = stmt.executeQuery("SELECT * FROM " + tableName + ";");

            while(rslt.next()) {
                int id = rslt.getInt("BookID");
                String name = rslt.getString("Title");
                columnNames.add(id + " : " + name);
            }
        }
        finally {
            close(stmt, rslt);
        }

        return columnNames;

    }



    private static void close(Connection myConn, Statement stmt, ResultSet rslt)
			throws SQLException {

		if (rslt != null) {
			rslt.close();
		}

		if (stmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement stmt, ResultSet rslt) throws SQLException {
		close(null, stmt, rslt);		
	}

}