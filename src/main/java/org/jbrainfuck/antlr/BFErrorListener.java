package org.jbrainfuck.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.LinkedList;

public class BFErrorListener extends BaseErrorListener {

    private final LinkedList<String> errors = new LinkedList<>();

    @Override
    public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
                            final int charPositionInLine, final String msg, final RecognitionException e) {
        errors.add("syntax error at line " + line + ":" + charPositionInLine + " " + msg);
    }

    public int getErrorCount() {
        return errors.size();
    }

    public LinkedList<String> getErrors() {
        return errors;
    }
}
