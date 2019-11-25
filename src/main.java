import compiler.Compiler;

import java.util.ArrayList;

public class main {


    public static void main(String[] args) {
        System.out.println("Starting program");

        // opcode	rs	rt	rd	shift (shamt)	funct

        Compiler comp = new Compiler("testFile1.s");
        Compiler comp2 = new Compiler("testFile2.s");


        comp.toHexFile();

        //comp2.toHexFile();
    }

}