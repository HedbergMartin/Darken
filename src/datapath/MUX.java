package datapath;

public class MUX {

    private int result;

    public void perform(int first, int second, boolean control){

        if(control){
            result = first;
        }else{
            result = second;
        }
    }

    public int getResult(){
        return result;
    }

}
