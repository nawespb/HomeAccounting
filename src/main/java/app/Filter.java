package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Filter {
    
    public PlainDocument numberFilter (int number) {
        return new PlainDocument() {
            
            @Override
            public void insertString (int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if (((getLength() + str.length()) <= number) && isInteger(str)) {
                    super.insertString(offset, str, attr);
                }
            }
        };
    }
    
    public PlainDocument doubleNumberFilter (int number) {
        return new PlainDocument() {
            
            @Override
            public void insertString (int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if (((getLength() + str.length()) <= number) && isDouble(str)) {
                    super.insertString(offset, str, attr);
                }
            }
        };
    }
    
    public boolean isInteger (String str) {
        return str.matches("[-+]?\\d+");
    }
    
    public boolean isDouble (String str) {
        return isInteger(str) || contains(str, ".");
    }
    
    public boolean contains(String str, String substr){
        return str.contains(substr);
    }
    
    public Date getDate (String day, String month, String year) {
        
        String dateStr = year + "." + month + "." + day;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy.MM.dd");
        if (!day.trim().isEmpty() && !month.trim().isEmpty() && !year.trim().isEmpty()) {
            try {
                return format.parse(dateStr);
            } catch (ParseException ex) {
                System.out.println(dateStr);
                System.out.println("Ошибка преобразовани даты. Возможно введенная дата некорректна");
                return null;
            }
        }
        return null;
    }
}
