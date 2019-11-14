package compiler;

public class ITypeCommand extends Command {

    private int op; // 6 bit
    private int rs; // 5 bit
    private int rt; // 5 bit
    private int addressOrImmediate; // 16 bit

    public ITypeCommand(String line) {
        super(line);
    }

    @Override
    public int toHex() {
        return 0;
    }
}
