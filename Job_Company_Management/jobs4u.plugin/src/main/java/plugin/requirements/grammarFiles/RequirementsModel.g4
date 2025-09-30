grammar RequirementsModel;

// Parser Rules
start: requirement_model (REQ '/' specification NEWLINE?)+ EOF;

requirement_model: 'REQUIREMENT ID' COLLON DIGIT NEWLINE;

// Constitution of the Specification
specification: boolean_spec | short_text_spec | long_text_spec | education_level_spec | languages_spec | digit_spec | decimal_spec | programming_languages_spec | date_spec | range_spec;

boolean_spec: 'BOOLEAN' COLLON BOOLEAN;
short_text_spec: 'SHORT_TEXT' COLLON SHORT_TEXT;
long_text_spec: 'LONG_TEXT' COLLON LONG_TEXT;
education_level_spec: 'EDUCATION_LEVEL' COLLON EDUCATION_LEVEL;
languages_spec: 'LANGUAGES' COLLON multi_languages;
programming_languages_spec: 'PROGRAMMING_LANGUAGES' COLLON multi_planguages;
digit_spec: 'DIGIT' COLLON DIGIT;
decimal_spec: 'DECIMAL' COLLON DECIMAL;
date_spec: 'DATE' COLLON DATE;
range_spec: 'RANGE' COLLON RANGE;


multi_planguages: PROGRAMMING_LANGUAGES (',' PROGRAMMING_LANGUAGES)*;
multi_languages: LANGUAGES (',' LANGUAGES)*;

OPT_LETTERS: [a-d];

REQ: DIGIT '-' 'REQ';

EDUCATION_LEVEL: (([Pp]'rimary' | [Ss]'econdary' | [Hh]'igher') [Ss]'chool') | [Bb]'achelor' | [Mm]'asters' | [Pp]'ostgraduate' | [Oo]'ther';

LANGUAGES: [Ee]'nglish' | [Ff]'rench' | [Gg]'erman' | [Ss]'panish' | [Oo]'ther';

PROGRAMMING_LANGUAGES: [Jj]'ava' | [Pp]'ython' | [Rr]'uby' | [Pp]'hp' | [Cc]'++' | [Cc]'sharp' | [Oo]'ther';

BOOLEAN: ([Yy]'es') | ([Nn]'o');

DIGIT: [0-9];

COLLON: ':';

DECIMAL: DIGIT+ '.' DIGIT+;

SHORT_TEXT: [a-zA-Z0-9]+;

LONG_TEXT: '"' (~["\r\n])* '"';

DATE: DIGIT DIGIT DIGIT DIGIT '-' DIGIT DIGIT '-' DIGIT DIGIT;

RANGE: DIGIT+ '-' DIGIT+;

NEWLINE: '\r'? '\n';

WS: [ \t\r\n]+ -> skip;
