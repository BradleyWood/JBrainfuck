package org.jbrainfuck.interpreter;

import org.jbrainfuck.program.Brackets;
import org.jbrainfuck.program.OpCode;
import org.jbrainfuck.program.Program;
import org.jbrainfuck.program.ProgramVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Interpreter implements ProgramVisitor {

    private final Program program;

    private byte[] memory = new byte[4096];
    private int dataPointer = 0;

    private InputStream in = System.in;
    private PrintStream out = System.out;

    public Interpreter(Program program) {
        this.program = program;
    }

    public void run() {
        program.accept(this);
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public void setIn(InputStream in) {
        this.in = in;
    }

    @Override
    public void visitOpcode(OpCode opCode) {
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
            memory[dataPointer] = (byte) in.read();
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
