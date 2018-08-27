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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MainFrame extends JFrame {
    
    
    private final JButton insert = new JButton("Добавить");
    private final JButton search = new JButton("Поиск");
    private final JButton change = new JButton("Изменить");
    private final JButton delete = new JButton("Удалить");
    private final JTextField date1 = new JTextField();
    private final JTextField date2 = new JTextField();
    private final JTextField date3 = new JTextField();
    private final JTextField group = new JTextField();
    private final JTextField product_name = new JTextField();
    private final JTextField amount = new JTextField();
    private final JTextField price = new JTextField();
    private final JTextField id = new JTextField();
    private final JTextField dateFrom1 = new JTextField ();
    private final JTextField dateFrom2 = new JTextField ();
    private final JTextField dateFrom3 = new JTextField ();
    private final JTextField dateTo1 = new JTextField ();
    private final JTextField dateTo2 = new JTextField ();
    private final JTextField dateTo3 = new JTextField ();
    private final JTextField summ = new JTextField ();
    private final JLabel date_label = new JLabel("Дата ( Д/М/Год ):");
    private final JLabel group_label = new JLabel("Товарная группа:");
    private final JLabel product_name_label = new JLabel("Наименование:");
    private final JLabel amount_label = new JLabel("Кол-во:");
    private final JLabel price_label = new JLabel("Цена:");
    private final JLabel id_label = new JLabel("ID:");
    private final JLabel dateFrom_label = new JLabel("Дата с ( Д/М/Год ):");
    private final JLabel dateTo_label = new JLabel("По ( Д/М/Год ):");
    private ArrayList<Product> productArray = new ArrayList<>();
    private final MyTableModel tModel = new MyTableModel(productArray);
    private final JTable table = new JTable(tModel);
    private final JScrollPane jSPane = new JScrollPane(table);
    private final Filter filter = new Filter();
    private final DBConnector db = new DBConnector();
    
    public MainFrame() {
        
        super("Домашняя бухгалтерия");
        
        setBounds(100, 25, 1000, 650);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(null);
        setResizable(false);
        setColumnWidths(table, 50, 100, 400, 400, 100, 150);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                db.close();
                System.exit(0);
            }
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
        
//        result.setColumnSelectionAllowed(false);
//        result.setCellSelectionEnabled(false);

        //TextFields
        
        date1.setSize(25, 25);
        date1.setLocation(25, 50);
        date1.setDocument(filter.numberFilter(2));
        add(date1);
        date2.setSize(25, 25);
        date2.setLocation(55, 50);
        date2.setDocument(filter.numberFilter(2));
        add(date2);
        date3.setSize(40, 25);
        date3.setLocation(85, 50);
        date3.setDocument(filter.numberFilter(4));
        add(date3);
        group.setSize(200, 25);
        group.setLocation(150, 50);
        add(group);
        product_name.setSize(200, 25);
        product_name.setLocation(375, 50);
        add(product_name);
        amount.setSize(50, 25);
        amount.setLocation(600, 50);
        amount.setDocument(filter.numberFilter(3));
        add(amount);
        price.setSize(75, 25);
        price.setLocation(675, 50);
        price.setDocument(filter.doubleNumberFilter(10));
        add(price);
//        id.setSize(75, 25);
//        id.setLocation(375, 100);
//        id.setDocument(filter.numberFilter(8));
//        add(id);
        dateFrom1.setSize(25, 25);
        dateFrom1.setLocation(475, 100);
        dateFrom1.setDocument(filter.numberFilter(2));
        add(dateFrom1);
        dateFrom2.setSize(25, 25);
        dateFrom2.setLocation(505, 100);
        dateFrom2.setDocument(filter.numberFilter(2));
        add(dateFrom2);
        dateFrom3.setSize(40, 25);
        dateFrom3.setLocation(535, 100);
        dateFrom3.setDocument(filter.numberFilter(4));
        add(dateFrom3);
        dateTo1.setSize(25, 25);
        dateTo1.setLocation(625, 100);
        add(dateTo1);
        dateTo2.setSize(25, 25);
        dateTo2.setLocation(655, 100);
        add(dateTo2);
        dateTo3.setSize(40, 25);
        dateTo3.setLocation(685, 100);
        add(dateTo3);
        summ.setSize(100, 25);
        summ.setLocation(25, 575);
        summ.setEditable(false);
        add(summ);
        
        //Labels
        
        date_label.setSize(100, 25);
        date_label.setLocation(25, 25);
        add(date_label);
        group_label.setSize(200, 25);
        group_label.setLocation(150, 25);
        add(group_label);
        product_name_label.setSize(200, 25);
        product_name_label.setLocation(375, 25);
        add(product_name_label);
        amount_label.setSize(50, 25);
        amount_label.setLocation(600, 25);
        add(amount_label);
        price_label.setSize(75, 25);
        price_label.setLocation(675, 25);
        add(price_label);
//        id_label.setSize(75, 25);
//        id_label.setLocation(375, 75);
//        add(id_label);
        dateFrom_label.setSize(125, 25);
        dateFrom_label.setLocation(475, 75);
        add(dateFrom_label);
        dateTo_label.setSize(125, 25);
        dateTo_label.setLocation(625, 75);
        add(dateTo_label);
        
        //Buttons
        
        insert.setSize(150, 25);
        insert.setLocation(825, 50);
        insert.setFocusable(false);
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enterFieldsIsEmpty()) {
                    Date date = filter.getDate(date1.getText(), date2.getText(), date3.getText());
                    if (date == null) {
                        db.insert(new Date(), group.getText(), product_name.getText(), 
                                Integer.parseInt(amount.getText()), Double.parseDouble(price.getText()));
                    } else {
                        db.insert(date, group.getText(), product_name.getText(), 
                                Integer.parseInt(amount.getText()), Double.parseDouble(price.getText()));
                    } 
                    clearEnterFields();
                    date1.requestFocus();
                }
            }
        });
        add(insert);
        
        search.setSize(150, 25);
        search.setLocation(825, 100);
        search.setFocusable(false);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Date dateTo = filter.getDate(dateTo1.getText(), dateTo2.getText(), dateTo3.getText());
                Date dateFrom = filter.getDate(dateFrom1.getText(), dateFrom2.getText(), dateFrom3.getText());
                productArray.clear();
                
                if (dateTo == null && dateFrom == null) {
                    for (Product p: db.selectAll(100)) {
                        productArray.add(p);
                    } table.updateUI();
                } else {
                    for (Product p: db.selectByDate(dateFrom, dateTo)) {
                        productArray.add(p);
                    } table.updateUI();
                } table.updateUI();
                summ.setText("Итого: " + tModel.getSumm());
            }
        });
        add(search);
        
        change.setSize(100, 25);
        change.setLocation(750, 575);
        change.setFocusable(false);
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                new EditFrame(db, table, tModel, row).setVisible(true);
            }
        });
        add(change);
        
        delete.setSize(100, 25);
        delete.setLocation(875, 575);
        delete.setFocusable(false);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Product pr = tModel.getProduct(table.getSelectedRow());
                db.deleteByID(pr.getId());
                tModel.removeRow(table.getSelectedRow());
                table.updateUI();
            }
        });
        add(delete);
        
        //JList with ScrollBar
        
        jSPane.setSize(950,400);
        jSPane.setLocation(25, 150);
        getContentPane().add(jSPane);
    }
    
    
    
    public boolean enterFieldsIsEmpty () {
        Date date = filter.getDate(date1.getText(), date2.getText(), date3.getText());
        return (date == null && group.getText().trim().isEmpty() && product_name.getText().trim().isEmpty() 
                && amount.getText().trim().isEmpty() && price.getText().trim().isEmpty());
    }
    
    public void clearEnterFields () {
        
        date1.setText("");
        date2.setText("");
        date3.setText("");
        group.setText("");
        product_name.setText("");
        amount.setText("");
        price.setText("");
    }
    
    public static void setColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            }
            else break;
        }
    }
}
