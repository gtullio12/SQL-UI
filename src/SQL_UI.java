import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SQL_UI extends JFrame{

    /** Panel for customer queries **/
    private JPanel customerPanel = new JPanel();

    /** Panel for Checking/Savings account queries **/
    private JPanel moneyAccountPanel = new JPanel();

    /** text boxes for customer panel **/
    private JTextField customerID = new JTextField("Customer ID");
    private JTextField SSN = new JTextField("SSN");
    private JTextField firstName = new JTextField("First Name");
    private JTextField lastName = new JTextField("Last Name");
    private JTextField autoLoanNumber = new JTextField("Auto Loan Number");
    private JTextField mortgageLoanNumber = new JTextField("Mortgage Loan Number");
    private JTextField studentLoanNumber = new JTextField("Student Loan Number");
    private JTextField address = new JTextField("Address");
    private JTextField DOB = new JTextField("DOB");
    private JTextField gender = new JTextField("Gender");
    private JTextField creditScore = new JTextField("Credit score");
    private JTextField email = new JTextField("Email");
    private List<JTextField> components = new ArrayList<>(
            Arrays.asList(customerID,SSN,firstName,lastName,autoLoanNumber,mortgageLoanNumber,
                    studentLoanNumber,address,DOB,gender,creditScore,email)
    );

    /** Components for selecting SQL operation **/
    private String[] operations = {"SELECT", "INSERT", "UPDATE", "DELETE"};
    private JComboBox operationsBox = new JComboBox(operations);

    /** Area to display Query results **/
    private JTextArea displayResults = new JTextArea();

    public SQL_UI() {
        setLayout(new GridLayout(3,1));
        setSize(new Dimension(1000,1000));
        setVisible(true);
        createCustomerPanel();
        createMoneyAccountPanel();
        add(customerPanel);
        add(moneyAccountPanel);
        displayResults.setEnabled(false);
        add(displayResults);
    }

    private void createCustomerPanel() {
        customerPanel.setLayout(new GridLayout(3,13));
        components.forEach(component -> {
            component.addFocusListener(new FocusListener() {
                private String field;
                @Override
                public void focusGained(FocusEvent e) {
                    field = component.getText();
                    component.setText("");
                }

                @Override
                public void focusLost(FocusEvent e) {
                    component.setText(field);
                }
            });

        });
        components.forEach(component -> customerPanel.add(component));
        customerPanel.add(operationsBox);
    }

    private void createMoneyAccountPanel() {

    }
}
