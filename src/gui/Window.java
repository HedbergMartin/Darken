package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {

	public Window() {
		super("Mips Simulator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1024, 1024));
		this.completeWindow();
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
