package org.jbrainfuck.program;

import org.jbrainfuck.Command;

public class OpCode extends AST {

    private Command cmd;

    public OpCode(Command cmd) {
        this.cmd = cmd;
    }

    public OpCode() {

    }

    public Command getCmd() {
        return cmd;
    }

    @Override
    public void accept(ProgramVisitor visitor) {
        visitor.visitOpcode(this);
    }

    @Override
    public String toString() {
        return "" + cmd.ch;
    }
}
