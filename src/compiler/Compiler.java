package compiler;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Compiler {
	
	private Map<String, Integer> lableAddress;
	//Todo update to I-Command
	private Queue<Command> unfinishedRefs;
	
	/**
	 * 
	 * Input: File
	 */
	public Compiler() {
		// TODO Auto-generated constructor stub
	}
	
	private void parseFile() {
		// Read file
		// Save to arraylist of command
		
	}
	
	public void toHexFile() {
		//for every command line
			//command.toHex
	}
	

	public static Map<String, type> comType = new HashMap<String, type>();
	static {
        comType.put("Add", type.R);
        comType.put("Sub", type.R);
    }
	
	private enum type {
		R,
		I,
		J
	}
}
