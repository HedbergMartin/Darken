package datapath;

public class MUX {

    int result;

    public void perform(int first, int second, boolean control){

        if(control){
            result = 0;
        }else{
            result = 1;
        }
    }

    public int getResult(){
        return result;
    }

}
