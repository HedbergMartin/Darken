package datapath;

// (Gränsyta mot CONTROLLERN till GUI ish)

public class Datapath {

    // IF - section
    PC pc;
    InstructionMemory instructionMemory;

    // ID - section
    Control control;
    RegisterFile registerFile;
    ALUControl aluControl;

    // EX - section
    // Note we have a flying AND here!

    // MEM - section
    DataMemory dataMemory;

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

    public void oneStep(){


        // IF ----
        int currentInstuctionAddress = pc.getAddress();
        instructionMemory.perform(currentInstuctionAddress);


        int nextAddress = ALU.performAdd(currentInstuctionAddress,4); // Used as next address if no jump (+4)

        control.perform(instructionMemory.returnBits(31,26));
        aluControl.perform(instructionMemory.returnBits(5,0),control.isALUOP0(),control.isALUOp1());


        // ID ---
        // Register file ----
        boolean regWrite = control.isRegWrite();
        int read1 = instructionMemory.returnBits(25,21);
        int read2 = instructionMemory.returnBits(20,16);
        int writeReg = MUX.perform(read2,instructionMemory.returnBits(15,11),control.isRegDst());
        int writeData = 0; // Nothing in IF-phase

        registerFile.perform(read1,read2,writeReg,writeData,regWrite);
        // Register file ----



        // Data memory ------


        int ALUres = ALU.perform(registerFile.readData1(),
                MUX.perform(registerFile.readData2(),instructionMemory.returnBits(15,0),control.isALUSrc()),
                aluControl.getALUOp());
        int address = ALUres;

        dataMemory.perform(address,registerFile.readData2(),control.isMemWrite(),control.isMemRead());

        // Data memory ------





        int lastMux = MUX.perform(dataMemory.getReadData(),ALUres,control.isMemtoReg());
        registerFile.perform(read1,read2,writeReg,lastMux,control.isRegWrite());

    }

    private  void datapathStage_IF(){


    }

    private void datapathStage_ID(){


    }

    private void datapathStage_EX(){


    }

    private void datapathStage_MEM(){


    }

    private void datapathStage_WB(){


    }





    public static void main(String[] args){

        Datapath d = new Datapath();
        d.oneStep();

    }

}
