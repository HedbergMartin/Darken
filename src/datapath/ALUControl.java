package datapath;

public class ALUControl {

    private int ALUOpOut;

    public void perform(int instruction, boolean ALUOp0, boolean ALUOp1){

        // Page 260 in textbook
        if (!ALUOp0 && !ALUOp1) {

            ALUOpOut = 2;

        } else if (ALUOp0) {
        	//Beq
            ALUOpOut = 3;

        }else {
            switch(instruction) { // R-type funct field
                case 32: // add
                    ALUOpOut = 2;
                    break;
                case 34: // subtract
                    ALUOpOut = 3;
                    break;
                case 36: // AND
                    ALUOpOut = 0;
                    break;
                case 37: // or
                    ALUOpOut = 1;
                    break;
                case 42: // set on less than
                    ALUOpOut = 4;
                    break;
                case 39: //Nor
                    ALUOpOut = 5;
                    break;
                case 0: //Sll
                    ALUOpOut = 6;
                    break;
                case 2: //srl
                    ALUOpOut = 7;
                    break;
                case 3: //sra
                    ALUOpOut = 8;
                    break;
                case 8: //jr
                    ALUOpOut = 9;
                    break;
                default:
                    System.out.println("ALU CONTROLFUCK got invalid instruction: " + instruction + " Al0 " + ALUOp0 + " Alu1 " + ALUOp1);
                    ALUOpOut = 2;
                    break;
            }

        }

    }


    public int getALUOp(){
        return ALUOpOut;
    }

}
