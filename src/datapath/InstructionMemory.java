package datapath;

import java.util.HashMap;
import java.util.Map;

public class InstructionMemory {

    private Integer instruction;

    private Map<Integer, Integer> instructionMemory = new HashMap();

    public void perform(int readAddress){
        instruction = instructionMemory.get(readAddress);
    }

    public int returnBits(int start, int end){

        String binaryRepresentation = Integer.toBinaryString(instruction);
        String correctRange = binaryRepresentation.substring(31-start,31-end);
        return Math.toIntExact(Long.parseLong(correctRange, 2));
    }

    // To be set by us with the assembly code we provide it with
    public void setInstruction(Integer address, Integer instruction){
        instructionMemory.put(address,instruction);
    }


}
