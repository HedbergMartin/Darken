package compiler;

import static compiler.Utilities.checkBits;
import static compiler.Utilities.getBinary;
import static compiler.Utilities.getHex;

public class JTypeCommand extends Command {
    private int op; // 6 bit
    private int targetAddress = -1; // 26 bit
    // private int row;
    // private int address;         if commands should save which row and address it has.
    private String address = null;

    public JTypeCommand(String op, String address, String line) {
        super(line);
        this.address = address;
        this.op = 2;

    }

    public JTypeCommand(String op, String line){
        super(line);
        this.targetAddress = 0;
        this.op = 0;

    }


    @Override
    public boolean hasMissingLabelAddress() {
        return targetAddress == -1;
    }

    @Override
    public String getMissingLabelAddress() {
        return address;
    }

    @Override
    public void setMissingLabelAddress(int address) {
        this.targetAddress = address;
    }

    /**
     * Convert a string instruction into
     * a string in hex decimal form.
     * @return the hex decimal string.
     */
    @Override
    public String toHex() {
        if( targetAddress == -1){
            return null;
        }
        String binaryString =  checkBits(6, getBinary(op)) + checkBits(26, getBinary(targetAddress));
        //System.out.println("initialConcatedString: " + binaryString);
        //System.out.println("Hexdecimal: " + checkBits(8, getHex(binaryString)));
        return checkBits(8, getHex(binaryString));
    }
}

