package compiler;

public class ITypeCommand extends Command {


    public ITypeCommand(String line) {
        super(line);
    }

    @Override
    public int toHex() {
        return 0;
    }
}
