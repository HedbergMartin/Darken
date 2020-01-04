package datapath;

public class ALU {

    public static int performAdd(int leftOperand, int rightOperan){
        return perform(leftOperand,rightOperan,2);
    }

    public static int perform(int leftOperand, int rightOperand, int operation){

        int ALUresult = 0;

        switch(operation) {
            case 0:

                // AND
                if(leftOperand == 1 && rightOperand == 1){
                    ALUresult = 1;
                }else{
                    ALUresult = 0;
                }

                break;
            case 1:
                // OR

                if(leftOperand == 1 || rightOperand == 1){
                    ALUresult = 1;
                }else{
                    ALUresult = 0;
                }

                break;
            case 2:
                // add

                ALUresult = leftOperand + rightOperand;

                break;
            case 3:
                // subtract

                ALUresult = leftOperand - rightOperand;

                break;
            case 4:
                // set on less than

                if(leftOperand < rightOperand){
                    ALUresult = 1;
                }else{
                    ALUresult = 0;
                }

                break;
            case 5:
                // NOR

                if(leftOperand != 1 && rightOperand != 1){
                    ALUresult = 1;
                }else{
                    ALUresult = 0;
                }

                break;
            case 6:
                //SLL
                ALUresult = leftOperand << rightOperand;
                break;
            case 7:
                //SRL
                ALUresult = leftOperand >> rightOperand;
                break;
            case 8:
                //SRA
                //TODO
                break;
            case 9:
                //JR
                //TODO
            default:
                System.out.println("ALU got invalid operation: " + operation);
        }

        return ALUresult;
    }

}
