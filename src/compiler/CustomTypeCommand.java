package compiler;

public class CustomTypeCommand extends Command {

    public CustomTypeCommand(String line) {
        super(null, line, 0);
    }

    @Override
    public String toHex() {
        return null;
    }

	@Override
	public int hexCode() {
		return 0;
	}

	@Override
	public int[] getFields() {
		return null;
	}
    
    
}
