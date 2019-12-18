package datapath;

public class PC {

    private int address;

    public void perform(int number){
        address = number;
    }

    public int getAddress(){
        return address;
    }
}
