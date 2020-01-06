package compiler;

public class NopCommand extends Command {

	NopCommand(String line, int row) {
		super(null, line, row);
	}

	@Override
	public String toHex() {
		return "00000000";
	}

	@Override
	public int hexCode() {
		return 0;
	}

}
