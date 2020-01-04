package datapath;

public class PC {

    private int address = 0;

    public void perform(int number){
        address = number;
    }

    public int getAddress(){
        return address;
    }
}
