package org.jbrainfuck.antlr.visitor;

import org.jbrainfuck.BrainfuckBaseVisitor;
import org.jbrainfuck.BrainfuckParser;
import org.jbrainfuck.program.Loop;
import org.jbrainfuck.program.OpCode;

public class LoopVisitor extends BrainfuckBaseVisitor<Loop> {

    private LoopVisitor() {
    }

    @Override
    public Loop visitLoop(final BrainfuckParser.LoopContext ctx) {
        final OpCode[] opCodes = new OpCode[ctx.command().size()];

        for (int i = 0; i < ctx.command().size(); i++) {
            opCodes[i] = ctx.command(i).accept(OpCodeVisitor.INSTANCE);
        }
        return new Loop(opCodes);
    }

    static final LoopVisitor INSTANCE = new LoopVisitor();
}
