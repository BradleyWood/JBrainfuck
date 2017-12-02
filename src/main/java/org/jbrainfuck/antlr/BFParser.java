package org.jbrainfuck.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.jbrainfuck.BrainfuckLexer;
import org.jbrainfuck.BrainfuckParser;
import org.jbrainfuck.antlr.visitor.BFFileVisitor;
import org.jbrainfuck.program.Program;

import java.io.*;

public class BFParser {

    private final InputStream inputStream;

    public BFParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public BFParser(File file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    public BFParser(String file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    public Program parse() throws IOException {
        BrainfuckLexer lexer = new BrainfuckLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        BrainfuckParser parser = new BrainfuckParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);

        BFErrorListener errorListener = new BFErrorListener();
        parser.addErrorListener(errorListener);

        BFFileVisitor bfFileVisitor = new BFFileVisitor();

        Program program = bfFileVisitor.visit(parser.program());

        if (errorListener.getErrorCount() > 0) {
            errorListener.getErrors().stream().forEach(System.err::println);
            return null;
        }

        return program;
    }
}
