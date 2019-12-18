package datapath;

public class ALU {

    int ALUresult;

    public void perform(int leftOperand, int rightOperand, int operation){

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
            default:
                System.out.println("ALU got invalid operation: " + operation);
        }


    }

    public int getALUresult(){
        return ALUresult;
    }

    public int getZero(){
        return 0;
    }
}
