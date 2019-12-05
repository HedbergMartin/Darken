package compiler;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static compiler.Utilities.checkBits;
import static compiler.Utilities.getBinary;
import static compiler.Utilities.getHex;

public class Compiler {
	
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
	public Compiler(String inputFile, String prettyPrint, String hexFile) {
		File file = new File(inputFile);
		this.prettyPrint = prettyPrint;
		this.hexFile = hexFile;
		try {
			parseFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// R-Command 
	private void parseFile(File file) throws FileNotFoundException {
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
				}

				currentAddress += 4;
			}
		}
		// Go through unfninished commands and add missing label-adresses
		while(!unfinishedRefs.isEmpty()){
			Command currentUnfinishedCommand = unfinishedRefs.poll();
			String missingLabelAddress = currentUnfinishedCommand.getMissingLabelAddress();
			if( lableAddress.get(missingLabelAddress + ":") == null){
				currentUnfinishedCommand.setLine("	Error: Missing label");
			} else {
				currentUnfinishedCommand.setMissingLabelAddress(lableAddress.get(missingLabelAddress + ":"));
			}
		}

	}

	private Command getCommandType(String line, ArrayList<String> list, int currentAddress) {
		Command newCommand = null;
		type comType = COMMAND_TYPES.get(list.get(0));
		if( comType == null ){
			line = line + "	Error: instruction not allowed";
			finishedCommands.add(newCommand = new CustomTypeCommand(line));
			return newCommand;
		}
		switch (comType) {
            case R:
                // Special jr-case
                if(list.get(0).equals("jr")){
                    newCommand = new RTypeCommand(list.get(0), list.get(1), "$zero", "$zero", line);
                }else{
                	try{
						newCommand = new RTypeCommand(list.get(0), list.get(1), list.get(2), list.get(3), line);
					} catch(IndexOutOfBoundsException e) {
                		line = line + "	Error: instruction call is not correct";
                		newCommand = new CustomTypeCommand(line);
					}
                }
                break;
            case I:
                if(list.get(0).equals("sw") || list.get(0).equals("lw")){
                	newCommand = new ITypeCommand(list.get(0), list.get(1), list.get(2), line);
                }else{
					try{
						newCommand = new ITypeCommand(list.get(0), list.get(1), list.get(2), list.get(3), (currentAddress >> 2), line);
					} catch(IndexOutOfBoundsException e) {
						line = line + "	Error: instruction call is not correct";
						newCommand = new CustomTypeCommand(line);
					}

                }
                break;
            case J:
                if(list.get(0).equals("nop")){
                    newCommand = new JTypeCommand(list.get(0), line);
                } else {
					try{
						newCommand = new JTypeCommand(list.get(0), list.get(1), line);
					} catch(IndexOutOfBoundsException e) {
						line = line + "	Error: instruction call is not correct";
						newCommand = new CustomTypeCommand(line);
					}

                }
                break;
            default:
                //Nops
                newCommand = new CustomTypeCommand(line);
                break;
        }
		return newCommand;
	}

	public void prettyPrintFile(){
		// Create pretty print file!
		String fileContent = "";
		int rowAddress = 0;

		for (Command com : finishedCommands) {
			if( com.toHex() == null ){
				fileContent = fileContent + "						" + com.getOriginalLine() + "\n";
			} else {
				String hexAddress = checkBits(8,getHex(checkBits(32,getBinary(rowAddress))));
				fileContent = fileContent + "0x"+hexAddress + "	" + "0x"+com.toHex() +
						"	" + com.getOriginalLine() + "\n";
				rowAddress+=4;
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
	
	public void toHexFile() {
		String fileContent = "";
        for (Command com: finishedCommands) {
        	if(com.toHex() != null){
				fileContent = fileContent + com.toHex() + "\n";
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
        COMMAND_TYPES.put("nop", type.J);
    }
	
	private enum type {
		R,
		I,
		J,
        C
	}
}
