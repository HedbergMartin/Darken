package compiler;

public abstract class Command {
	
	int command;
	short op;
	
	public Command() {	}
	
	public abstract int toHex();
}
