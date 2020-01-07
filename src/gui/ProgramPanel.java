package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class ProgramPanel extends JPanel{
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JTable table;

    private int currentRow = 0;

    public ProgramPanel(){
        model = new DefaultTableModel();
        model.addColumn("Step");
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
     * Set's table values at given row.
     * @param command String array with info
     */
    public void setRowData(String[] command){
        model.addRow(command);
    }

    public void setRow(int row){

        this.model.setValueAt(" ",currentRow,0);

        if(this.model.getRowCount() > row){
            this.model.setValueAt(">>>",row,0);
            currentRow = row;
        }

    }

	public void reset() {
		this.model.getDataVector().removeAllElements();
		this.model.fireTableDataChanged();
	}
}
