package org.jbrainfuck;

public enum Command {
    INC_PTR('>', "increment the data pointer"),
    DEC_PTR('<', "decrement the data pointer"),
    INC('+', "increment the byte at the data pointer"),
    DEC('-', "decrement the byte at the data pointer"),
    OUTPUT('.', "output the byte at the data pointer"),
    INPUT(',', "input one byte to the byte at the data pointer"),
    LPAREN('[', ""),
    R_PAREN(']', "");

    public char ch;
    public String desc;

    Command(char ch, String desc) {
        this.ch = ch;
        this.desc = desc;
    }
}
