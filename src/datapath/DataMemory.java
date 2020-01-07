package datapath;

import java.util.HashMap;
import java.util.Map;

public class DataMemory {

    private int readData = 0;

    private Map<Integer, Integer> memoryMap = new HashMap<Integer, Integer>(); // Address -> data


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


    // To be used to provide GUI with memory data
    public Map<Integer, Integer> getMemoryMap(){
        return memoryMap;
    }

}
