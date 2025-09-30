grammar InterviewModel;
 
// Parser Rules
start: interview_model  (ANSWER'/' specification NEWLINE?)+ EOF;

interview_model: 'INTERVIEW ID' COLLON DIGIT NEWLINE;

// Constitution of the Specification
specification: boolean_spec | short_text_spec | option_spec | multi_spec | digit_spec | decimal_spec | date_spec | time_spec | range_spec;

boolean_spec: 'BOOLEAN' COLLON BOOLEAN;
short_text_spec: 'SHORT_TEXT' COLLON SHORT_TEXT;
option_spec: 'OPTION' COLLON option;
multi_spec: 'MULTIPLE_CHOICE' COLLON multi;
digit_spec: 'DIGIT' COLLON DIGIT;
decimal_spec: 'DECIMAL' COLLON DECIMAL;
date_spec: 'DATE' COLLON DATE;
time_spec: 'TIME' COLLON TIME;
range_spec: 'RANGE' COLLON DIGIT;


multi: option (',' option)*;
option: OPT_LETTERS ')';
 
OPT_LETTERS: [a-d] ;
 
ANSWER: DIGIT '-' 'ANSWER' ;
BOOLEAN: 'True' | 'False' ;
DIGIT: [0-9];
DATE: ([0-2]DIGIT | '3'[0-1]) '-' ('0'[0-9] | '1'[0-2]) '-' DIGIT DIGIT DIGIT DIGIT;
COLLON: ':';
DECIMAL: DIGIT+ '.' DIGIT+;
SHORT_TEXT: [a-zA-Z0-9]+;
TIME: ([0-1][0-9] | '2'[0-3]) COLLON ([0-5][0-9]);
NEWLINE: '\r' | '\n';
WS : [ \t\r\n]+ -> skip ;