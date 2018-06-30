package app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DBConnector {
    
    private static final String URL = "jdbc:mysql://localhost:3306/homeaccounting?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    
    private static final String SELECT = "SELECT ID, PRODUCT_NAME FROM accounting WHERE ID = ?;";
    private static final String SELECT_ALL = "SELECT * FROM accounting LIMIT ?;";
    private static final String SELECT_BY_DATE = "SELECT * FROM accounting "
            + "WHERE SPENDING_DATE BETWEEN ? AND ?;";
    private static final String INSERT = "INSERT INTO accounting "
            + "(SPENDING_DATE, PRODUCT_GROUP, PRODUCT_NAME, PRICE) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE accounting "
            + "SET PRODUCT_NAME = ?, PRICE = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM accounting WHERE ID = ?;";
    
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
    
    public String select (int id) {
        
        String result = "";
        
        try {
            
            prep = con.prepareStatement(SELECT);
            prep.setInt(1, id);
            rs = prep.executeQuery();
            while (rs.next()) {
                int pId = rs.getInt(1);
                String name = rs.getString(2);
                result = pId + name;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String> selectAll (int limit) {
        
        ArrayList<String> arr = new ArrayList<>();
        try {
            
            prep = con.prepareStatement(SELECT_ALL);
            prep.setInt(1, limit);
            rs = prep.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(4);
                arr.add("ID: " + id + ": " + name);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }
    
    public ArrayList<String> selectByDate (Date from, Date to) {
        
        ArrayList<String> arr = new ArrayList<>();
        try {
            
            prep = con.prepareStatement(SELECT_BY_DATE);
            prep.setDate(1, new java.sql.Date(from.getTime()));
            prep.setDate(2, new java.sql.Date(to.getTime()));
            rs = prep.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(4);
                arr.add("ID: " + id + ": " + name);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }
    
    public void insert (Date date, String group, String name, int price) {
        try {
            
            prep = con.prepareStatement(INSERT);
            prep.setDate(1, new java.sql.Date(date.getTime()));
            prep.setString(2, group);
            prep.setString(3, name);
            prep.setInt(4, price);
            prep.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void update (int id, String name, int price) {
        try {
            
            prep = con.prepareStatement(UPDATE);
            prep.setString(1, name);
            prep.setInt(2, price);
            prep.setInt(3, id);
            prep.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void delete (int id) {
        try {
            
            prep = con.prepareStatement(DELETE);
            prep.setInt(1, id);
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
