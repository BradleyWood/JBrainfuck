package org.jbrainfuck.antlr.visitor;

import org.jbrainfuck.BrainfuckBaseVisitor;
import org.jbrainfuck.BrainfuckParser;
import org.jbrainfuck.program.OpCode;
import org.jbrainfuck.program.Program;

public class BFFileVisitor extends BrainfuckBaseVisitor<Program> {

    @Override
    public Program visitProgram(final BrainfuckParser.ProgramContext ctx) {
        final OpCode[] opCodes = new OpCode[ctx.command().size()];

        for (int i = 0; i < ctx.command().size(); i++) {
            opCodes[i] = ctx.command(i).accept(OpCodeVisitor.INSTANCE);
        }
        return new Program(opCodes);
    }
}
