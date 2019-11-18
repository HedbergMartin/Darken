package compiler;

public class JTypeCommand extends Command {
    private int op; // 6 bit
    private int targetAddress; // 26 bit
    // private int row;
    // private int address;         if commands should save which row and address it has.


    public JTypeCommand(String op, String address, String line) {
        super(line);

    }

    public void setTargetAddress(int addr){
        targetAddress = addr;
    }
    /**
     * Convert a string instruction into
     * a string in hex decimal form.
     * @return the hex decimal string.
     */
    @Override
    public String toHex() {
        String binaryString = checkBits(6, getBinary(op)) + checkBits(26, getBinary(targetAddress));
        System.out.println("initialConcatedString: " + binaryString);
        System.out.println("Hexdecimal: " + checkBits(8, getHex(binaryString)));
        return checkBits(8, getHex(binaryString));
    }
}

