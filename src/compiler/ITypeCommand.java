package compiler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ITypeCommand extends Command {
    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int addressOrImmediate; // 16 bit
    // private int row;
    // private int address;         if commands should save which row and address it has.

    public ITypeCommand(String op, String rs, String rt, String address, String line) {
        super(line);
        this.op = opt_encoding.get(op);
        this.rs = getRegisterNumber(rs);
        this.rt = getRegisterNumber(rt);
    }

    public void setAddressOrImmediate(int addr){
        addressOrImmediate = addr;
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
}
