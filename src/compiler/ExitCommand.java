package compiler;

import java.util.ArrayList;

public class ExitCommand extends Command {

	public ExitCommand(ArrayList<String> args, String line, int row) {
		super(args, line, row);
	}

	@Override
	public String toHex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hexCode() {
		// TODO Auto-generated method stub
		return 268500991;
	}

	@Override
	public int[] getFields() {
		return null;
	}

}
