package org.jbrainfuck.antlr.visitor;

import org.jbrainfuck.BrainfuckBaseVisitor;
import org.jbrainfuck.BrainfuckParser;
import org.jbrainfuck.Command;
import org.jbrainfuck.program.OpCode;

public class OpCodeVisitor extends BrainfuckBaseVisitor<OpCode> {

    private OpCodeVisitor() {
    }

    @Override
    public OpCode visitCommand(BrainfuckParser.CommandContext ctx) {
        OpCode opCode = null;

        if (ctx.loop() != null) {
            opCode = ctx.loop().accept(LoopVisitor.INSTANCE);
        } else {
            if (ctx.ADD() != null) {
                opCode = new OpCode(Command.INC);
            } else if (ctx.SUB() != null) {
                opCode = new OpCode(Command.DEC);
            } else if (ctx.GT() != null) {
                opCode = new OpCode(Command.INC_PTR);
            } else if (ctx.LT() != null) {
                opCode = new OpCode(Command.DEC_PTR);
            } else if (ctx.DOT() != null) {
                opCode = new OpCode(Command.OUTPUT);
            } else if (ctx.COMMA() != null) {
                opCode = new OpCode(Command.INPUT);
            }
        }

        if (opCode == null) {
            throw new RuntimeException("Unhandled opcode");
        }

        return opCode;
    }

    public static OpCodeVisitor INSTANCE = new OpCodeVisitor();
}
