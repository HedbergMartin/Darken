package program;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.function.BiConsumer;

import compiler.Command;
import datapath.Datapath;
import gui.Window;
import listeners.OpenFileActionListener;
import listeners.ControllButtonListener;

import static java.lang.Thread.sleep;

public class MIPSsimulator {
    public static void main(String[] args) {
    	new MIPSsimulator();
    }
    
    private Window window;
    private Datapath datapath;
    private Queue<Command> lastCommandQueue;

    public MIPSsimulator() {
    	this.window = Window.getWindowInstance();
    	this.window.addOpenListener(new OpenFileActionListener(this));
    	this.window.addControllListener(new ControllButtonListener(this));
    	this.datapath = new Datapath();
    	//Compiler compiler = new Compiler(args[0], args[1], args[2]);
	}

	public void loadProgram(Queue<Command> commands) {
		this.lastCommandQueue = new LinkedList<>(commands);
		this.window.resetProgram();
		this.datapath.reset();
		//this.window.clearProgramTable();
		while(!commands.isEmpty()) {
			Command com = commands.poll();
			if (!com.getCommandLine().equals("")) {
				this.window.addProgramLine(com.getRow(), com.toHex(), com.getCommandLine().trim());
				this.datapath.appendInstruction(com.hexCode());
			}
		}
		this.window.setCurrentRow(0);
	}
    
    public void oneStep() {
    	datapath.oneStep();
    	this.updateGui(datapath.getCurrentInstructionAddress(),datapath.getRegisterDataMap(), datapath.getMemoryDataMap());
    }

	private void updateGui(int currentAddress, Map<Integer, Integer> registerDataMap,
			Map<Integer, Integer> memoryDataMap) {
		
		
		registerDataMap.forEach(new BiConsumer<Integer, Integer>() {

			@Override
			public void accept(Integer t, Integer u) {
				window.writeToRegister(t, u);
			}
		});
		
		memoryDataMap.forEach(new BiConsumer<Integer, Integer>() {

			@Override
			public void accept(Integer address, Integer value) {
				window.writeToDatamem(address, value);
			}
		});
		
		

		this.window.setCurrentRow(currentAddress >> 2);
	}

	public void resetWindow(){
		this.loadProgram(lastCommandQueue);
	}

	public void run(){
		new Thread(new Runnable() {

			@Override
			public void run() {
		    	while (datapath.oneStep()){
					updateGui(datapath.getCurrentInstructionAddress(),datapath.getRegisterDataMap(), datapath.getMemoryDataMap());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    	}
			}
		}).start();
	}

	public void toggleRegisterValueFormat() {
		this.window.toggleFormat();
	}

}
