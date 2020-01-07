package gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControllPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton runButton;
	private JButton stepButton;
	private JButton resetButton;
	private JButton hexDecButton;

	public ControllPanel() {
		this.runButton = createButton("Run");
		this.stepButton = createButton("Step");
		this.resetButton = createButton("Reset");
		this.hexDecButton = createButton("Hex/Dec");
		boolean value = false;
		this.runButton.setEnabled(value);
		this.stepButton.setEnabled(value);
		this.resetButton.setEnabled(value);
	}
	
	private JButton createButton(String title) {
		JButton button = new JButton(title);
		this.add(button);
		return button;
	}
	
	public void addButtonListener(ActionListener listener) {
		this.runButton.addActionListener(listener);
		this.stepButton.addActionListener(listener);
		this.resetButton.addActionListener(listener);
		this.hexDecButton.addActionListener(listener);
	}
	
	public void enableButtons() {
		boolean value = true;
		this.runButton.setEnabled(value);
		this.stepButton.setEnabled(value);
		this.resetButton.setEnabled(value);
	}
}
