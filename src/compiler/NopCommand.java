package compiler;

import java.util.ArrayList;

public class NopCommand extends Command {

	public NopCommand(ArrayList<String> args, String line, int row) {
		super(args, line, row);
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
