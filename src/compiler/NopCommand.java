package compiler;

public class NopCommand extends Command {

	NopCommand(String line, int row) {
		super(line, row);
	}

	@Override
	public String toHex() {
		return "00000000";
	}

}
