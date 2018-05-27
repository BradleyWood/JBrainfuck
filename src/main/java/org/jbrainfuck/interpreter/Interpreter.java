package org.jbrainfuck.interpreter;

import org.jbrainfuck.program.Loop;
import org.jbrainfuck.program.OpCode;
import org.jbrainfuck.program.Program;
import org.jbrainfuck.program.ProgramVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Interpreter implements ProgramVisitor {

    private final Program program;

    private final byte[] memory;
    private int dataPointer = 0;

    private InputStream in = System.in;
    private PrintStream out = System.out;

    public Interpreter(final Program program, final int maxMemory) {
        this.program = program;
        memory = new byte[maxMemory];
    }

    public Interpreter(final Program program) {
        this(program, 4096);
    }

    public void run() {
        program.accept(this);
    }

    public void setOut(final PrintStream out) {
        this.out = out;
    }

    public void setIn(final InputStream in) {
        this.in = in;
    }

    @Override
    public void visitOpcode(final OpCode opCode) {
        switch (opCode.getCmd()) {
            case OUTPUT:
                out.print((char) getByte());
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
    public void visitLoop(final Loop br) {
        while (getByte() != 0) {
            Arrays.stream(br.getOpCodes()).forEach(op -> op.accept(this));
        }
    }

    private void readByte() {
        verifyDataPointer();
        try {
            memory[dataPointer] = (byte) in.read();
        } catch (final IOException e) {
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
