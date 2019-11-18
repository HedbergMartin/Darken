package compiler;
import java.util.ArrayList;

public class ITypeCommand extends Command {
    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int addressOrImmediate; // 16 bit
    // private int row;
    // private int address;         if commands should save which row and address it has.

    public ITypeCommand(String line) {
        super(line);
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
}
