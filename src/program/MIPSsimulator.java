package program;
import java.util.Map;
import java.util.Queue;

import compiler.Command;
import datapath.Datapath;
import gui.Window;
import listeners.OpenFileActionListener;
import listeners.ControllButtonListener;

public class MIPSsimulator {
    public static void main(String[] args) {
        //if (args.length != 3){
        //    System.exit(-1);
        //}
        //;
        //compiler.prettyPrintFile();
        //compiler.toHexFile();
    	new MIPSsimulator(args);
    }
    
    private Window window;
    private Datapath datapath;
    
    public MIPSsimulator(String[] args) {
    	this.window = new Window();
    	this.window.addOpenListener(new OpenFileActionListener(this));
    	this.window.addControllListener(new ControllButtonListener(this));
    	this.datapath = new Datapath();
    	//Compiler compiler = new Compiler(args[0], args[1], args[2]);
	}

	public void loadProgram(Queue<Command> commands) {
		while(!commands.isEmpty()) {
			Command com = commands.poll();
			if (!com.getCommandLine().equals("")) {
				System.out.println(com.hexCode());
				this.window.addProgramLine(com.getRow(), com.toHex(), com.getCommandLine().trim());
				this.datapath.appendInstruction(com.hexCode());
			}
		}
	}
    
    public void oneStep() {
    	datapath.oneStep();
    	this.updateGui(datapath.getRegisterDataMap(), datapath.getMemoryDataMap());
    }

	private void updateGui(Map<Short, Short> registerDataMap,
			Map<Integer, Integer> memoryDataMap) {
		
		for(int i = 0; i < registerDataMap.size(); i++) {
			this.window.writeToRegister(i, registerDataMap.get(i));
		}
		
	}
    
}
