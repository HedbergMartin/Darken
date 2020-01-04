package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import compiler.MipsCompiler;
import program.MIPSsimulator;

public class OpenFileActionListener implements ActionListener {
	
	private MIPSsimulator simulator;
	
	public OpenFileActionListener(MIPSsimulator simulator) {
		this.simulator = simulator;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			//TODO DONT DO IN EDT!
			MipsCompiler compile = new MipsCompiler(file, null, null);
			simulator.showProgram(compile.getCommands());
		}
	}

}
