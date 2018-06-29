package app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
    
    private static final String URL = "jdbc:mysql://localhost:3306/homeaccounting?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    
    private static final String SELECT = "SELECT ID, PRODUCT_NAME FROM accounting where id = ?;";
    private static final String INSERT = "INSERT INTO accounting (PRODUCT_GROUP, PRODUCT_NAME, PRICE) VALUES (?, ?, ?);";
    
    private Connection con;
    private ResultSet rs;
    private PreparedStatement prep;
        

    public DBConnector() {
        
        try {
            
            con = DriverManager.getConnection(URL, USER, PASSWORD);           
           
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        
    }
    
public void executeSelect (int id) {
        
        try {
            
            prep = con.prepareStatement(SELECT);
            prep.setInt(1, id);
            rs = prep.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println(count + " " + name);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void executeInsert (String group, String name, int price) {
        try {
            
            prep = con.prepareStatement(INSERT);
            prep.setString(1, group);
            prep.setString(2, name);
            prep.setInt(3, price);
            prep.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void close () {
        
        try { 
            prep.close(); 
        } catch(SQLException se) { 
        }
        try { 
            con.close(); 
        } catch(SQLException se) { 
        }
        
    }
}
