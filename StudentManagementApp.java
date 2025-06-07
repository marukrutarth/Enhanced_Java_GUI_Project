
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementApp extends JFrame {
    private JTextField nameField, rollField;
    private DefaultTableModel tableModel;
    private JTable table;

    public StudentManagementApp() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll No:"));
        rollField = new JTextField();
        inputPanel.add(rollField);

        JButton addButton = new JButton("Add Student");
        JButton deleteButton = new JButton("Delete Selected");
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Roll No", "Name"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            if (name.isEmpty() || roll.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            tableModel.addRow(new String[]{roll, name});
            nameField.setText("");
            rollField.setText("");
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Select a row to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementApp().setVisible(true));
    }
}
