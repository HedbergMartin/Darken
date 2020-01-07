package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DataMemPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private int memSize;
	
	public DataMemPanel(int memorySize) {
		this.memSize = memorySize;
		this.setLayout(new BorderLayout());
		
		int rows = (memorySize-1) / 16 + 1;
		String[] colnames = new String[17];
		colnames[0] = "Address";
		for (int i = 0; i < 16; i++) {
			colnames[i+1] = Integer.toHexString(i);
		}
		TableModel model = new DefaultTableModel(colnames, rows);
		table = new JTable(model);
		
		
		for (int i = 0; i < rows; i++) {
			String hexValue = "0x" + String.format("%07X", i) + "X";
			model.setValueAt(hexValue, i, 0);
		}
		table.getColumnModel().getColumn(0).setMinWidth(80);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroll);

		for (int i = 0; i < this.memSize; i++) {
			setMemoryValue(0, i);
		}
	}
	
	public void setMemoryValue(int value, int memoryLoc) {
		if (memoryLoc >= memSize) {
			throw new IndexOutOfBoundsException();
		}
		table.getModel().setValueAt(String.format("%02X", value), memoryLoc / 16, memoryLoc % 16 + 1);
	}

	public void reset() {
		for (int i = 0; i < this.memSize; i++) {
			setMemoryValue(0, i);
		}
	}

}
