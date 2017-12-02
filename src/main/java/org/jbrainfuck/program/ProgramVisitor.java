package org.jbrainfuck.program;

public interface ProgramVisitor {

    void visitOpcode(OpCode opCode);

    void visitLoop(Loop br);

}
