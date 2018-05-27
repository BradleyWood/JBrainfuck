package org.jbrainfuck.program;

public interface ProgramVisitor {

    void visitOpcode(final OpCode opCode);

    void visitLoop(final Loop br);

}
