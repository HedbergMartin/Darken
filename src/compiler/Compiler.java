package compiler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {
	
	private Map<String, Integer> lableAddress = new HashMap<String, Integer>();
	//Todo update to I-Command
	private Queue<Command> finishedCommands =  new LinkedList<>();
	private Queue<Command> unfinishedRefs = new LinkedList<>();
	
	/**
	 * 
	 * Input: File
	 */
	public Compiler(String pathName) {
		File file = new File(pathName);
		try {
			parseFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// R-Command 
	private void parseFile(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			System.out.println(line);
			if (line.isEmpty()) {
				//save to EmptyCommand without comment
			} else if (line.charAt(0) == '#') {
				//save to EmptyCommand with comment
			} else {
				String pattern = "([\\S\\-]+:)|([\\w\\-]+)";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(line);
				int i = 1;
				
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

				// TODO Set label addresses in the lableAddress-map

				System.out.println("LABEL IS: " + label + " Rest: " + list);

				Command newCommand = null;
				type comType = COMMAND_TYPES.get(list.get(0));
				switch (comType) {
				case R:
					newCommand = new RTypeCommand(list.get(0), list.get(1), list.get(2), list.get(3), line);
					break;
					
				case I:
					newCommand = new ITypeCommand(list.get(0), list.get(1), list.get(2), list.get(3), line);
					break;
					
				case J:
					newCommand = new JTypeCommand(list.get(0), list.get(1), line);
					break;

				default:
					//Nops
					newCommand = new CustomTypeCommand(line);
					break;
				}

				finishedCommands.add(newCommand);

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
		}

		// Go throuh unfninished commands and add missing label-adresses
		while(!unfinishedRefs.isEmpty()){
			Command currentUnfinishedCommand = unfinishedRefs.poll();
			String missingLabelAddress = currentUnfinishedCommand.getMissingLabelAddress();
			currentUnfinishedCommand.setMissingLabelAddress(lableAddress.get(missingLabelAddress));
		}

	}
	
	public void toHexFile() {
		//for every command line
			//command.toHex
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
        COMMAND_TYPES.put("nop", type.C);
    }
	
	private enum type {
		R,
		I,
		J,
        C
	}
}
