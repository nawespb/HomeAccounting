package app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
    
    private static final String URL = "jdbc:mysql://localhost:3306/homeaccounting?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public DBConnector() {
        
        try {
            
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = con.createStatement();
           
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        
    }
    
    public void execute (String query) {
        
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println(" : " + count);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException se) {
                
            }
        }
        
    }
    
    public void close () {
        
        try { 
            con.close(); 
        } catch(SQLException se) { 
        }
        try { 
            stmt.close(); 
        } catch(SQLException se) { 
        }
    }
    
}
