package datapath;
// (Gränsyta mot CONTROLLERN till GUI ish)

import java.util.Map;

public class Datapath {

    // IF - section
    private PC pc;
    private InstructionMemory instructionMemory;

    // ID - section
    private Control control;
    private RegisterFile registerFile;
    private ALUControl aluControl;

    // EX - section
    // Note we have a flying AND here!

    // MEM - section
    private DataMemory dataMemory;

    // WB - section

    public Datapath() {

        // IF - section
        pc = new PC();
        instructionMemory = new InstructionMemory();

        // ID - section
        control = new Control();
        registerFile = new RegisterFile();
        aluControl = new ALUControl();

        // EX - section
        // Note we have a flying AND here!

        // MEM - section
        dataMemory = new DataMemory();

        // WB - section

    }

    public int getCurrentInstructionAddress(){
        return pc.getAddress();
    }

    public void appendInstruction(int instruction){
        instructionMemory.appendInstruction(instruction);
    }

    // To be displayed in GUI
    public Map<Integer, Integer> getRegisterDataMap(){
        return registerFile.getRegisterMap();
    }

    // To be displayed in GUI
    public Map<Integer, Integer> getMemoryDataMap(){
        return dataMemory.getMemoryMap();
    }

    public boolean oneStep(){
        // IF ----
        int currentInstuctionAddress = pc.getAddress();
        instructionMemory.perform(currentInstuctionAddress);

        if(!instructionMemory.hasInstruction()){
            System.out.println("Done with program!");
            return false;
        }

        int nextAddress = ALU.performAdd(currentInstuctionAddress,4); // Used as next address if no jump (+4)

        control.perform(instructionMemory.returnBits(26,31), instructionMemory.returnBits(0, 5));
        aluControl.perform(instructionMemory.returnBits(0,5),control.isALUOP0(),control.isALUOp1());


        // ID ---
        // Register file ----
        int read1 = instructionMemory.returnBits(21,25);
        int read2 = instructionMemory.returnBits(16,20);
        int writeReg = MUX.perform(read2,instructionMemory.returnBits(11,15),control.isRegDst());

        registerFile.readData(read1,read2);
        // Register file ----



        // Data memory ------

        int signExtend = (short)instructionMemory.returnBits(0,15);
        System.out.println(control.isALUSrc());
        int ALUres = ALU.perform(registerFile.readData1(),
                MUX.perform(registerFile.readData2(),signExtend,control.isALUSrc()),
                aluControl.getALUOp(), instructionMemory.returnBits(6, 10));

        int address = ALUres;

        dataMemory.perform(address,registerFile.readData2(),control.isMemWrite(),control.isMemRead());

        // Data memory ------

        int lastMux = MUX.perform(ALUres, dataMemory.getReadData(),control.isMemtoReg());

        if (writeReg == 0) {
        	System.out.println();
        	registerFile.readData(0, 13);
        }
        registerFile.writeData(writeReg,lastMux,control.isRegWrite());
        int jumpAddress = ShiftLeftTwo.perform(instructionMemory.returnBits(0, 25)) + (nextAddress & -268435456);//Bitmask for bits 31-28

        int aluResult = ALU.performAdd(nextAddress, ShiftLeftTwo.perform(signExtend));

        int muxResult1 = MUX.perform(nextAddress, aluResult, control.isBranch() && ALUres == 0);
        
        int muxResult2 = MUX.perform(muxResult1, jumpAddress, control.isJump());
        
        int muxJumpReg = MUX.perform(muxResult2, registerFile.readData1(), control.isJumpReg());
        
        pc.perform(muxJumpReg);

        return true;
    }

	public void reset() {
        pc = new PC();
        instructionMemory = new InstructionMemory();
        control = new Control();
        registerFile = new RegisterFile();
        aluControl = new ALUControl();
        dataMemory = new DataMemory();
	}
}
