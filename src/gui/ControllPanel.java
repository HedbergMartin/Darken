package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControllPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton runButton;
	private JButton stepButton;
	private JButton resetButton;

	public ControllPanel() {
		this.runButton = createButton("Run");
		this.stepButton = createButton("Step");
		this.resetButton = createButton("Reset");
	}
	
	private JButton createButton(String title) {
		JButton button = new JButton(title);
		this.add(button);
		return button;
	}
}
