package program;
import java.util.Queue;

import compiler.Command;
import gui.Window;
import listeners.OpenFileActionListener;

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
    
    public MIPSsimulator(String[] args) {
    	this.window = new Window();
    	this.window.addOpenListener(new OpenFileActionListener(this));
    	//Compiler compiler = new Compiler(args[0], args[1], args[2]);
	}

	public void showProgram(Queue<Command> commands) {
		while(!commands.isEmpty()) {
			Command com = commands.poll();
			this.window.addProgramLine(com.getRow(), com.getOriginalLine());
		}
	}
    
    
}
