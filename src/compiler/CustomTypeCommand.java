package compiler;

public class CustomTypeCommand extends Command {
    private String commandLine;
    private int row;
    private int address;
    private boolean islable;

    public CustomTypeCommand(String line, boolean islabel) {
        super(line);
        commandLine = line;
        this.islable = islabel;
        this.row = row;
    }

    public boolean islable(){
        return islable;
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
