package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class ProgramPanel extends JPanel{
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JTable table;

    public ProgramPanel(){
        model = new DefaultTableModel();
        model.addColumn("Address");
        model.addColumn("Hexadecimal");
        model.addColumn("Command");
        model.addColumn("Field");

        //model.setRowCount(10);

        table = new JTable(model);

        scroll = new JScrollPane(table,VERTICAL_SCROLLBAR_AS_NEEDED,HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scroll);
    }

    public JScrollPane getProgPanel(){
      return scroll;
    }

    /**
     * Set's table values att given row.
     * @param command String array with info
     */
    public void setRowData(String[] command){
        model.addRow(command);
    }

}
