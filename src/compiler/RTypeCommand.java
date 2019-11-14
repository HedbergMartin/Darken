package compiler;

public class RTypeCommand extends Command {

    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int rd; // 5 bit
    private int shamt; // 5 bit
    private int funct; // 6 bit

    public RTypeCommand(String line) {
        super(line);
    }

    @Override
    public int toHex() {
        return 0;
    }
}
