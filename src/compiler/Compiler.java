package compiler;

import java.util.Map;

public class Compiler {
	
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
	

	public static Map<String, type> comType;
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
