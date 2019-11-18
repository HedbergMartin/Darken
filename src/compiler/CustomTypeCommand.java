package compiler;

public class CustomTypeCommand {
    private String commandLine;
    private int row;
    private int address;

    public CustomTypeCommand(String line, int row, int addr ) {
        commandLine = line;
        this.row = row;
        address = addr;
    }

    public int getRow() { return row; }

}
