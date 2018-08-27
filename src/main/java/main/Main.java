package main;

import app.DBConnector;
import gui.MainFrame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    
    public static void main(String[] args) throws ParseException {
        DBConnector db = new DBConnector();
        
//        db.insert(new java.util.Date(),"ALCOHOL", "BEER", 5, 1);
//        db.insert(new java.util.Date(),"ALCOHOL", "WINE", 12, 1);
//        db.insert(new java.util.Date(),"ALCOHOL", "CONIAC", 2, 1);
//        
//        db.select(2);
//        db.update(3, "RYAZHENKA", 42);
//        db.delete(3);
//
//        String dt = "2018.07.02";
//        SimpleDateFormat format = new SimpleDateFormat();
//        format.applyPattern("yyyy.MM.dd");
//        Date docDate= format.parse(dt);
//
//        ArrayList<String> arr = new ArrayList();
//        arr = db.selectByDate(docDate, docDate);
//        for (String s: arr) {
//            System.out.println(s);
//        }
//        db.close();
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
    }
    
}
