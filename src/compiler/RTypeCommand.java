package compiler;
import java.util.ArrayList;

public class RTypeCommand extends Command {
    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int rd; // 5 bit
    private int shamt; // 5 bit
    private int funct; // 6 bit
    // private int row;
    // private int address;         if commands should save which row and address it has.

    public RTypeCommand(String line) {
        // set private ints
        super(line);
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
        System.out.println("initialValues: " + initialValues);
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

        System.out.println("initialConcatedString: " + initialConcatedString);
        System.out.println("Hexdecimal: " + checkBits(8, getHex(initialConcatedString)));
        return checkBits(8, getHex(initialConcatedString));
    }
}



