package org.jbrainfuck.program;

import java.util.Arrays;

public class Program extends AST {

    private final OpCode[] operations;

    public Program(final OpCode... operations) {
        this.operations = operations;
    }

    public void accept(final ProgramVisitor visitor) {
        Arrays.stream(operations).forEach(opCode -> opCode.accept(visitor));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(operations).forEach(builder::append);
        return builder.toString();
    }
}
