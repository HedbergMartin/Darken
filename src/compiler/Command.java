package compiler;

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

	/**
	 * Sets the right bit length of a
	 * string with binary form.
	 * @param nrBits the wanted length of the binary string.
	 * @param bits the binary string.
	 * @return the modified string.
	 */
	static String checkBits(int nrBits, String bits){
		int nr = nrBits - bits.length();
		for(int i = 0; i<nr;i++){
			bits = "0" + bits;
		}
		return bits;
	}

	public abstract boolean hasMissingLabelAddress();
	public abstract String getMissingLabelAddress();
	public abstract void setMissingLabelAddress(int address);

	/**
	 * Convert integer into a binary string.
	 * @param number integer to be converted.
	 * @return The converted string.
	 */
	static String getBinary(Integer number){
		return Integer.toString(number,2);
	}

	/**
	 * Convert binary form to hex decimal form.
	 * @param binaryForm String to convert.
	 * @return the converted String.
	 */
	static String getHex(String binaryForm){
		int hex = Integer.parseInt(binaryForm,2);
		return Integer.toString(hex,16);
	}

	public abstract String toHex();

	/**
     * Convert register code ($t3 $zero etc) to the corresponding int-value
     * @param registerCode register code ($t3 $zero etc)
     * @return corresponding int-value
     */
	public static int getRegisterNumber(String registerCode){

		//System.out.println("getRegisterNumber got " + registerCode);

		// TODO handle 42($v1) codes

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

				//System.out.println("Wtf! Error haven't seen this (" + registerCode + ") register-code before?!?");
				//System.out.println("I guess it is a immediate value! aaaaah");


				//registerNumber = Integer.parseInt(registerCode);


		}
		return registerNumber;
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
}
