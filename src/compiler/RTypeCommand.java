package compiler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static compiler.Utilities.checkBits;
import static compiler.Utilities.getBinary;
import static compiler.Utilities.getHex;

public class RTypeCommand extends Command {
    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int rd; // 5 bit
    private int shamt; // 5 bit
    private int funct; // 6 bit
    
    public RTypeCommand(ArrayList<String> args, String line, int row) {
    	super(line, row);
    	this.op = 0;
    	
    	switch (args.get(0).toLowerCase()) {
		case "sll":
            this.rs = 0;
            this.rt = getRegisterNumber(args.get(2));
            this.rd = getRegisterNumber(args.get(1));
            this.shamt = Integer.parseInt(args.get(3));
            this.funct = func_encoding.get(args.get(0));
			break;

		case "jr":
            this.rs = getRegisterNumber(args.get(1));
            this.rt = 0;
            this.rd = 0;
            this.shamt = 0;
            this.funct = func_encoding.get(args.get(0));
			break;
			
		default:
            this.rs = getRegisterNumber(args.get(3));
            this.rt = getRegisterNumber(args.get(2));
            this.rd = getRegisterNumber(args.get(1));
            this.shamt = 0;
            this.funct = func_encoding.get(args.get(0));
			break;
		}
    }

    @Override
    public boolean hasMissingLabelAddress() {
        return false;
    }

    @Override
    public String getMissingLabelAddress() {
        return null;
    }

    @Override
    public void setMissingLabelAddress(int address) {
        return;
    }

    /**
     * Convert a string instruction into
     * a string in hex decimal form.
     * @return the hex decimal string.
     */
    @Override
    public String toHex() {
    	int result = op;
    	result = result << 5;
    	result += rs;
    	result = result << 5;
    	result += rt;
    	result = result << 5;
    	result += rd;
    	result = result << 5;
    	result += shamt;
    	result = result << 6;
    	result += funct;

        return String.format("%08X", result);
    }

    private static Map<String, Integer> func_encoding = new HashMap<String, Integer>();
    static {
        func_encoding.put("add", 32);
        func_encoding.put("sub", 34);
        func_encoding.put("and", 36);
        func_encoding.put("or", 37);
        func_encoding.put("nor", 39);
        func_encoding.put("slt", 42);
        func_encoding.put("sll", 0);//Speciell
        func_encoding.put("jr", 8);//Speciell
    }
}



