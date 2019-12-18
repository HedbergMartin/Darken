package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Window extends JFrame {

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
		this.add(new ProgramPanel(), BorderLayout.CENTER);
		this.add(new RegisterPanel(), BorderLayout.WEST);
		this.add(new DataMemPanel(1024), BorderLayout.SOUTH);
	}
	
	private void createMenubar() {
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		
		this.setJMenuBar(menubar);
		
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
}
