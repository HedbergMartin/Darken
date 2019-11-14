package compiler;

public class CustomTypeCommand extends Command {


    public CustomTypeCommand(String line) {
        super(line);
    }

    @Override
    public int toHex() {
        return 0;
    }
}
