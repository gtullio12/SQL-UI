import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SqlUi extends JFrame{

    /** Field to store the active columns for the query **/
    private String[] columnNames = {"Customer ID", "SSN", "First Name", "Last Name", "Auto Loan Number",
            "Mortgage Loan Number", "Student Loan Number", "Address", "DOB", "Gender", "Credit Score", "Email"};

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
    private JButton executeCustomerQuery = new JButton("Execute");
    private List<JTextField> components = new ArrayList<>(
            Arrays.asList(customerID,SSN,firstName,lastName,autoLoanNumber,mortgageLoanNumber,
                    studentLoanNumber,address,DOB,gender,creditScore,email)
    );

    /** Components for selecting SQL operation **/
    private String[] operations = {"SELECT", "DISPLAY ALL", "INSERT", "DELETE"};
    private JComboBox operationsBoxCustomer = new JComboBox(operations);

    public SqlUi() {
        setLayout(new GridLayout(1,1));
        setSize(new Dimension(650,100));
        setVisible(true);
        setTitle("Customer");
        createCustomerPanel();
        add(customerPanel);
        createListeners();
    }

    private void createListeners() {
        executeCustomerQuery.addActionListener(e -> {
            String[][] result = executeQuery();
            new QueryResult(result, columnNames);
        });
    }

    private String[][] executeQuery() {
        String[][] result = new String[0][0];
        Map<String, String> customerFields = getCustomerFields();
        final String[] buildQuery = {""};
        buildQuery[0] += operationsBoxCustomer.getSelectedItem() + " ";
        switch ((String)operationsBoxCustomer.getSelectedItem()) {
            case "DISPLAY ALL":
                buildQuery[0] = "SELECT * FROM Customer";
                result = SqlConnect.executeCustomerQuery(buildQuery[0]);
                break;
            case "SELECT":
                buildQuery[0] += "* FROM Customer WHERE ";
                customerFields.forEach((key,value)-> {
                    buildQuery[0] += key + "=" + value + " AND ";
                });
                buildQuery[0] = buildQuery[0].substring(0, buildQuery[0].length() - 5); // Remove AND at the end
                result = SqlConnect.executeCustomerQuery(buildQuery[0]);
                break;
            case "DELETE":
                // Delete customer information in other tables
                String customer_id = customerFields.get("Customer_ID");
                String dropBorrowingRecord = "DELETE FROM Borrowing_Record WHERE Customer_ID=" + customer_id;
                String dropCheckingAccount = "DELETE FROM Checking_Account WHERE Customer_ID=" + customer_id;
                String dropSavingsAccount = "DELETE FROM Savings_Account WHERE Customer_ID=" + customer_id;
                String dropDebitCard = "DELETE FROM Debit_Card WHERE Customer_ID=" + customer_id;
                String dropCreditCard = "DELETE FROM Credit_Card WHERE Customer_ID=" + customer_id;
                SqlConnect.executeCustomerUpdate(dropBorrowingRecord);
                SqlConnect.executeCustomerUpdate(dropCheckingAccount);
                SqlConnect.executeCustomerUpdate(dropSavingsAccount);
                SqlConnect.executeCustomerUpdate(dropDebitCard);
                SqlConnect.executeCustomerUpdate(dropCreditCard);
                buildQuery[0] += " FROM Customer WHERE ";

                customerFields.forEach((key,value)-> {
                    buildQuery[0] += key + "=" + value + " AND ";
                });
                buildQuery[0] = buildQuery[0].substring(0, buildQuery[0].length() - 5); // Remove AND at the end
                SqlConnect.executeCustomerUpdate(buildQuery[0]);

                // Display the new table
                buildQuery[0] = "SELECT * FROM Customer";
                result = SqlConnect.executeCustomerQuery(buildQuery[0]);
                break;
            case "INSERT":
                buildQuery[0] += "INTO Customer(";
                customerFields.forEach((key,value)-> {
                    buildQuery[0] += key+",";
                });
                buildQuery[0] = buildQuery[0].substring(0, buildQuery[0].length() - 1); // Remove comma at the end
                buildQuery[0] += ") VALUES(";
                customerFields.forEach((key,value)-> {
                    buildQuery[0] += value+",";
                });
                buildQuery[0] = buildQuery[0].substring(0, buildQuery[0].length() - 1); // Remove comma at the end
                buildQuery[0] += ")";
                SqlConnect.executeCustomerUpdate(buildQuery[0]);
        }
        System.out.println(buildQuery[0]);
        return result;
    }

    private Map<String, String> getCustomerFields() {
        Map<String, String> resultMap = new HashMap<>();
        if (!customerID.getText().equals("Customer ID"))
            resultMap.put("Customer_ID", customerID.getText());
        if (!SSN.getText().equals("SSN"))
            resultMap.put("SSN", SSN.getText());
        if (!firstName.getText().equals("First Name"))
            resultMap.put("First_Name", "\'" + firstName.getText() + "\'");
        if (!lastName.getText().equals("Last Name"))
            resultMap.put("Last_Name", "\'" +lastName.getText()+"\'");
        if (!autoLoanNumber.getText().equals("Auto Loan Number"))
            resultMap.put("Auto_Loan_Number", autoLoanNumber.getText());
        if (!studentLoanNumber.getText().equals("Student Loan Number"))
            resultMap.put("Student_Loan_Number", studentLoanNumber.getText());
        if (!mortgageLoanNumber.getText().equals("Mortgage Loan Number"))
            resultMap.put("Mortgage_Loan_Number", mortgageLoanNumber.getText());
        if (!address.getText().equals("Address"))
            resultMap.put("Address", "\'"+address.getText()+"\'");
        if (!DOB.getText().equals("DOB"))
            resultMap.put("DOB", "\'"+DOB.getText()+"\'");
        if (!gender.getText().equals("Gender"))
            resultMap.put("SSN", gender.getText());
        if (!creditScore.getText().equals("Credit score"))
            resultMap.put("Credit_Score", creditScore.getText());
        if (!email.getText().equals("Email"))
            resultMap.put("Email", "\'"+email.getText()+"\'");
        return resultMap;
    }

    private void createCustomerPanel() {
        customerPanel.setLayout(new GridLayout(3,13));
        components.forEach(component -> {
            component.addFocusListener(new Focus(component));
        });
        components.forEach(component -> customerPanel.add(component));
        customerPanel.add(operationsBoxCustomer);
        customerPanel.add(executeCustomerQuery);
    }

    private void printTable(String[][] result) {
        for (int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

