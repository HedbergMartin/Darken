package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import program.MIPSsimulator;

public class ControllButtonListener implements ActionListener {

	private MIPSsimulator simulator;
	
	public ControllButtonListener(MIPSsimulator simulator) {
		this.simulator = simulator;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.simulator.oneStep();
		
	}

}
