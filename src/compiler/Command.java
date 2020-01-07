package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
	private int row;
	private int operation;

	private String originalLine;
	private String commandLine;

	public Command(ArrayList<String> args, String line, int row) {
		commandLine = "";
		if (args != null) {
			for (String arg : args) {
				commandLine += arg + " ";
			}
		}
		this.originalLine = line;
		this.row = row;
	}

    public boolean hasMissingLabelAddress() {
        return false;
    }

    public String getMissingLabelAddress() {
        return null;
    }

    public void setMissingLabelAddress(int address) { }

	public abstract String toHex();
	public abstract int hexCode();
	public abstract int[] getFields();

	public int getRow() {
		return this.row;
	}
	
	protected void setOpcode(int op) {
		this.operation = op;
	}
	
	public int getOpcode() {
		return this.operation;
	}
	
	/**
     * Convert register code ($t3 $zero etc) to the corresponding int-value
     * @param registerCode register code ($t3 $zero etc)
     * @return corresponding int-value
     */
	public static int getRegisterNumber(String registerCode){
		if (REG_NUMBERS.get(registerCode) == null) {
			return -1;
		}
		return REG_NUMBERS.get(registerCode);
	}
	
	public static int getBase(String registerCode) {
		if (REG_NUMBERS.get(registerCode) == null) {
			String pattern = "([\\d\\-]+)|(\\$[\\w]+)";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(registerCode);
	
			int insideRegister;
	
			if (!m.find()) {
				return -1;
			}
	
			if (!m.find()) {
				return -1;
			}
			
			insideRegister = getRegisterNumber(m.group(2));
	
			return insideRegister;
		}
		
		return REG_NUMBERS.get(registerCode);
	}

	
	public static int getOffset(String registerCode) {
		if (REG_NUMBERS.get(registerCode) == null) {
			String pattern = "([\\d\\-]+)|(\\$[\\w]+)";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(registerCode);
	
			String multiplierString;
	
			int multiplier = -1;
	
			if (!m.find()) {
				return -1;
			}
			multiplierString = m.group(1);
	
			if(isInteger(multiplierString)){
				multiplier = Integer.parseInt(multiplierString);
			} else {
				return -1;
			}
	
			return multiplier;
		}
		
		return 0;
	}

	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return false;
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	public String getOriginalLine() {
		return originalLine;
	}
	
	public String getCommandLine() {
		return commandLine;
	}

	public void appendToLine(String line){
		originalLine = originalLine + line;
	}
	
	public final static HashMap<String, Integer> REG_NUMBERS = new HashMap<String, Integer>();
	static {
		REG_NUMBERS.put("$zero", 0);
		REG_NUMBERS.put("$at", 1);
		REG_NUMBERS.put("$v0", 2);
		REG_NUMBERS.put("$v1", 3);
		REG_NUMBERS.put("$a0", 4);
		REG_NUMBERS.put("$a1", 5);
		REG_NUMBERS.put("$a2", 6);
		REG_NUMBERS.put("$a3", 7);
		REG_NUMBERS.put("$t0", 8);
		REG_NUMBERS.put("$t1", 9);
		REG_NUMBERS.put("$t2", 10);
		REG_NUMBERS.put("$t3", 11);
		REG_NUMBERS.put("$t4", 12);
		REG_NUMBERS.put("$t5", 13);
		REG_NUMBERS.put("$t6", 14);
		REG_NUMBERS.put("$t7", 15);
		REG_NUMBERS.put("$s0", 16);
		REG_NUMBERS.put("$s1", 17);
		REG_NUMBERS.put("$s2", 18);
		REG_NUMBERS.put("$s3", 19);
		REG_NUMBERS.put("$s4", 20);
		REG_NUMBERS.put("$s5", 21);
		REG_NUMBERS.put("$s6", 22);
		REG_NUMBERS.put("$s7", 23);
		REG_NUMBERS.put("$t8", 24);
		REG_NUMBERS.put("$t9", 25);
		REG_NUMBERS.put("$k0", 26);
		REG_NUMBERS.put("$k1", 27);
		REG_NUMBERS.put("$gp", 28);
		REG_NUMBERS.put("$sp", 29);
		REG_NUMBERS.put("$fp", 30);
		REG_NUMBERS.put("$ra", 31);
    }

}
