package datapath;

import java.util.HashMap;
import java.util.Map;

public class RegisterFile {

    private int readData1;
    private int readData2;


    private Map<Integer, Integer> registers = new HashMap();


    public void perform(int readReg1, int readReg2, int writeReg, int writeData, boolean regWrite){

        if(regWrite){
            registers.put(writeReg,writeData);
        }

        readData1 = registers.get(readReg1);
        readData2 = registers.get(readReg2);

    }

    public int readData1(){
        return readData1;
    }

    public int readData2(){
        return readData2;
    }


    // To be used by GUI to display register content
    public int getRegister(int address){
        return registers.get(address);
    }

}
