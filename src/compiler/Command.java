package compiler;

public abstract class Command {

	private int row;
	private int address;

	private short operation; // Add sub ..

	private String originalLine;
	
	public Command(String line) {
		this.originalLine = line;
	}
	
	public abstract int toHex();
}
