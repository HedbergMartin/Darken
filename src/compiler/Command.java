package compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
	private int row;
	private int address; // remove?
	private short operation; // remove ?

	private String originalLine;
	
	Command(String line) {
		this.originalLine = line;
	}


	public abstract boolean hasMissingLabelAddress();
	public abstract String getMissingLabelAddress();
	public abstract void setMissingLabelAddress(int address);

	public abstract String toHex();

	/**
     * Convert register code ($t3 $zero etc) to the corresponding int-value
     * @param registerCode register code ($t3 $zero etc)
     * @return corresponding int-value
     */
	public static int getRegisterNumberShitty(String registerCode){
		int registerNumber = -1;

		switch(registerCode) {
			case "$zero":
				registerNumber = 0;
				break;
			case "$at":
				registerNumber = 1;
				break;
			case "$v0":
				registerNumber = 2;
				break;
			case "$v1":
				registerNumber = 3;
				break;
			case "$a0":
				registerNumber = 4;
				break;
			case "$a1":
				registerNumber = 5;
				break;
			case "$a2":
				registerNumber = 6;
				break;
			case "$a3":
				registerNumber = 7;
				break;
			case "$t0":
				registerNumber = 8;
				break;
			case "$t1":
				registerNumber = 9;
				break;
			case "$t2":
				registerNumber = 10;
				break;
			case "$t3":
				registerNumber = 11;
				break;
			case "$t4":
				registerNumber = 12;
				break;
			case "$t5":
				registerNumber = 13;
				break;
			case "$t6":
				registerNumber = 14;
				break;
			case "$t7":
				registerNumber = 15;
				break;
			case "$s0":
				registerNumber = 16;
				break;
			case "$s1":
				registerNumber = 17;
				break;
			case "$s2":
				registerNumber = 18;
				break;
			case "$s3":
				registerNumber = 19;
				break;
			case "$s4":
				registerNumber = 20;
				break;
			case "$s5":
				registerNumber = 21;
				break;
			case "$s6":
				registerNumber = 22;
				break;
			case "$s7":
				registerNumber = 23;
				break;
			case "$t8":
				registerNumber = 24;
				break;
			case "$t9":
				registerNumber = 25;
				break;
			case "$k0":
				registerNumber = 26;
				break;
			case "$k1":
				registerNumber = 27;
				break;
			case "$gp":
				registerNumber = 28;
				break;
			case "$sp":
				registerNumber = 29;
				break;
			case "$fp":
				registerNumber = 30;
				break;
			case "$ra":
				registerNumber = 31;
				break;
			default:

				String pattern = "([\\$\\w\\-]+)";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(registerCode);

				String multiplierString;
				String insideRegisterString;

				int multiplier = -1;
				int insideRegister;

				m.find();
				multiplierString = m.group(1);

				if(isInteger(multiplierString)){
					multiplier = Integer.parseInt(multiplierString);
				}

				m.find();
				insideRegister = getRegisterNumber(m.group(1));

				registerNumber = multiplier * insideRegister;
		}
		return registerNumber;
	}
	
	/**
     * Convert register code ($t3 $zero etc) to the corresponding int-value
     * @param registerCode register code ($t3 $zero etc)
     * @return corresponding int-value
     */
	public static int getRegisterNumber(String registerCode){

		if (REG_NUMBERS.get(registerCode) == null) {
			String pattern = "([\\d\\-]+)|(\\$[\\w]+)";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(registerCode);
	
			String multiplierString;
	
			int multiplier = -1;
			int insideRegister;
	
			if (!m.find()) {
				return -1;
			}
			multiplierString = m.group(1);
	
			if(isInteger(multiplierString)){
				multiplier = Integer.parseInt(multiplierString);
			}
	
			if (!m.find()) {
				return -1;
			}
			insideRegister = getRegisterNumber(m.group(2));
	
			return multiplier * insideRegister;
		}
		
		return REG_NUMBERS.get(registerCode);
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

	public void setLine(String line){
		originalLine = originalLine + line;
	}
	
	private static Map<String, Integer> REG_NUMBERS = new HashMap<String, Integer>();
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
