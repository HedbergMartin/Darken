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
	
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private boolean decimalFormat = true;

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
		
		this.initTable();
    }

    // Dont try to read this :)))
    private void initTable() {
    	
    	@SuppressWarnings("unchecked")
		Iterator<Entry<String, Integer>> it = ((HashMap<String, Integer>) Command.REG_NUMBERS.clone()).entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> pair = (Entry<String, Integer>)it.next();
            if (pair.getValue() != 0) {
                this.table.getModel().setValueAt(pair.getKey(), pair.getValue()-1, 0);
                this.table.getModel().setValueAt(0, pair.getValue()-1, 1);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public void updateTable(String data, int register) {
        // Register-1 pga vi kör inte med $zero
        if (register != 0) {
            this.table.getModel().setValueAt(data, register-1, 1);
        }
    }

	public void reset() {
		this.initTable();
	}

    public void toggleFormat(){

        for(int rowIndex = 0; rowIndex < this.table.getModel().getRowCount(); rowIndex++){
            String value = this.table.getModel().getValueAt(rowIndex,1).toString();
        	String newValue;
        	if (decimalFormat) {
        		newValue = "0x" + String.format("%08X", Integer.valueOf(value));//Integer.toHexString(Integer.parseInt(value));
        	} else {
        		value = value.substring(2, value.length());
        		newValue = Integer.toString(Long.valueOf(value,16).intValue());
        	}
        	
            this.table.getModel().setValueAt(newValue, rowIndex, 1);
        }
        this.decimalFormat = !decimalFormat;
    }

}
