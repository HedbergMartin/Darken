package datapath;

public class ALUControl {

    private int ALUOpOut;

    public void perform(int instruction, int ALUOpIn){

        // Page 260 in textbook
        switch(ALUOpIn) {
            case 0: // LW/SW

                if(instruction == 43 || instruction == 35){ // lw/sw instruction opcode
                    ALUOpOut = 2;
                }

                break;
            case 1: // Branch equal
                ALUOpOut = 6;

                break;
            case 2:

                switch(instruction) { // R-type funct field
                    case 32: // add
                        ALUOpOut = 2;
                        break;
                    case 34: // subtract
                        ALUOpOut = 6;
                        break;
                    case 36: // AND
                        ALUOpOut = 0;
                        break;
                    case 37: // or
                        ALUOpOut = 1;
                        break;
                    case 42: // set on less than
                        ALUOpOut = 7;
                        break;
                    default:
                        System.out.println("ALU got invalid instruction: " + instruction);
                }

                break;
            default:
                System.out.println("ALU got invalid ALUOpIn: " + ALUOpIn);
        }

    }


    public int getALUOp(){
        return ALUOpOut;
    }

}
