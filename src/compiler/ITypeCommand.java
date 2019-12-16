package compiler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ITypeCommand extends Command {
    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int addressOrImmediate; // 16 bit
    private String address = null;

    public ITypeCommand(ArrayList<String> args, String line, int row) {
    	super(line, row);
    	this.op = opt_encoding.get(args.get(0));
    	
    	switch (args.get(0).toLowerCase()) {
		case "beq":
	        this.rs = getRegisterNumber(args.get(1));
	        this.rt = getRegisterNumber(args.get(2));
	        this.address = args.get(3);
			break;
			
		case "addi":
	        this.rs = getRegisterNumber(args.get(2));
	        this.rt = getRegisterNumber(args.get(1));
	        addressOrImmediate = Integer.parseInt(args.get(3));
			break;
			
		default: // SW and LW are defaults
	        this.rs = getBase(args.get(2));
	        this.rt = getRegisterNumber(args.get(1));
	        addressOrImmediate = getOffset(args.get(2));
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
        this.addressOrImmediate = (address >> 2) - (this.getRow() + 1);
        this.address = null;
    }

    @Override
    public String toHex() {
    	int result = op;
        result = result << 5;
        result += rs;
        result = result << 5;
        result += rt;
        result = result << 16;
        //65535 Is mask for 16first bits
        result += (65535 & addressOrImmediate);
        
        return String.format("%08X", result);
    }

    public static Map<String, Integer> opt_encoding = new HashMap<String, Integer>();
    static {
        opt_encoding.put("lw", 35);
        opt_encoding.put("sw", 43);
        opt_encoding.put("beq", 4);
        opt_encoding.put("addi", 8);
    }
}
