package org.jbrainfuck.program;

public abstract class AST {

    public abstract void accept(final ProgramVisitor visitor);

}
