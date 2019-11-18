package compiler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {
	
	private Map<String, Integer> lableAddress;
	//Todo update to I-Command
	private Queue<Command> unfinishedRefs;
	
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
				String pattern = "([\\S\\-]+:)|([\\w\\-]+)|(\\$[\\w\\-]+)|(#.*)";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(line);
				int i = 1;
				//System.out.println("Group count: " + m.groupCount());
				while (m.find()) {
					String s = null;
					if (i == 1) {
						s = m.group(1);
						if (s == null) {
							i++;
						}
					}
					if (i == 2) {
						s = m.group(2);
						if (s == null) {
							i++;
						}
					}
					if (i == 3) {
						s = m.group(3);
						if (s == null) {
							i++;
						}
					}
					System.out.println("Found: (" + i + ") " + s);
//					i++;
				}
			}
		}
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
