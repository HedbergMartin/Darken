package compiler;

public class CustomTypeCommand extends Command {
    private String commandLine;
    private int row;

    public CustomTypeCommand(String line) {
        super(line);
        commandLine = line;
        this.row = row;
    }

    @Override
    public boolean hasMissingLabelAddress() {
        return false;
    }

    @Override
    public String getMissingLabelAddress() {
        return null;
    }

    @Override
    public void setMissingLabelAddress(int address) {
        return;
    }

    @Override
    public String toHex() {
        return null;
    }

    public int getRow() { return row; }

}
