package datapath;

// (Gränsyta mot CONTROLLERN till GUI ish)

public class Datapath {

    PC pc;
    ALU addAlu1;


    public Datapath() {

        pc = new PC();
        addAlu1 = new ALU();

        // Skapa alla komponenter
        // Koppla ihop komponenter

    }

    public void oneStep(){


        pc.perform(0);

        int pcRes = pc.getAddress();

        System.out.println("pcRes: " + pcRes);

        addAlu1.perform(pcRes,4,2);

        System.out.println("ALUres: " + addAlu1.getALUresult());


        pc.perform(addAlu1.getALUresult());

        addAlu1.perform(pc.getAddress(),4,2);

        System.out.println("ALUres: " + addAlu1.getALUresult());







        /*
        InstructionMemory.readAdress = PC.current;

        RegisterFile.Reg1 = InstructionMemory.??
        RegisterFile.Reg2 = InstructionMemory.??

        mux.0 =


        RegisterFile.writeReg = InstructionMemory.??
        RegisterFile.writeDat = InstructionMemory.??5
        */



    }

    public static void main(String[] args){

        Datapath d = new Datapath();
        d.oneStep();

    }

}
