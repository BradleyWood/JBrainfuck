package org.jbrainfuck.program;

import org.jbrainfuck.Command;

public class OpCode extends AST {

    private Command cmd;

    public OpCode(final Command cmd) {
        this.cmd = cmd;
    }

    public OpCode() {

    }

    public Command getCmd() {
        return cmd;
    }

    @Override
    public void accept(final ProgramVisitor visitor) {
        visitor.visitOpcode(this);
    }

    @Override
    public String toString() {
        return "" + cmd.ch;
    }
}
