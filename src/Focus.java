import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Focus implements FocusListener {
    private String field;
    private JTextField component;

    public Focus(JTextField field) {
        component = field;
    }
    @Override
    public void focusGained(FocusEvent e) {
        field = component.getText();
        component.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        component.setText(field);
    }
}
