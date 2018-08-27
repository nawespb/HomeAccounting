package app;

import java.util.Date;

public class Product {
    
    private int id;
    private Date date;
    private String group;
    private String name;
    private int amount;
    private double price;
    
    public int getId() {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String print () {
        return "ID: " + id + " Date: " + date +" Group: " + group + " Product name: " + name 
                        + " Amount: " + amount + " Price: " + price;
    }
    
}
