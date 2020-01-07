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

		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			new Thread(new Runnable() {

				@Override
				public void run() {
						File file = fileChooser.getSelectedFile();
						MipsCompiler compile = new MipsCompiler(file, null, null);
						simulator.loadProgram(compile.getCommands());
				}
			}).start();
		}
	}

}
