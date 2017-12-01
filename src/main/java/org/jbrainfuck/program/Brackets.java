package org.jbrainfuck.program;

import java.util.Arrays;

public class Brackets extends OpCode {

    private OpCode[] opCodes;

    public Brackets(OpCode... opCodes) {
        this.opCodes = opCodes;
    }

    @Override
    public void accept(ProgramVisitor visitor) {
        visitor.visitLoop(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Arrays.stream(opCodes).forEach(builder::append);
        return builder.append("]").toString();
    }
}
