package datapath;

import java.util.HashMap;
import java.util.Map;

public class RegisterFile {

    private Integer readData1 = 0;
    private Integer readData2 = 0;


    private Map<Integer, Integer> registers = new HashMap<Integer, Integer>();


    public void readData(int readReg1, int readReg2){
        if(registers.get(readReg1) != null){
            readData1 = registers.get(readReg1);
        }
        if(registers.get(readReg2) != null){
            readData2 = registers.get(readReg2);
        }

    }
    
    public void writeData(int writeReg, int writeData, boolean regWrite) {
        if(regWrite) {
            registers.put(writeReg,writeData);
        }
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
