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
		if( arg0.getActionCommand().equals("Step")){
			this.simulator.oneStep();
		} else if (arg0.getActionCommand().equals("Run")){
			this.simulator.run();
		} else if(arg0.getActionCommand().equals("Reset")){
			this.simulator.resetWindow();
		} else {
			//TODO switch hex/dec

		}
		
	}

}
