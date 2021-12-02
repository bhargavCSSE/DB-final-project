import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

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

    public String printCustomQueryResult(String query) throws Exception {
        Statement stmt = myConn.createStatement(); 
        String queryResult = new String();
        boolean status = stmt.execute(query);
        if(status){
            //query is a select query.
            ResultSet rs = stmt.getResultSet();
            int numOfCols = rs.getMetaData().getColumnCount();
            
            while(rs.next()){
                for(int i = 1; i<=numOfCols; i++) {
                    queryResult += rs.getString(i) + " | ";
                }
                queryResult += "\n";
            }
            rs.close();
        } else {
            //query can be update or any query apart from select query
            int count = stmt.getUpdateCount();
            System.out.println("Total records updated: "+count);
        }
        
        return queryResult;
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

    public List<String[]> getTableTuples(String tableName, String[] columnNames) throws Exception {
        
        Statement stmt = null; 
        ResultSet rslt = null; 
        List<String[]> tableTuples = new ArrayList<String[]>();
        
        try{

            stmt = myConn.createStatement();
            rslt = stmt.executeQuery("SELECT * FROM " + tableName + ";");

            while(rslt.next()) {
                String[] temp = new String[columnNames.length];
                int index = 0;
                for(String col : columnNames){
                    temp[index] = rslt.getString(col);
                    index++;
                }
                tableTuples.add(temp);
            }
        }
        finally {
            close(stmt, rslt);
        }

        return tableTuples;

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