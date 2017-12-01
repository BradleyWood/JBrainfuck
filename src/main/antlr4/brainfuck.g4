grammar brainfuck;

program
    :   command+
    ;
command
    :   GT
    |   LT
    |   ADD
    |   SUB
    |   DOT
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
LPAREN  :   '[';
RPAREN  :   ']';
