grammar Brainfuck;

program
    :   command+
    ;
command
    :   GT
    |   LT
    |   ADD
    |   SUB
    |   DOT
    |   COMMA
    |   brackets
    ;

brackets
    :   LPAREN command+ RPAREN
    ;

GT      :   '>';
LT      :   '<';
ADD     :   '+';
SUB     :   '-';
DOT     :   '.';
COMMA   :   ',';
LPAREN  :   '[';
RPAREN  :   ']';


WS      :   [ \t\r\n\u000C]+ -> skip;

WORDS   :   [a-zA-Z]-> skip;
