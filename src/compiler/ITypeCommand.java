package compiler;
import java.util.HashMap;
import java.util.Map;

public class ITypeCommand extends Command {
    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int addressOrImmediate = -1; // 16 bit
    private String address;
    private int row;

    //Är för sw och lw
    public ITypeCommand(String op, String rt, String target, String line) {
        super(line);
        this.op = opt_encoding.get(op);
        this.rs = getBase(target);
        this.rt = getRegisterNumber(rt);
        addressOrImmediate = getOffset(target);
    }

    public ITypeCommand(String op, String rs, String rt, String address, int row, String line) {
        super(line);
        this.op = opt_encoding.get(op);
        this.rs = getRegisterNumber(rs);
        this.rt = getRegisterNumber(rt);
        this.address = address;
        this.row = row;	

        if(isInteger(address)){
            addressOrImmediate = Integer.parseInt(address);
        }

    }

    @Override
    public boolean hasMissingLabelAddress() {
        return addressOrImmediate == -1;
    }

    @Override
    public String getMissingLabelAddress() {
        return address;
    }

    @Override
    public void setMissingLabelAddress(int address) {
        this.addressOrImmediate = (address >> 2) - (this.row + 1);
    }

    @Override
    public String toHex() {
    	int result = op;
        result = result << 5;
        result += rs;
        result = result << 5;
        result += rt;
        result = result << 16;
        result += (Short.MAX_VALUE & addressOrImmediate);
        
        return String.format("%08X", result);
    }

    public static Map<String, Integer> opt_encoding = new HashMap<String, Integer>();
    static {
        opt_encoding.put("lw", 35);
        opt_encoding.put("sw", 43);
        opt_encoding.put("beq", 4);
        opt_encoding.put("addi", 8);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
