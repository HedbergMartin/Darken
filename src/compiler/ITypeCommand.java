package compiler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ITypeCommand extends Command {
    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int addressOrImmediate = -1; // 16 bit
    private String address;

    public ITypeCommand(String op, String rs, String rt, String line) {
        super(line);
        this.op = opt_encoding.get(op);
        this.rs = getRegisterNumber(rs);
        this.rt = 0;
        addressOrImmediate = getRegisterNumber(rt);
    }

    public ITypeCommand(String op, String rs, String rt, String address, String line) {
        super(line);
        this.op = opt_encoding.get(op);
        this.rs = getRegisterNumber(rs);
        this.rt = getRegisterNumber(rt);
        this.address = address;

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
        this.addressOrImmediate = address;
    }

    @Override
    public String toHex() {
        ArrayList<Integer> initialValues = new ArrayList<Integer>() {{
            add(op);
            add(rs);
            add(rt);
            add(addressOrImmediate);

        }};
        System.out.println("initialValues: " + initialValues);
        String initialConcatedString = new String();

        int counter = 0;
        for (int content: initialValues ) {
            if(counter == 0 ) {
                initialConcatedString = initialConcatedString + checkBits(6, getBinary(content));
            } else if(counter == 3){
                initialConcatedString = initialConcatedString + checkBits(16, getBinary(content));
            } else {
                initialConcatedString = initialConcatedString + checkBits(5, getBinary(content));
            }
            counter++;
        }

        System.out.println("initialConcatedString: " + initialConcatedString);
        System.out.println("Hexdecimal: " + getHex(initialConcatedString));

        return getHex(initialConcatedString);
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
