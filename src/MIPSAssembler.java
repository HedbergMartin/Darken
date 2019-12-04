import compiler.Compiler;

public class MIPSAssembler {

    public static void main(String[] args) {
        if (args.length != 3){
            System.exit(-1);
        }
        Compiler compiler = new Compiler(args[0],args[1] ,args[2]);
        compiler.prettyPrintFile();
        compiler.toHexFile();
    }
}
