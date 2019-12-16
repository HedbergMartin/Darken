package compiler;

public class CustomTypeCommand extends Command {

    public CustomTypeCommand(String line) {
        super(line, 0);
    }

    @Override
    public String toHex() {
        return null;
    }
    
    
}
