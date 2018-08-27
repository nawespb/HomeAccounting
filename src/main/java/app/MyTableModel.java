package app;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MyTableModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private List<Product> products;
    
    
    public MyTableModel (List<Product> products) {
         
        
        this.products = products;
        
     }
     
    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Дата";
            case 2:
                return "Группа";
            case 3:
                return "Название";
            case 4:
                return "Количество";
            case 5:
                return "Цена";
            }
            return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return Date.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
            case 5:
                return Double.class;    
            }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Product prod = products.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return prod.getId();
            case 1:
                return prod.getDate();
            case 2:
                return prod.getGroup();
            case 3:
                return prod.getName();
            case 4:
                return prod.getAmount();
            case 5:
                return prod.getPrice();    
            }
            return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
    
    public void removeRow (int row) {
        products.remove(row);
    }
    
    public Product getProduct (int row) {
        return products.get(row);
    }

    public void editRowDate (int row, Date date) {
        products.get(row).setDate(date);
    }
    
    public void editRowGroup (int row, String group) {
        products.get(row).setGroup(group);
    }
    
    public void editRowProductName (int row, String name) {
        products.get(row).setName(name);
    }
    
    public void editRowAmount (int row, int amount) {
        products.get(row).setAmount(amount);
    }
    
    public void editRowPrice (int row, double price) {
        products.get(row).setPrice(price);
    }
    
    public double getSumm () {
        double sum = 0;
        for (Product pr: products) {
            sum += pr.getPrice();
        }
        return sum;
    }
}
