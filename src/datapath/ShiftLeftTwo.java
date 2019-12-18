package datapath;

public class ShiftLeftTwo {

    private int output;

    public void perform(int input){
        output = input << 2;
    }

    public int getOutput(){
        return output;
    }
}
