package main;

import app.DBConnector;

public class Main {
    
    public static void main(String[] args) {
        DBConnector db = new DBConnector();
        db.execute("SELECT * FROM accounting LIMIT 100;");
        db.close();
    }
    
}
