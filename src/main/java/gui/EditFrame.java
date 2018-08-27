package gui;

import app.DBConnector;
import app.Filter;
import app.MyTableModel;
import app.Product;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EditFrame extends JFrame {
    
    private final DBConnector db;
    private final MyTableModel tModel;
    private final JTable table;
    
    private final JTextField date1 = new JTextField();
    private final JTextField date2 = new JTextField();
    private final JTextField date3 = new JTextField();
    private final JTextField group = new JTextField();
    private final JTextField product_name = new JTextField();
    private final JTextField amount = new JTextField();
    private final JTextField price = new JTextField();
    private final JTextField id = new JTextField();
    private final JButton btn = new JButton("Применить");
    private final JLabel date_label = new JLabel("Дата:");
    private final JLabel group_label = new JLabel("Товарная группа:");
    private final JLabel product_name_label = new JLabel("Наименование:");
    private final JLabel amount_label = new JLabel("Кол-во:");
    private final JLabel price_label = new JLabel("Цена:");
    
    private final Filter filter = new Filter();
    
    public EditFrame(DBConnector db, JTable table, MyTableModel tModel, int row){
        
        super("Изменение данных");
        this.db = db;
        this.table = table;
        this.tModel = tModel;
        
        setBounds(100, 25, 580, 150);
        Container container = this.getContentPane();
        container.setLayout(null);
        setResizable(false);
        
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(false);
                dispose();}
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        //TextFields
        
        date1.setSize(23, 25);
        date1.setLocation(10, 30);
        date1.setDocument(filter.numberFilter(2));
        add(date1);
        date2.setSize(23, 25);
        date2.setLocation(35, 30);
        date2.setDocument(filter.numberFilter(2));
        add(date2);
        date3.setSize(35, 25);
        date3.setLocation(60, 30);
        date3.setDocument(filter.numberFilter(4));
        add(date3);
        group.setSize(150, 25);
        group.setLocation(105, 30);
        add(group);
        product_name.setSize(150, 25);
        product_name.setLocation(265, 30);
        add(product_name);
        amount.setSize(50, 25);
        amount.setLocation(425, 30);
        amount.setDocument(filter.numberFilter(3));
        add(amount);
        price.setSize(75, 25);
        price.setLocation(485, 30);
        price.setDocument(filter.doubleNumberFilter(10));
        add(price);
        
        date_label.setSize(85, 25);
        date_label.setLocation(10, 5);
        add(date_label);
        group_label.setSize(150, 25);
        group_label.setLocation(105, 5);
        add(group_label);
        product_name_label.setSize(150, 25);
        product_name_label.setLocation(265, 5);
        add(product_name_label);
        amount_label.setSize(50, 25);
        amount_label.setLocation(425, 5);
        add(amount_label);
        price_label.setSize(75, 25);
        price_label.setLocation(485, 5);
        add(price_label);
        
        btn.setSize(150, 25);
        btn.setLocation(215, 70);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit(row);
                table.updateUI();
                dispose();
            }
        });
        add(btn);
        
        
    }
    
    private void edit (int row) {
        
        Product pr = tModel.getProduct(row);
        Date date = filter.getDate(date1.getText(), date2.getText(), date3.getText());
        String groupE;
        String nameE;
        int amountE;
        double priceE;
        
        if (date != null) {
            tModel.editRowDate(row, date);
        } else date = pr.getDate();
        if (!group.getText().trim().isEmpty()) {
            tModel.editRowGroup(row, group.getText());
            groupE = group.getText();
        } else groupE = pr.getGroup();
        if (!product_name.getText().trim().isEmpty()) {
            tModel.editRowProductName(row, product_name.getText());
            nameE = product_name.getText();
        } else nameE = pr.getName();
        if (!amount.getText().trim().isEmpty()) {
            tModel.editRowAmount(row, Integer.parseInt(amount.getText().trim()));
            amountE = Integer.parseInt(amount.getText().trim());
        } else amountE = pr.getAmount();
        if (!price.getText().trim().isEmpty()) {
            tModel.editRowPrice(row, Double.parseDouble(price.getText().trim()));
            priceE = Double.parseDouble(price.getText().trim());
        } else priceE = pr.getPrice();
        
        db.update(pr.getId(), date, groupE, nameE, amountE, priceE);
    }
}
