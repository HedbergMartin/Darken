package gui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import compiler.Command;

public class RegisterPanel extends JPanel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table;

    public RegisterPanel () {
        createTable();
    }

    private void createTable() {
    	this.setLayout(new BorderLayout());
    	String[] colnames = {"Register", "Value"};
        TableModel model = new DefaultTableModel(colnames, 31);
        this.table = new JTable(model);

		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroll);
		
		this.setTable("0", 0);
    }

    // Dont try to read this :)))
    private void setTable(String register, int values) {
    	
    	Iterator<Entry<String, Integer>> it = ((HashMap<String, Integer>) Command.REG_NUMBERS.clone()).entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> pair = (Entry<String, Integer>)it.next();
            if (pair.getValue() != 0) {
                this.table.getModel().setValueAt(pair.getKey(), pair.getValue()-1, 0);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public void updateTable(String data, int register) {
        this.table.getModel().setValueAt(data, register, 1);
    }

}
