package main;

import app.DBConnector;

public class Main {
    
    public static void main(String[] args) {
        DBConnector db = new DBConnector();
        db.executeInsert("MILK", "KEFIR", 75);
        db.executeSelect(2);
        db.close();
    }
    
}
