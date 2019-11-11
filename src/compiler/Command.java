package compiler;

public abstract class Command {
	
	int command;
	short op;
	String line;
	
	public Command(String line) {
		this.line = line;
	}
	
	public abstract int toHex();
}
