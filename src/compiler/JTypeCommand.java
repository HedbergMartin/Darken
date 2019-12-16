package compiler;

import java.util.ArrayList;

public class JTypeCommand extends Command {
    private int op; // 6 bit
    private int targetAddress = -1; // 26 bit
    // private int row;
    // private int address;         if commands should save which row and address it has.
    private String address = null;
    
    public JTypeCommand(ArrayList<String> args, String line, int row) {
    	super(line, row);
    	
    	switch (args.get(0).toLowerCase()) {
		case "nop":
	        this.targetAddress = 0;
	        this.op = 0;
			break;
		default:
			this.op = 2;
			this.address = args.get(1);
			break;	
    	}
    }

    @Override
    public boolean hasMissingLabelAddress() {
        return address != null;
    }

    @Override
    public String getMissingLabelAddress() {
        return address;
    }

    @Override
    public void setMissingLabelAddress(int address) {
        this.targetAddress = (address >> 2);
        this.address = null;
    }

    /**
     * Convert a string instruction into
     * a string in hex decimal form.
     * @return the hex decimal string.
     */
    @Override
    public String toHex() {
    	int result = op;
    	result = result << 26;
    	result += targetAddress;
    	
        return String.format("%08X", result);
    }
}

