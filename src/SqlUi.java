import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SqlUi extends JFrame{

    /** SQL Connection object. Used for querying database **/
    private SqlConnect sqlConnect = new SqlConnect();

    /** Text to identify the query components **/
    private JLabel customerText = new JLabel("Customer");
    private JLabel accountText = new JLabel("Checking Account");

    /** Panel for customer queries **/
    private JPanel customerPanel = new JPanel();

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

    /** Components for checking account queries **/
    private JPanel checkingAccountPanel = new JPanel();
    private JTextField accountNumber = new JTextField("Account Number");
    private JTextField balance = new JTextField("Balance");
    private JTextField customerIDAccount = new JTextField("Customer ID");

    /** Components for selecting SQL operation **/
    private String[] operations = {"SELECT", "INSERT", "UPDATE", "DELETE"};
    private JComboBox operationsBoxCustomer = new JComboBox(operations);
    private JComboBox operationsBoxAccount = new JComboBox(operations);

    /** Area to display Query results **/
    private JTextArea displayResults = new JTextArea();

    public SqlUi() {
        setLayout(new GridLayout(5,1));
        setSize(new Dimension(600,400));
        setVisible(true);
        createCustomerPanel();
        createMoneyAccountPanel();
        customerText.setEnabled(false);
        customerText.setHorizontalAlignment(SwingConstants.CENTER);
        accountText.setHorizontalAlignment(SwingConstants.CENTER);
        add(customerText);
        add(customerPanel);
        accountText.setEnabled(false);
        add(accountText);
        add(checkingAccountPanel);
        displayResults.setEnabled(false);
        add(displayResults);
    }

    private void createCustomerPanel() {
        customerPanel.setLayout(new GridLayout(3,13));
        components.forEach(component -> {
            component.addFocusListener(new Focus(component));
        });
        components.forEach(component -> customerPanel.add(component));
        customerPanel.add(operationsBoxCustomer);
    }

    private void createMoneyAccountPanel() {
        checkingAccountPanel.setLayout(new GridLayout(1,4));
        accountNumber.addFocusListener(new Focus(accountNumber));
        balance.addFocusListener(new Focus(balance));
        customerIDAccount.addFocusListener(new Focus(customerIDAccount));
        checkingAccountPanel.add(accountNumber);
        checkingAccountPanel.add(balance);
        checkingAccountPanel.add(customerIDAccount);
        checkingAccountPanel.add(operationsBoxAccount);
    }
}
