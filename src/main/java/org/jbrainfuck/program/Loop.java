package org.jbrainfuck.program;

import java.util.Arrays;

public class Loop extends OpCode {

    private OpCode[] opCodes;

    public Loop(final OpCode... opCodes) {
        this.opCodes = opCodes;
    }

    @Override
    public void accept(final ProgramVisitor visitor) {
        visitor.visitLoop(this);
    }

    public OpCode[] getOpCodes() {
        return opCodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Arrays.stream(opCodes).forEach(builder::append);
        return builder.append("]").toString();
    }
}
