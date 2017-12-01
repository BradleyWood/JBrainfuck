package org.jbrainfuck.antlr.visitor;

import org.jbrainfuck.BrainfuckBaseVisitor;
import org.jbrainfuck.BrainfuckParser;
import org.jbrainfuck.program.Brackets;
import org.jbrainfuck.program.OpCode;

public class BracketsVisitor extends BrainfuckBaseVisitor<Brackets> {

    private BracketsVisitor() {
    }

    @Override
    public Brackets visitBrackets(BrainfuckParser.BracketsContext ctx) {
        OpCode[] opCodes = new OpCode[ctx.command().size()];

        for (int i = 0; i < ctx.command().size(); i++) {
            opCodes[i] = ctx.command(i).accept(OpCodeVisitor.INSTANCE);
        }
        return new Brackets(opCodes);
    }

    public static BracketsVisitor INSTANCE = new BracketsVisitor();
}
