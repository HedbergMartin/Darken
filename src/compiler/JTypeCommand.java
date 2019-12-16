package compiler;

import java.util.ArrayList;

public class JTypeCommand extends Command {
    private int targetAddress = -1; // 26 bit
    private String address = null;
    
    public JTypeCommand(ArrayList<String> args, String line, int row) {
    	super(line, row);
		this.setOpcode(2);
		this.address = args.get(1);
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
    	int result = this.getOpcode();
    	result = result << 26;
    	result += targetAddress;
    	
        return String.format("%08X", result);
    }
}

