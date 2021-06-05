import javax.swing.*;
import java.awt.*;

public class QueryResult extends JFrame {
    private JTable queryResult;

    public QueryResult(String[][] query, String[] columnNames) {
        queryResult = new JTable(query, columnNames);
        JScrollPane scrollPane = new JScrollPane(queryResult);

        setLayout(new GridLayout(1,1));
        setVisible(true);
        setSize(new Dimension(800,400));
        add(scrollPane);
    }
}
