package datapath;

import java.util.HashMap;
import java.util.Map;

public class RegisterFile {

    private Integer readData1 = 0;
    private Integer readData2 = 0;


    private Map<Integer, Integer> registers = new HashMap();


    public void perform(int readReg1, int readReg2, int writeReg, int writeData, boolean regWrite){

        if(regWrite){

            System.out.println("Writing " + writeData +" to  " + writeReg );
            registers.put(writeReg,writeData);
        }

        readData1 = registers.get(readReg1);
        readData2 = registers.get(readReg2);

    }

    public int readData1(){

        if(readData1 == null){
            readData1 = 0;
        }

        return readData1;
    }

    public int readData2(){

        if(readData2 == null){
            readData2 = 0;
        }

        return readData2;
    }


    // To be used by GUI to display register content
    public Map<Integer, Integer> getRegisterMap(){
        return registers;
    }

}
