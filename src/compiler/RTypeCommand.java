package compiler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static compiler.Utilities.checkBits;
import static compiler.Utilities.getBinary;
import static compiler.Utilities.getHex;

public class RTypeCommand extends Command {
    private int op = 0; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int rd; // 5 bit
    private int shamt = 0; // 5 bit
    private int funct; // 6 bit
    // private int row;
    // private int address;         if commands should save which row and address it has.

    public RTypeCommand(String op, String rd, String rt, String rs, String line) {
        // set private ints
        super(line);

        if(op.compareTo("sll") > 0){
            this.rs = 0;
            this.rt = getRegisterNumber(rs);
            this.rd = getRegisterNumber(rd);
            this.shamt = getRegisterNumber(rt);
            this.funct = func_encoding.get(op);
        } else if(op.compareTo("jr") > 0){
            this.rs = getRegisterNumber(rd);
            this.rt = 0;
            this.rd = 0;
            this.shamt = 0;
            this.funct = func_encoding.get(op);
        } else {
            this.rs = getRegisterNumber(rs);
            this.rt = getRegisterNumber(rt);
            this.rd = getRegisterNumber(rd);
            this.shamt = 0;
            this.funct = func_encoding.get(op);
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
        ArrayList<Integer> initialValues = new ArrayList<Integer>() {{
            add(op);
            add(rs);
            add(rt);
            add(rd);
            add(shamt);
            add(funct);
        }};
        String initialConcatedString = new String();

        int counter = 0;
        for (int content: initialValues ) {
            if(counter == 0 || counter == 5) {
                initialConcatedString = initialConcatedString + checkBits(6, getBinary(content));
            } else {
                initialConcatedString = initialConcatedString + checkBits(5, getBinary(content));
            }
            counter++;
        }

        return checkBits(8, getHex(initialConcatedString));
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



