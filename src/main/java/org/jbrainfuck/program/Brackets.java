package org.jbrainfuck.program;

public class Brackets extends OpCode {

    private OpCode[] opCodes;

    public Brackets(OpCode... opCodes) {
        this.opCodes = opCodes;
    }

    @Override
    public void accept(ProgramVisitor visitor) {
        visitor.visitLoop(this);
    }
}
