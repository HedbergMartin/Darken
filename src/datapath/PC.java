package datapath;

public class PC {

    int address;

    public void perform(int number){
        address = number;
    }

    public int getAddress(){
        return address;
    }
}
