package datapath;

import java.util.HashMap;
import java.util.Map;

public class DataMemory {

    int readData;

    private Map<Integer, Integer> memoryMap = new HashMap(); // Address -> data


    // Returns readData
    public void perform(int address, int writeData, boolean memWrite, boolean memRead){

        if(memWrite){
            memoryMap.put(address,writeData);
        }

        if(memRead){
            readData = memoryMap.get(address);
        }

    }

    public int getReadData(){
        return readData;
    }
}
