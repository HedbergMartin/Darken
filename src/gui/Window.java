package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Window extends JFrame {
	
	private RegisterPanel regPanel;
	private ProgramPanel progPanel;
	private ControllPanel ctrlPanel;
	private DataMemPanel memPanel;
	private JMenuItem openItem;
	
	private static Window window;
	
	public static Window getWindowInstance() {
		if (window == null) {
			window = new Window();
		}
		return window;
	}

	private Window() {
		super("Mips Simulator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1024, 500));
		this.addPanels();
		this.createMenubar();
		this.completeWindow();
	}
	
	private void addPanels() {
		this.ctrlPanel = new ControllPanel();
		this.add(this.ctrlPanel, BorderLayout.NORTH);
		this.progPanel = new ProgramPanel();
		this.add(this.progPanel, BorderLayout.CENTER);
		this.regPanel = new RegisterPanel();
		this.regPanel.setPreferredSize(new Dimension(300,200));
		this.add(this.regPanel, BorderLayout.WEST);
		this.memPanel = new DataMemPanel(1024);
		this.memPanel.setPreferredSize(new Dimension(1024,200));
		this.add(this.memPanel, BorderLayout.SOUTH);
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
	
	public void addControllListener(ActionListener listener) {
		this.ctrlPanel.addButtonListener(listener);
	}
	
	/**
	 * The window has completed its setup and is ready to be showed.
	 */
	public void completeWindow() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
	}
	
	public void writeToRegister(int register, int value) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				regPanel.updateTable(Integer.toString(value), register);
			}
		});
	}
	
	public void writeToDatamem(int memoryLoc, int value) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				memPanel.setMemoryValue(value, memoryLoc);
			}
		});
	}

	public void addProgramLine(int row, String hex, String command) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				int address = row << 2;
				progPanel.setRowData(new String[] {" " , "0x" + String.format("%08X", address), hex, command});
			}
		});
	}

	public void setCurrentRow(int row){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				progPanel.setRow(row);
			}
		});
	}

	public void resetProgram() {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				progPanel.reset();
				regPanel.reset();
				memPanel.reset();
			}
		});
	}
}
