package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import listeners.OpenFileActionListener;

public class Window extends JFrame {
	
	private RegisterPanel regPanel;
	private ProgramPanel progPanel;
	private JMenuItem openItem;

	public Window() {
		super("Mips Simulator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1024, 1024));
		this.addPanels();
		this.createMenubar();
		this.completeWindow();
	}
	
	private void addPanels() {
		this.add(new ControllPanel(), BorderLayout.NORTH);
		this.progPanel = new ProgramPanel();
		this.add(this.progPanel, BorderLayout.CENTER);
		this.regPanel = new RegisterPanel();
		this.add(this.regPanel, BorderLayout.WEST);
		this.add(new DataMemPanel(1024), BorderLayout.SOUTH);
	}
	
	private void createMenubar() {
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		
		this.openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		
		this.setJMenuBar(menubar);
		
	}
	
	public void addOpenListener(ActionListener listener) {
		openItem.addActionListener(listener);
	}
	
	/**
	 * The window has completed its setup and is ready to be showed.
	 */
	public void completeWindow() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void writeToRegister(int register, int value) {
		this.regPanel.updateTable(Integer.toString(value), register);
	}

	public void addProgramLine(int row, String hex, String command) {
		int address = row << 2;
		this.progPanel.setRowData(new String[] {"0x" + String.format("%08X", address), hex, command});
	}
}
