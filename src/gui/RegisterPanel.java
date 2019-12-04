package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegisterPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(550, 400);
        RegisterPanel p = new RegisterPanel();

        frame.add(p);
        frame.setVisible(true);

    }

    public RegisterPanel () {
        createPanel();
    }

    private void createPanel() {
        createTable();
        this.add(table);
    }

    private void createTable() {
        this.model = new DefaultTableModel();
        this.model.addColumn("Register");
        this.model.addColumn("Value");
        this.table = new JTable(this.model);
    }

    public void setTable(String register, int values) {
        for(int i = 0; i < 32; i++) {
        }
    }

    public void updateTable(String data, int row, int column) {
        this.model.setValueAt(data, row, column);
    }

}
