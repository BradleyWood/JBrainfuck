package org.jbrainfuck;

import org.jbrainfuck.antlr.BFParser;
import org.jbrainfuck.interpreter.Interpreter;
import org.jbrainfuck.program.Program;

public class Application {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Must specify source file!");
            return;
        }

        String file = args[0];

        BFParser parser = new BFParser(file);
        Program program = parser.parse();

        if (program == null) {
            return;
        }

        System.out.println("-------Parsed Input-------");
        System.out.println(program);
        System.out.println("--------------------------");
        System.out.println();

        Interpreter interpreter = new Interpreter(program);
        interpreter.run();
    }
}
