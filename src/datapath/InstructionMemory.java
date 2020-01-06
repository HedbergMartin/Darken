package datapath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InstructionMemory {

    private Integer instruction = null;

    private ArrayList<Integer> instructionMemory = new ArrayList<>();

    public void perform(int readAddress){

        if(instructionMemory.size() == 0){
            System.out.println("InstructionMemory Error: No instructions have been loaded.");
            return;
        }

        instruction = instructionMemory.get(readAddress >> 2);
    }

    public int returnBits(int start, int end){


        String binaryRepresentation = String.format("%32s",Integer.toBinaryString(instruction)).replace(' ', '0');
        String correctRange = binaryRepresentation.substring(31-end,32-start);
        return Math.toIntExact(Long.parseLong(correctRange, 2));
    }

    // To be set by us with the assembly code we provide it with
    public void setInstruction(Integer address, Integer instruction){
        instructionMemory.add(instruction);
    }

    public void appendInstruction(int instruction){
        instructionMemory.add(instruction);
    }

}
