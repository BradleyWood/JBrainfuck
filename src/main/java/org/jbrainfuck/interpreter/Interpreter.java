package org.jbrainfuck.interpreter;

import org.jbrainfuck.program.Brackets;
import org.jbrainfuck.program.OpCode;
import org.jbrainfuck.program.Program;
import org.jbrainfuck.program.ProgramVisitor;

import java.io.IOException;
import java.util.Arrays;

public class Interpreter implements ProgramVisitor {

    private final Program program;

    private byte[] memory = new byte[4096];
    private int dataPointer = 0;

    public Interpreter(Program program) {
        this.program = program;
    }

    public void run() {
        program.accept(this);
    }

    @Override
    public void visitOpcode(OpCode opCode) {
        switch (opCode.getCmd()) {
            case OUTPUT:
                System.out.print((char) getByte());
                break;
            case INPUT:
                readByte();
                break;
            case INC:
                inc();
                break;
            case DEC:
                dec();
                break;
            case INC_PTR:
                dataPointer++;
                break;
            case DEC_PTR:
                dataPointer--;
                break;
        }
    }

    @Override
    public void visitLoop(Brackets br) {
        if (getByte() == 0) {
            return;
        }
        Arrays.stream(br.getOpCodes()).forEach(op -> op.accept(this));
        if (getByte() != 0) {
            visitLoop(br);
        }
    }

    private void readByte() {
        verifyDataPointer();
        try {
            memory[dataPointer] = (byte) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte getByte() {
        verifyDataPointer();
        return memory[dataPointer];
    }

    private void inc() {
        verifyDataPointer();
        memory[dataPointer]++;
    }

    private void dec() {
        verifyDataPointer();
        memory[dataPointer]--;
    }

    private void verifyDataPointer() {
        if (dataPointer < 0 || dataPointer >= memory.length) {
            System.err.println("Data pointer is out of bounds: " + dataPointer);
            System.exit(0);
        }
    }
}
