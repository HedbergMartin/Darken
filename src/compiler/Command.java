package compiler;

public abstract class Command {
	private int row;
	private int address;
	private short operation; // Add sub ..

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
}
