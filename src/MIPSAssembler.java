import compiler.Command;
import compiler.MipsCompiler;
import compiler.NopCommand;

public class MIPSAssembler {

    public static void main(String[] args) {
        if (args.length != 3){
            System.exit(-1);
        }
        MipsCompiler compiler = new MipsCompiler(args[0],args[1] ,args[2]);
        compiler.prettyPrintFile();
        compiler.toHexFile();
    }
}
