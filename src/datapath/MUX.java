package datapath;

public class MUX {

    public static int perform(int first, int second, boolean control){

        if(control){
            return first;
        }else{
            return second;
        }
    }

}
