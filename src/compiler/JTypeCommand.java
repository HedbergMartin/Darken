package compiler;

public class JTypeCommand extends Command {

    private int op; // 6 bit
    private int targetAddress; // 26 bit

    public JTypeCommand(String line) {
        super(line);
    }

    @Override
    public int toHex() {
        return 0;
    }
}
