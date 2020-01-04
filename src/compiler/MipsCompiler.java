package compiler;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import program.MIPSsimulator;

import static compiler.Utilities.checkBits;
import static compiler.Utilities.getBinary;
import static compiler.Utilities.getHex;

public class MipsCompiler {
	
	private Map<String, Integer> lableAddress = new HashMap<String, Integer>();
	//Todo update to I-Command
	private Queue<Command> finishedCommands =  new LinkedList<>();
	private Queue<Command> unfinishedRefs = new LinkedList<>();
	private String prettyPrint;
	private String hexFile;
	
	/**
	 * 
	 * Input: File
	 */
	public MipsCompiler(File inputFile, String prettyPrint, String hexFile) {
		this.prettyPrint = prettyPrint;
		this.hexFile = hexFile;
		try {
			parseFile(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("NFuck");
			return;
		}
	}
	
	public MipsCompiler(String inputFile, String prettyPrint, String hexFile) {
		this(new File(inputFile), prettyPrint, hexFile);
	}
	
	// R-Command 
	private void parseFile(File file) throws Exception {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		int currentAddress = 0;

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.isEmpty()) {
				//save to EmptyCommand without comment
			} else if (line.charAt(0) == '#') {
				Command comment = new CustomTypeCommand(line);
				finishedCommands.add(comment);
			} else {
				String pattern = "([\\S\\-]+:)|([\\(\\$\\w\\)\\-]+)|(#.*)";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(line);

				String label = null;
				ArrayList<String> list = new ArrayList<String>();
				while (m.find()) {
					if (m.group(1) != null) {
					    label = m.group(1);
					}
				    if (m.group(2) != null) {
				    	list.add(m.group(2));
				    }
				}
				
				if(label != null){
					lableAddress.put(label,currentAddress);
					
					// If only label on line, go to next line without incrementing address.
					if(list.isEmpty()){
						finishedCommands.add(new CustomTypeCommand(label));
						continue;
					}
				}

				if(!list.isEmpty()){
					Command newCommand = getCommandType(line, list, currentAddress);
					if(!finishedCommands.contains(newCommand)){
						finishedCommands.add(newCommand);
					}

					// Add unfinished commands to unfinishedRefs (To enter label-adresses missing)
					if(newCommand.hasMissingLabelAddress()){
						String missingLabelAddress = newCommand.getMissingLabelAddress();

						if(lableAddress.containsKey(missingLabelAddress)){
							newCommand.setMissingLabelAddress(lableAddress.get(missingLabelAddress));
						}else{
							unfinishedRefs.add(newCommand);
						}
					}

					currentAddress += 4;
				}
			}
		}
		// Go through unfninished commands and add missing label-adresses
		while(!unfinishedRefs.isEmpty()){
			Command currentUnfinishedCommand = unfinishedRefs.poll();
			String missingLabelAddress = currentUnfinishedCommand.getMissingLabelAddress();
			if( lableAddress.get(missingLabelAddress + ":") == null){
				currentUnfinishedCommand.appendToLine("	Error: Missing label");
			} else {
				currentUnfinishedCommand.setMissingLabelAddress(lableAddress.get(missingLabelAddress + ":"));
			}
		}

	}

	private Command getCommandType(String line, ArrayList<String> list, int currentAddress) throws Exception {
		Command newCommand = null;
		type comType = COMMAND_TYPES.get(list.get(0));
		int row = (currentAddress >> 2);
		
		if( comType == null ){
			line = line + "	Error: instruction not allowed";
			finishedCommands.add(newCommand = new CustomTypeCommand(line));
			throw new Exception("Invalid command at line: " + row);
		}
		
		switch (comType) {
            case R:
            	newCommand = new RTypeCommand(list, line, row);
            	break;
            	
            case I:
            	newCommand = new ITypeCommand(list, line, row);
                break;
                
            case J:
                newCommand = new JTypeCommand(list, line, row);
                break;
                
            case N:
            	newCommand = new NopCommand(line, row);
            	break;
        }
		return newCommand;
	}

	public void prettyPrintFile(){
		// Create pretty print file!
		String fileContent = "";

		for (Command com : finishedCommands) {
			if( com.toHex() == null ){
				fileContent = fileContent + "						" + com.getOriginalLine() + "\n";
			} else {
				String hexAddress = checkBits(8,getHex(checkBits(32,getBinary(com.getRow()*4))));
				fileContent = fileContent + "0x"+hexAddress + "	" + "0x"+com.toHex() +
						"	" + com.getOriginalLine() + "\n";
			}
		}
		fileContent = fileContent + "\nSymbols \n";
		for (String label : lableAddress.keySet()) {
			String hexAddress = checkBits(8,getHex(checkBits(32,getBinary(lableAddress.get(label)))));
			fileContent = fileContent + label + "	" +"0x"+ hexAddress + "\n";
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(prettyPrint));
			writer.write(fileContent);
			writer.close();
		} catch (IOException e) {}

	}
	
	public Queue<Command> getCommands() {
		return finishedCommands;
	}
	
	public void toHexFile() {
		String fileContent = "";
        for (Command com: finishedCommands) {
        	if(com.toHex() != null){
				fileContent = fileContent + "0x" + com.toHex() + "\n";
			}
        }
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(hexFile));
			writer.write(fileContent);
			writer.close();
		} catch (IOException e) {}
	}

	private static Map<String, type> COMMAND_TYPES = new HashMap<String, type>();
	static {
        COMMAND_TYPES.put("add", type.R);
        COMMAND_TYPES.put("sub", type.R);
        COMMAND_TYPES.put("and", type.R);
        COMMAND_TYPES.put("or", type.R);
        COMMAND_TYPES.put("nor", type.R);
        COMMAND_TYPES.put("slt", type.R);
        COMMAND_TYPES.put("lw", type.I);
        COMMAND_TYPES.put("sw", type.I);
        COMMAND_TYPES.put("beq", type.I);
        COMMAND_TYPES.put("addi", type.I);
        COMMAND_TYPES.put("sll", type.R);//Speciell
        COMMAND_TYPES.put("j", type.J);
        COMMAND_TYPES.put("jr", type.R);//Speciell
        COMMAND_TYPES.put("nop", type.N);//Nop
    }
	
	private enum type {
		R,
		I,
		J,
        N // Nop type
	}
}
