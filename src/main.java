import compiler.Compiler;

import java.util.ArrayList;

public class main {


    public static void main(String[] args) {
        System.out.println("Starting program");

        // opcode	rs	rt	rd	shift (shamt)	funct

        Compiler comp = new Compiler("testFile1.s");

        /*
        Integer opcode = 2; // Addi 0010
        Integer rs = 10;
        Integer rt = 11;
        Integer rd = 12;
        Integer shamt = 0;
        Integer funct = 15;

        ArrayList<String> instructionFieldName =new ArrayList<String>();
        instructionFieldName.add("opcode");
        instructionFieldName.add("rs");
        instructionFieldName.add("rt");
        instructionFieldName.add("rd");
        instructionFieldName.add("shamt");
        instructionFieldName.add("funct");

        ArrayList<Integer> instructionInteger =new ArrayList<Integer>();
        instructionInteger.add(opcode);
        instructionInteger.add(rs);
        instructionInteger.add(rt);
        instructionInteger.add(rd);
        instructionInteger.add(shamt);
        instructionInteger.add(funct);


        System.out.println("--");
        for (int i = 0; i<6;i++){
            System.out.println(instructionFieldName.get(i) + "  " + getHex(instructionInteger.get(i)));
        }

        System.out.println("---");

        createHexFromFields(opcode,rs,rt,rd,shamt,funct);
        */
    }

    public static String createHexFromFields(Integer op, Integer rs, Integer rt, Integer rd, Integer shamt, Integer funct){
// 10 0000 0000 1010 0000 0000 0000 0010
        // convert all field values binary
        // sätt ihop alla fields i en sträng
        // plocka ut 4 bit i taget och omvandla till hexadecimal.
        // sätt ihop alla
        String initialConcatedString = getHex(op) + getHex(rs) + getHex(rt) + getHex(rd) + getHex(shamt) + getHex(funct);
        System.out.println("initialConcatedString: " + initialConcatedString);
        Integer shiftToAdd = 0;


        // Printing pretty
        System.out.println("opcode" + " " + "rs" + " " + "rt" + " " + "rd" + " " + "rd" + " " + "funct");
        System.out.println(getBinary(op) + " " + getBinary(rs) + " " + getBinary(rt) + " " + getBinary(rd) + " " + getBinary(shamt) + " " + getBinary(funct));

        String completedHexString = "0x" + getHex(op) + getHex(rs) + getHex(rt) + getHex(rd) + getHex(shamt) + getHex(funct);
        System.out.println(completedHexString);

        return completedHexString;
    }

    public static String getBinary(Integer number){
        return Integer.toString(number,2);
    }

    public static String getHex(Integer number){
        return Integer.toString(number,16);
    }

    public static Integer binaryStrToInt(String binary){
        return Integer.parseInt(binary,2);
    }

}