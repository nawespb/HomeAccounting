package app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DBConnector {
    
    private static final String URL = "jdbc:mysql://localhost:3306/homeaccounting?useSSL=false&useUnicode=true&amp;characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    
    private static final String SELECT = "SELECT * FROM accounting WHERE ID = ?;";
    private static final String SELECT_ALL = "SELECT * FROM accounting LIMIT ?;";
    private static final String SELECT_BY_DATE = "SELECT * FROM accounting "
            + "WHERE SPENDING_DATE BETWEEN ? AND ?;";
    private static final String SELECT_BY_DATE_1 = "SELECT * FROM accounting "
            + "WHERE SPENDING_DATE <= ?;";
    private static final String SELECT_BY_DATE_2 = "SELECT * FROM accounting "
            + "WHERE SPENDING_DATE >= ?;";
    private static final String INSERT = "INSERT INTO accounting "
            + "(SPENDING_DATE, PRODUCT_GROUP, PRODUCT_NAME, AMOUNT, PRICE) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE accounting "
            + " SET SPENDING_DATE = ?, PRODUCT_GROUP = ?, PRODUCT_NAME = ?, AMOUNT = ?, PRICE = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM accounting WHERE ID = ?;";
    
    private Connection con;
    private ResultSet rs;
    private PreparedStatement prep;
        

    public DBConnector() {
        
        try {
            
            con = DriverManager.getConnection(URL, USER, PASSWORD);           
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public ArrayList<Product> selectAll (int limit) {
        
        ArrayList<Product> arr = new ArrayList<>();
        try {
            prep = con.prepareStatement(SELECT_ALL);
            prep.setInt(1, limit);
            rs = prep.executeQuery();
            while (rs.next()) {
                Product prod = new Product();
                prod.setId(rs.getInt(1));
                prod.setDate(rs.getDate(2));
                prod.setGroup(rs.getString(3));
                prod.setName(rs.getString(4));
                prod.setAmount(rs.getInt(5));
                prod.setPrice(rs.getDouble(6));
                arr.add(prod);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }
    
    public Product selectByID (int id) {
        
        Product result = new Product();
        
        try {
            prep = con.prepareStatement(SELECT);
            prep.setInt(1, id);
            rs = prep.executeQuery();
            while (rs.next()) {
                result.setId(rs.getInt(1));
                result.setDate(rs.getDate(2));
                result.setGroup(rs.getString(3));
                result.setName(rs.getString(4));
                result.setAmount(rs.getInt(5));
                result.setPrice(rs.getDouble(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Product> selectByDate (Date from, Date to) {
        
        ArrayList<Product> arr = new ArrayList<>();
        if (from != null && to != null) {
            try {
                prep = con.prepareStatement(SELECT_BY_DATE);
                prep.setDate(1, new java.sql.Date(from.getTime()));
                prep.setDate(2, new java.sql.Date(to.getTime()));
                rs = prep.executeQuery();
                while (rs.next()) {
                    Product prod = new Product();
                    prod.setId(rs.getInt(1));
                    prod.setDate(rs.getDate(2));
                    prod.setGroup(rs.getString(3));
                    prod.setName(rs.getString(4));
                    prod.setAmount(rs.getInt(5));
                    prod.setPrice(rs.getDouble(6));
                    arr.add(prod);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (from != null) {
            try {
                prep = con.prepareStatement(SELECT_BY_DATE_2);
                prep.setDate(1, new java.sql.Date(from.getTime()));
                rs = prep.executeQuery();
                while (rs.next()) {
                    Product prod = new Product();
                    prod.setId(rs.getInt(1));
                    prod.setDate(rs.getDate(2));
                    prod.setGroup(rs.getString(3));
                    prod.setName(rs.getString(4));
                    prod.setAmount(rs.getInt(5));
                    prod.setPrice(rs.getDouble(6));
                    arr.add(prod);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (to != null){
            try {
                prep = con.prepareStatement(SELECT_BY_DATE_1);
                prep.setDate(1, new java.sql.Date(to.getTime()));
                rs = prep.executeQuery();
                while (rs.next()) {
                    Product prod = new Product();
                    prod.setId(rs.getInt(1));
                    prod.setDate(rs.getDate(2));
                    prod.setGroup(rs.getString(3));
                    prod.setName(rs.getString(4));
                    prod.setAmount(rs.getInt(5));
                    prod.setPrice(rs.getDouble(6));
                    arr.add(prod);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }   
        return arr;
    }
    
    public void insert (Date date, String group, String name, int amount, double price) {
        try {
            
            prep = con.prepareStatement(INSERT);
            prep.setDate(1, new java.sql.Date(date.getTime()));
            prep.setString(2, group);
            prep.setString(3, name);
            prep.setInt(4, amount);
            prep.setDouble(5, price);
            prep.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void update (int id, Date date, String group, String name, int amount, double price) {
        try {
            
            prep = con.prepareStatement(UPDATE);
            prep.setDate(1, new java.sql.Date(date.getTime()));
            prep.setString(2, group);
            prep.setString(3, name);
            prep.setInt(4, amount);
            prep.setDouble(5, price);
            prep.setInt(6, id);
            prep.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteByID (int id) {
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
            con.close(); 
        } catch(SQLException se) { 
        }
    }
}
